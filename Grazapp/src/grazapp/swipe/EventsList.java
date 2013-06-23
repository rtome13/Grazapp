package grazapp.swipe;

import java.util.Calendar;

import grazapp.database.DatabaseManager;
import grazapp.events.Event;
import grazapp.events.ViewEvent;
import grazapp.main.AppManager;
import grazapp.main.R;
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
public class EventsList extends ListFragment{
	public static final String ARG_SECTION_NUMBER = "section_number";
	public DatabaseManager dbm = null;
	private static Event[] events = null;
	private static int oldestEvent = 0;

	public EventsList(){}	
	
	public EventsList(DatabaseManager dbm){
		this.dbm = dbm;
	}
	
	public class AlphabeticArrayAdapter extends ArrayAdapter<Event> {
		private Context context;
		

		public AlphabeticArrayAdapter(Context context, Event[] events, int oldestEvent) {
			super(context, R.layout.element_events_list, events);
			this.context = context;
			EventsList.oldestEvent = oldestEvent;
			EventsList.events = events;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.element_events_list, parent, false);	

			ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

			if(events[position].getType().equals("Party"))
				imageView.setImageResource(R.drawable.party_icon2);
			if(events[position].getType().equals("ESNEvent"))
				imageView.setImageResource(R.drawable.esn_icon);
			if(events[position].getType().equals("Festival"))			
				imageView.setImageResource(R.drawable.concert_icon);
			if(events[position].getType().equals("Movie"))				
				imageView.setImageResource(R.drawable.movie_icon);
			if(events[position].getType().equals("Sports"))				
				imageView.setImageResource(R.drawable.sport_icon);
			if(events[position].getType().equals("Dance"))	
				imageView.setImageResource(R.drawable.dance_icon);
			
			
			TextView title = (TextView) rowView.findViewById(R.id.label_title);
			title.setText(events[position].getName());

			TextView description = (TextView) rowView.findViewById(R.id.label_description);
			description.setText(events[position].getDescription());
			
			TextView date = (TextView) rowView.findViewById(R.id.label_date);
			date.setText(events[position].getStartDate() + " - " + events[position].getStartTime());
					
			if(events[position].getEID() == oldestEvent){
				TextView labelPastEvents = (TextView) rowView.findViewById(R.id.pastEvents);
				labelPastEvents.setVisibility(TextView.VISIBLE);
			}
			
