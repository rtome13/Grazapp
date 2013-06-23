package grazapp.main;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class ViewMap extends Activity {

	private float locationLatitude;
	private float locationLongitude;
	private String locationName;
	private LatLng locationLatLng;
	private String eventName;
	private GoogleMap GMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_map);
		Bundle extras = getIntent().getExtras();
		eventName = extras.getString("eventname");
		locationName = extras.getString("locationname");
		locationLatitude = Float.parseFloat(extras.getString("locationlatitude"));
		locationLongitude = Float.parseFloat(extras.getString("locationlongitude"));
		locationLatLng = new LatLng(locationLatitude,locationLongitude);
		
		
		GMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();
		
		Marker locationMarker = GMap.addMarker(new MarkerOptions().position(locationLatLng).title(eventName + " @ " + locationName));

		GMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng,15));
		
		
	}

}
