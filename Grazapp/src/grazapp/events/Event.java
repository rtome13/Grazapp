package grazapp.events;


public class Event {

	private int eID; 
	private String name;
	private String type;
	private String location;
	private String locationLatitude;
	private String locationLongitude;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String description;
	private String source;
	//private Image eventImage; 	//TODO: Add image

	/**
	 * Initialize Event
	 */
	public Event() { }

	public void setEID(int eID) { this.eID = eID; }
	public int getEID() { return eID; }

	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	public void setType(String type) { this.type = type; }
	public String getType() { return type; }

	public void setLocation(String location) { this.location = location; }
	public String getLocation() { return location; }

	public void setLocationLatitude(String locationLatitude) { this.locationLatitude = locationLatitude; }
	public String getLocationLatitude() { return locationLatitude; }

	public void setLocationLongitude(String locationLongitude) { this.locationLongitude = locationLongitude; }
	public String getLocationLongitude() { return locationLongitude; }

	public void setStartDate(String startDate) { this.startDate = startDate; }
	public String getStartDate() { return startDate; }

	public void setStartTime(String startTime) { this.startTime = startTime; }
	public String getStartTime() { return startTime; }

	public void setEndDate(String endDate) { this.endDate = endDate; }
	public String getEndDate() { return endDate; }

	public void setEndTime(String endTime) { this.endTime = endTime; }
	public String getEndTime() { return endTime; }

	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return description; }

	public void setSource(String source) { this.source = source; }
	public String getSource() { return source; }
	
	/**
	 * Let's create default event for testing
	 */
	public void setAll(String eID, String name, String type, String location, String locationLatitude, String locationLongitude, String startDate, String startTime, String endDate, String endTime, String description, String source) {
		this.eID = Integer.parseInt(eID);
		this.name = name;
		this.type = type;
		this.location = location;
		this.locationLatitude = locationLatitude;
		this.locationLongitude = locationLongitude;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.description = description;
		this.source = source;
	}

	/**
	 * Returns the event as a string for later saving or something
	 * @return event as a ; separated string
	 */
	@Override
	public String toString() {

		return name + ": " + startDate + " - " + startTime;
		//return eID + ";" + name + ";" + type + ";" + location + ";" + locationLatitude + ";" + locationLongitude + ";" + startDate + ";" + startTime + ";" + endDate + ";" + endTime + ";" + description;
	}


	/**
	 * Read the data from somewhere (is it a string), this just for testing
	 * @param string where the data is?
	 */
	public void parse(String string) {
		// TODO: read the data of an event somehow. Currently just for testing
		String [] sArray = string.split(";");
		eID = Integer.valueOf(sArray[0]);
		name = sArray[1];
		type = sArray[2];
		location = sArray[3];
		locationLatitude = sArray[4];
		locationLongitude = sArray[5];
		startDate = sArray[6];
		startTime = sArray[7];
		endDate = sArray[8];
		endTime = sArray[9];
		description = sArray[10];
		//eventImage = sArray[11];
	}


}

