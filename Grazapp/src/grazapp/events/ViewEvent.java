package grazapp.events;

import grazapp.main.R;
import grazapp.main.ViewMap;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewEvent extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_event);

		ImageButton mapButton = (ImageButton) findViewById(R.id.mapButton);

		mapButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Bundle extras = getIntent().getExtras();
				String eventName = extras.getString("name");
				String locationName = extras.getString("location");
				String locationLatitude = extras.getString("locationlatitude");
				String locationLongitude = extras
						.getString("locationlongitude");
				Intent intent = new Intent(view.getContext(), ViewMap.class);
				intent.putExtra("eventname", eventName);
				intent.putExtra("locationname", locationName);
				intent.putExtra("locationlatitude", locationLatitude);
				intent.putExtra("locationlongitude", locationLongitude);
				startActivity(intent);
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_event, menu);

		Bundle extras = getIntent().getExtras();

		String name = extras.getString("name");
		TextView nv = (TextView) findViewById(R.id.text_event_name);
		nv.setText(name);

		String type = extras.getString("type");
		TextView tv = (TextView) findViewById(R.id.text_event_type);
		tv.setText(type);

		String location = extras.getString("location");
		TextView lv = (TextView) findViewById(R.id.editText_location);
		lv.setText(location);

		String start = extras.getString("start");
		TextView sv = (TextView) findViewById(R.id.editText_start);
		sv.setText(start);

		String end = extras.getString("end");
		TextView ev = (TextView) findViewById(R.id.editText_end);
		ev.setText(end);

		String description = extras.getString("description");
		TextView dv = (TextView) findViewById(R.id.editText_description);
		dv.setText(description);

		return true;
	}

}
