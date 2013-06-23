package grazapp.swipe;

import grazapp.main.AppManager;
import grazapp.main.R;
import grazapp.sights.Sight;
import grazapp.sights.ViewSight;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class SightsList extends ListFragment{
	public static final String ARG_SECTION_NUMBER = "section_number";
	public AppManager appManager = null;
	private static Sight[] sights = null;
	
	public SightsList(){
       	sights = new Sight[10];
       	populateSights();
	}
	
	public class AlphabeticArrayAdapter extends ArrayAdapter<Sight> {
		private Context context;
		private Sight[] sights;

		public AlphabeticArrayAdapter(Context context, Sight[] sights) {
			super(context, R.layout.element_sights_list, sights);
			this.context = context;
			this.sights = sights;
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.element_sights_list, parent, false);	

			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
			
			if(sights[position].getType().equals("Monument"))
				imageView.setImageResource(R.drawable.monument_icon);
			if(sights[position].getType().equals("Cafe/Restaurant"))
				imageView.setImageResource(R.drawable.cafe_icon);
			if(sights[position].getType().equals("Museum"))			
				imageView.setImageResource(R.drawable.museum_icon);
			if(sights[position].getType().equals("Sight"))				
				imageView.setImageResource(R.drawable.sight_icon);
			if(sights[position].getType().equals("Music"))				
				imageView.setImageResource(R.drawable.music_icon);

			TextView title = (TextView) rowView.findViewById(R.id.label_title);
			title.setText(sights[position].getName());

			TextView description = (TextView) rowView.findViewById(R.id.label_description);
			description.setText(sights[position].getDescription());
					

			return rowView;
		}
	} 
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);

      AlphabeticArrayAdapter adapter = new AlphabeticArrayAdapter(getActivity(), sights);
      setListAdapter(adapter);
      
      registerForContextMenu(getListView());
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {        
        Intent intent = new Intent(v.getContext(), ViewSight.class);
        intent.putExtra("name", sights[position].getName());
        intent.putExtra("type", sights[position].getType());
        intent.putExtra("location", sights[position].getLocation());
        intent.putExtra("locationlatitude", sights[position].getLocationLatitude());
        intent.putExtra("locationlongitude", sights[position].getLocationLongitude());
        intent.putExtra("openingHours", sights[position].getOpeningHours());
        intent.putExtra("closingHours", sights[position].getClosingHours());
        intent.putExtra("description", sights[position].getDescription());
        startActivity(intent);
    }
    
    @Override
    public void onResume(){
  	  super.onResume();
  	  
      AlphabeticArrayAdapter adapter = new AlphabeticArrayAdapter(getActivity(), sights);
      setListAdapter(adapter);
      
      registerForContextMenu(getListView());
    }
    
    
    public void populateSights(){
    	Sight s1 = new Sight("The Clock tower of Graz");
    	s1.setDescription("The Clock Tower of Graz (Grazer Uhrturm) is a historical building on top of the Schlossberg mountain. Built in in the 14th century, it is a significant monument to the city of Graz and has one of its original bells still attached. The Clock Tower is 28 meters high and its current machinery is masterpiece from 18th century engineer Sylvester Funck. If you visit the Clock Tower you should bring a picnic basket and visit the rosegarden just situated around it.");
    	s1.setLocationLatitude("47.074185");
    	s1.setLocationLongitude("15.438538");
    	s1.setLocation("Graz Schlossberg Clock Tower 8010 Graz");
    	s1.setClosingHours("-");
    	s1.setOpeningHours("-");
    	s1.setType("Monument");
    	sights[0] = s1;
    	
    	Sight s2 = new Sight("Schlossberg");
    	s2.setDescription("The word \"Schloßberg\" literally means \"castle mountain\", which describes it exactly. It is a hill topped by a castle, in the centre of the city of Graz, Austria. Among Graz\'s most famous tourist attractions, the castle, never occupied until its partial demolition by Napoleonic forces under the Peace of Schönbrunn of 1809, was once a place of refuge for Graz\'s residents. It was turned into a public park on account of Ludwig von Welden in 1839. The Schloßberg contains an \"Uhrturm\" (clock tower), which functions as a recognisable icon for the city. Remarkably, the clock\'s handles have opposite roles to the common notion. That is, the larger one marks hours while the smaller is for minutes. This is due to the fact that originally only the larger handle was there to point out hours and display of minutes was only added later. Near the Uhrturm there is a café with views over the old town. Additionally, on the western side of the Schloßberg, there are two small cafés, one with table service and the other one with self-service. Next to the terminus of the funicular railway there is a hilltop restaurant with views of western Graz. There is also a Turkish Well that was built by Turkish slaves that was used to get water during times when Schlossberg was under siege. The water was routed from the nearby River Mur. An open-air stage for concerts and performances is located in the cellars of the former fortress. Furthermore, you can see two bastions and the belltower. To reach the top of the hill one can either take the funicular railway (Schloßbergbahn), an elevator built inside the mountain, or one of the older sets of pathways and steps.");
    	s2.setLocationLatitude("47.073952");
    	s2.setLocationLongitude("15.437565");
    	s2.setLocation("Schlossberg, 8010 Graz");
    	s2.setClosingHours("-");
    	s2.setOpeningHours("-");
    	s2.setType("Sight");
    	sights[1] = s2;
    	
    	Sight s3 = new Sight("Kunsthaus");
    	s3.setDescription("The Kunsthaus Graz, Grazer Kunsthaus, or Graz Art Museum was built as part of the European Capital of Culture celebrations in 2003 and has since become an architectural landmark in Graz, Austria. Its exhibition program specializes in contemporary art of the last four decades. Its unusual form differs radically from conventional exhibition contexts, many of which maintain the traditions of the modernist \"White Cube\". The team of architects used an innovative stylistic idiom, known as blob architecture within the historical ambiance of the Murvorstadt. Thus, the gigantic building affectionately called the \"Friendly Alien\" by its creators Peter Cook and Colin Fournier, in form and material, stands out consciously against the surrounding baroque roof landscape with its red clay roofing tiles but nevertheless integrates the façade of the 1847 iron house.");
    	s3.setLocationLatitude("47.071423");
    	s3.setLocationLongitude("15.434396");
    	s3.setLocation("Lendkai 1, 8020 Graz");
    	s3.setClosingHours("17h");
    	s3.setOpeningHours("10h");
    	s3.setType("Museum");
    	sights[2] = s3;
    	
    	Sight s4 = new Sight("Murinsel");
    	s4.setDescription("The Murinsel (in German, literally Mur island) in Graz, Austria, is actually not an island at all, but an artificial floating platform in the middle of the Mur river. This landmark of Graz was designed by New York artist Vito Acconci on the occasion of Graz becoming the 2003 European Capital of Culture.The building in the form of a giant sea shell measures 50m in length and 20m in width. Two footbridges connect it with both banks of the Mur. The center of the platform forms an amphitheatre. Below a twisted round dome there is a café and a playground. The Murinsel is built for a maximum number of 350 visitors.");
    	s4.setLocationLatitude("47.073527");
    	s4.setLocationLongitude("15.434546");
    	s4.setLocation("Mariahilferplatz 1, 8020 Graz");
    	s4.setClosingHours("22h");
    	s4.setOpeningHours("10h");
    	s4.setType("Cafe/Restaurant");
    	sights[3] = s4;
    	
     	Sight s5 = new Sight("Stadtpark");
    	s5.setDescription("Graz as a garden city – more than 70% of its area are green spaces. Seemingly a waste of green, the Stadtpark (city park) stretches like a belt around the formerly fortified Old Town. Thanks to some capable people in the 19th century, buildings were not allowed to be erected on the former Glacis, and the park with its in part exotic trees was created.In the 60s and 70s of the 20th century, committed young artists and writers made Stadtpark famous all over Europe. They founded \"Forum Stadtpark\", which since has been an important institution for all branches of the avant-garde.");
    	s5.setLocationLatitude("47.08228");
    	s5.setLocationLongitude("15.438194");
    	s5.setLocation("Stadtpark 1, 8010 Graz");
    	s5.setClosingHours("-");
    	s5.setOpeningHours("-");
    	s5.setType("Sight");
    	sights[4] = s5;
    	
    	Sight s6 = new Sight("Pamukkale Kebap");
    	s6.setDescription("Pamukkale is one of the best (if not 'the best') Döner Kebab places you can visit in Graz. Try the delicous selection of kebabs and falafels to stimulate your tasty senses.");
    	s6.setLocationLatitude("47.066674");
    	s6.setLocationLongitude("15.446538");
    	s6.setLocation("Dietrichsteinplatz 1, 8010 Graz");
    	s6.setClosingHours("24h");
    	s6.setOpeningHours("0h");
    	s6.setType("Cafe/Restaurant");
    	sights[5] = s6;
    	
    	Sight s7 = new Sight("Grazer Oper");
    	s7.setDescription("Opera had been performed in Graz since the 17th century, originally in a converted coach house on the Hapsburg royal estates. The National Theatre (Schauspielhaus Graz) constructed in 1776 saw many early performances of Mozart\'s operas, although today (after many reconstructions) it is devoted to the performances of plays.[1] The city's first dedicated opera house and the immediate predecessor of the Graz Opera was the Thalia Theatre adapted in 1864 from an old circus hall. Plans for a new theatre suitable to the growing size and importance of the city and intended to be a \"new home for German art\" were first proposed 1887.[2] Designed by Ferdinand Fellner and Herman Helmer in the neo-baroque style, the Graz Opera was inaugurated in 1899 with a performance of Schiller's play William Tell, followed a few days later by Wagner's opera Lohengrin. The building suffered damage during World War II bombings but was repaired and re-opened after the war. Between 1983 and 1985 it underwent a $15 million renovation which saw the installation of modern equipment and facilities without significantly changing the original exterior and opulent interior of the building.");
    	s7.setLocationLatitude("47.069451");
    	s7.setLocationLongitude("15.445551");
    	s7.setLocation("Kaiser-Josef-Platz 10, 8010 Graz");
    	s7.setClosingHours("19h");
    	s7.setOpeningHours("9h");
    	s7.setType("Music");
    	sights[6] = s7;
    	
    	Sight s8 = new Sight("The City Hall of Graz");
    	s8.setDescription("The City Hall or Town Hall in Graz has a long history. The first hall was built in Judengasse in 1450. Soon the people realized it is too small for conducting town business and was moved to the Hauptplazt. The predessor of the mordern City House is Christopher Stadlers architectural version. The mordern City Hall of Graz was designed by the Vienna architectures Wielmans and Reuter. The two were chosen after a big architecure competition in 1886 to built the city hall in the style of old germanic architecture. The building was finished in 1893. ");
    	s8.setLocationLatitude("47.070842");
    	s8.setLocationLongitude("15.438387");
    	s8.setLocation("Hauptplatz, Graz");
    	s8.setClosingHours("-");
    	s8.setOpeningHours("-");
    	s8.setType("Monument");
    	sights[7] = s8;
    	
    	Sight s9 = new Sight("Styrian Armory");
    	s9.setDescription("The Landeszeughaus, also known as the Styrian Armory, in Graz, Austria, is the largest existing original armoury in the whole world and attracts visitors from all over the world. It holds approximately 32,000 pieces of weaponry, tools, suits of armour for battle and ones for parades.");
    	s9.setLocationLatitude("47.07");
    	s9.setLocationLongitude("15.44");
    	s9.setLocation("Herrengasse 16, 8010 Graz");
    	s9.setClosingHours("17h");
    	s9.setOpeningHours("10h");
    	s9.setType("Museum");
    	sights[8] = s9;
    	
    	Sight s10 = new Sight("Cafe Sacher");
    	s10.setDescription("The Sacher Hotel is the most famous in Vienna, and in modern times Sacher cafes are appearing in some of the country's leading cities. Graz is no exception. This chic cafe, patronized by both visitors and locals, lies in the center at the intersection of Herrengasse and Hauptplatz. With its sparkling chandeliers, glittering gilt, and red upholstery, it is the most fashionable of all the cafes of Graz. Of course, you can order the legendary rich chocolate Sachertorte here, but you can also enjoy full meals as well. From breakfast specialties, including fluffy omelets, to the final desserts of the evening, including perhaps apfelstrudel, you can sample Viennese classics here, such as a Wiener schnitzel or tafelspitz (boiled cuts of beef), and most definitely Anna Sacher's legendary fried chicken. Sweet and savory snacks are also served in the adjacent Sacher Wien-Snackbar.");
    	s10.setLocationLatitude("47.070569");
    	s10.setLocationLongitude("15.438961");
    	s10.setLocation("Herrengasse 6, 8010 Graz");
    	s10.setClosingHours("22h");
    	s10.setOpeningHours("8h30");
    	s10.setType("Cafe/Restaurant");
    	sights[9] = s10;	
    }
    
    

}