			return rowView;
		}
	} 
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH)+1;
		int currentDay = c.get(Calendar.DAY_OF_MONTH);
		int currentHour = c.get(Calendar.HOUR_OF_DAY);
		int currentMinute = c.get(Calendar.MINUTE);
		
		Boolean oldEvent = false;
		int oldestEvent = 0;
		
		AppManager appManager = new AppManager(dbm, "addEvent");
		Event[] events = appManager.getAllEvents();
		
		int numberEvents = events.length;
		int numberPastEvents = 0;
		int numberFutureEvents = 0;
		
		for(int eventNr = 0; eventNr < numberEvents; eventNr++){
			String eventEndDate = events[eventNr].getEndDate();
			String eventEndTime = events[eventNr].getEndTime();	
			
			System.out.println("future:" + events[eventNr]);
			
			
			String[] eventDateSplitted = eventEndDate.split("-");
			String[] eventTimeSplitted = eventEndTime.split(":");
			
			if(Integer.parseInt(eventDateSplitted[0]) < currentYear && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) < currentMonth && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) < currentDay && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) == currentDay && 
					Integer.parseInt(eventTimeSplitted[0]) < currentHour && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) == currentDay && 
					Integer.parseInt(eventTimeSplitted[0]) == currentHour &&
					Integer.parseInt(eventTimeSplitted[1]) <= currentMinute && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}

				
			if(!oldEvent)
				numberFutureEvents++;
			else
				numberPastEvents++;
		}
		numberFutureEvents--;
		numberPastEvents++;

		Event[] reorderedEvents = new Event[numberEvents];
		
		Event[] pastEvents = new Event[numberPastEvents];
		Event[] futureEvents = new Event[numberFutureEvents];
		
		for(int eventNr = 0; eventNr < numberFutureEvents; eventNr++){
			futureEvents[numberFutureEvents-eventNr-1] = events[eventNr];
		}
		
		for(int eventNr = 0; eventNr < numberPastEvents; eventNr++){
			pastEvents[eventNr] = events[numberFutureEvents+eventNr];
		}
		
		
		
		for(int eventNr = 0; eventNr < numberFutureEvents; eventNr++){
			reorderedEvents[eventNr] = futureEvents[eventNr];
		}
		
		for(int eventNr = 0; eventNr < numberPastEvents; eventNr++){
			reorderedEvents[numberFutureEvents+eventNr] = pastEvents[eventNr];
		}
	
		
      AlphabeticArrayAdapter adapter = new AlphabeticArrayAdapter(getActivity(), reorderedEvents, oldestEvent);
      
      setListAdapter(adapter);
      
      registerForContextMenu(getListView());
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {        
       // Intent intent = new Intent(getActivity(), ViewEvent.class);
        Intent intent = new Intent(v.getContext(), ViewEvent.class);
        intent.putExtra("name", events[position].getName());
        intent.putExtra("type", events[position].getType());
        intent.putExtra("location", events[position].getLocation());
        intent.putExtra("locationlatitude", events[position].getLocationLatitude());
        intent.putExtra("locationlongitude", events[position].getLocationLongitude());
        intent.putExtra("start", events[position].getStartDate() + " - " + events[position].getStartTime());
        intent.putExtra("end", events[position].getEndDate() + " - " + events[position].getEndTime());
        intent.putExtra("description", events[position].getDescription());
        startActivity(intent);

    }
    
    @Override
    public void onResume(){
  	  super.onResume();
      
		Calendar c = Calendar.getInstance();
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH)+1;
		int currentDay = c.get(Calendar.DAY_OF_MONTH);
		int currentHour = c.get(Calendar.HOUR_OF_DAY);
		int currentMinute = c.get(Calendar.MINUTE);
		
		Boolean oldEvent = false;
		int oldestEvent = 0;
		
		AppManager appManager = new AppManager(dbm, "addEvent");
		Event[] events = appManager.getAllEvents();
		
		int numberEvents = events.length;
		int numberPastEvents = 0;
		int numberFutureEvents = 0;
		
		for(int eventNr = 0; eventNr < numberEvents; eventNr++){
			System.out.println("CURRENT EVENT: " + events[eventNr].getName());
			String eventEndDate = events[eventNr].getEndDate();
			String eventEndTime = events[eventNr].getEndTime();		
			
			String[] eventDateSplitted = eventEndDate.split("-");
			String[] eventTimeSplitted = eventEndTime.split(":");
			
			if(Integer.parseInt(eventDateSplitted[0]) < currentYear && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) < currentMonth && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) < currentDay && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) == currentDay && 
					Integer.parseInt(eventTimeSplitted[0]) < currentHour && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}
			else
			if(Integer.parseInt(eventDateSplitted[0]) == currentYear &&
					Integer.parseInt(eventDateSplitted[1]) == currentMonth &&
					Integer.parseInt(eventDateSplitted[2]) == currentDay && 
					Integer.parseInt(eventTimeSplitted[0]) == currentHour &&
					Integer.parseInt(eventTimeSplitted[1]) <= currentMinute && oldEvent == false){
				oldEvent = true;
				oldestEvent = events[eventNr-1].getEID();	
			}

				
			if(!oldEvent)
				numberFutureEvents++;
			else
				numberPastEvents++;
		}
		
		numberFutureEvents--;
		numberPastEvents++;
		
		Event[] reorderedEvents = new Event[numberEvents];
		
		Event[] pastEvents = new Event[numberPastEvents];
		Event[] futureEvents = new Event[numberFutureEvents];
		
		for(int eventNr = 0; eventNr < numberFutureEvents; eventNr++){
			futureEvents[numberFutureEvents-eventNr-1] = events[eventNr];
		}
		
		for(int eventNr = 0; eventNr < numberPastEvents; eventNr++){
			pastEvents[eventNr] = events[numberFutureEvents+eventNr];
		}
		
		
		
		for(int eventNr = 0; eventNr < numberFutureEvents; eventNr++){
			reorderedEvents[eventNr] = futureEvents[eventNr];
		}
		
		for(int eventNr = 0; eventNr < numberPastEvents; eventNr++){
			reorderedEvents[numberFutureEvents+eventNr] = pastEvents[eventNr];
		}
		System.out.println("OLDEST EVENT: " + oldestEvent);
		
      AlphabeticArrayAdapter adapter = new AlphabeticArrayAdapter(getActivity(), reorderedEvents, oldestEvent);

		
      setListAdapter(adapter);
      
      registerForContextMenu(getListView());
    }
    
    

}