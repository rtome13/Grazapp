package grazapp.sights;

public class Sight {
	private String name;
	private String type;
	private String location;
	private String locationLatitude;
	private String locationLongitude;
	private String openingHours;
	private String closingHours;
	private String description;
	//private Image eventImage; 	//TODO: Add image

	/**
	 * Initialize Sight
	 */
	public Sight(String name) { this.name = name; }

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

	public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }
	public String getOpeningHours() { return openingHours; }

	public void setClosingHours(String closingHours) { this.closingHours = closingHours; }
	public String getClosingHours() { return closingHours; }

	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return description; }


	/**
	 * Let's create default event for testing
	 */
	public void setAll(String name, String type, String location, String locationLatitude, String locationLongitude, String openingHours, String closingHours, String description) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.locationLatitude = locationLatitude;
		this.locationLongitude = locationLongitude;
		this.openingHours = openingHours;
		this.closingHours = closingHours;
		this.description = description;
	}


}

