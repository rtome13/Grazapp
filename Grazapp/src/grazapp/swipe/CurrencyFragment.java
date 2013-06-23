package grazapp.swipe;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import grazapp.main.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CurrencyFragment extends Fragment {

	//Imaginary identifier for euro
	private static final int euroIdent = 98;
	private static double amountToChange;
	private static double amountAsChanged;
	private static int fromCurrency;
	private static int toCurrency;
	private static String[][] rateArray;
	private static String[] idents;
	private static ArrayList<String> identsList;
	private static double[] rates;
	private EditText editTextFrom;
	private TextView textViewTo;
	private Spinner spinnerFrom;
	private Spinner spinnerTo;
	
	private static Context context;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		context = this.getActivity().getBaseContext();
		return inflater.inflate(R.layout.view_currency_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	    initCurrencyCalculator();
	}


	public void initCurrencyCalculator() {
		editTextFrom = (EditText)getView().findViewById(R.id.editTextFrom);
		textViewTo = (TextView)getView().findViewById(R.id.textViewTo);
		spinnerFrom = (Spinner)getView().findViewById(R.id.spinner1);
		spinnerTo = (Spinner)getView().findViewById(R.id.spinner2);
		
		try {
			rateArray = retrieveRates();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		idents = getCurr(rateArray);
		identsList = identsAsList();
		rates = doubleRates(rateArray);

		addItemsOnFromSpinner();
		addItemsOnToSpinner();


		Button convertButton = (Button)getView().findViewById(R.id.buttonConvert);
		convertButton.setOnClickListener(new OnClickListener() {
			@SuppressLint("DefaultLocale")
			public void onClick(View view) {
				String amountFrom = editTextFrom.getText().toString();
				amountFrom.trim();
				try {
					amountToChange = Double.valueOf(amountFrom).doubleValue();
					// amountToChange = Double.parseDouble(amountFrom);
				} catch (NumberFormatException nfe) {
					// if value is not double
					// TODO: could exit and show in the field "invalid input"
					amountToChange = 0;
				}  
				fromCurrency = getCurrencyId(String.valueOf(spinnerFrom.getSelectedItem())); 
				toCurrency = getCurrencyId(String.valueOf(spinnerTo.getSelectedItem()));	
				amountAsChanged = convert();
				String amountText = String.format("%.2f", amountAsChanged);
				textViewTo.setText(amountText);
				//textViewTo.setText(String.valueOf(amountAsChanged));
			}
		});

	}


	// Gets currency rates from ECB (xml-file))
	public static String[][] retrieveRates() throws SAXException, IOException, ParserConfigurationException {

		// LOCAL VERSION
		InputStream is = null;
		try {
			is = context.getAssets().open("eurofxref-daily.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docB = dbf.newDocumentBuilder();
		Document doc = docB.parse(is);

		/* ONLINE VERSION (does not have euro, that has to be added)
		URL url = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		URLConnection connection = url.openConnection();
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docB = dbf.newDocumentBuilder();
		Document doc = docB.parse(connection.getInputStream());
		 */


		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("Cube");
		int listSize = nodeList.getLength()-2; 
		int values = 2;                  // only interested in two values
		String[][] rates = new String[listSize][values];
		for (int i = 2; i < nodeList.getLength(); i++) {
			Node rate = nodeList.item(i);
			rates[i-2][0] = ((Element) rate).getAttributeNode("currency").getValue(); 
			rates[i-2][1] = ((Element) rate).getAttributeNode("rate").getValue(); 
		}
		return rates;  
	}

	public static String[] getCurr(String[][] rates) {
		String [] currencies = new String[rates.length];
		for (int i = 0; i < rates.length; i++){
			int ir = 0;  // interest in [i][0]
			currencies[i] = rates[i][ir]; 
		}
		return currencies;
	}

	public static double[] doubleRates(String[][] rates) {
		double [] ratesDouble = new double[rates.length];
		for (int i = 0; i < rates.length; i++){
			int ir = 1;  // interest in [i][1]
			ratesDouble[i] = Double.parseDouble(rates[i][ir]); 
		}
		return ratesDouble;
	}

	public static int getCurrencyId(String currencyIdent) {
		int currencyId = -1;
		for (int i = 0; i < idents.length; i++) {
			if (idents[i].equals(currencyIdent)) currencyId = i;
		}
		return currencyId;
	}

	public static double convert() {
		double convertedAmount = 000.00;
		double euroRate = 0.0;
		double asEuros = 000.00;
		double otherRate = 0.0;
		for (int mat1 = 0; mat1 <= rates.length; mat1++){
			if (mat1 == fromCurrency){
				euroRate = rates[mat1];   
			}
			else 
				if (fromCurrency == euroIdent){
					euroRate = 1.0;
				}
		}
		asEuros = amountToChange / euroRate;
		for (int mat2 = 0; mat2 <= rates.length; mat2++){
			if (mat2 == toCurrency){
				otherRate = rates[mat2];   
			}
			else 
				if (toCurrency == euroIdent){
					otherRate = 1.0;
				}
		}
		convertedAmount = otherRate * asEuros;
		return convertedAmount;
	}

	public ArrayList<String> identsAsList() {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < idents.length; i++) {
			list.add(idents[i]);
		}
		return list;
	}

	// adds "from currencies" to spinner1
	public void addItemsOnFromSpinner() {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, identsList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerFrom.setAdapter(dataAdapter);
	}

	// adds "to currencies" to spinner2
	public void addItemsOnToSpinner() {
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, identsList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTo.setAdapter(dataAdapter);
	}

}
