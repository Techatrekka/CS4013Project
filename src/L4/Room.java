package L4;

/**
 * @author: Nicole Berty
 */

public class Room extends Hotel {

	public String type;
	String occupancy;
	private boolean breakfastIncluded;

	Room(){}

	/**
	 * Creates a room object
	 * @param type
	 * @param occupancy
	 * @param breakfastIncluded
	 */
   public Room (String type, String occupancy, boolean breakfastIncluded) {
		this.type = type;
		this.occupancy = occupancy;
		this.breakfastIncluded = breakfastIncluded;
	}

	/**
	 * Sets the type of room
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the room type
	 */
	private String getType() {
		return type;
	}

	/**
	 * Sets the occupancy of a room
	 * @param occupancy
	 */
	void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}

	/**
	 * Gets the occupancy of a room
	 * @return
	 */
	private String getOccupancy() {
		return occupancy;
	}

	/**
	 * Set whether breakfast is included in a room.
	 * @param breakfastIncluded
	 */
	void setBreakfastIncluded(boolean breakfastIncluded) {
		this.breakfastIncluded = breakfastIncluded;
	}

	/**
	 * @return true or false based on whether breakfast is included with a room
	 */
	private boolean isBreakfastIncluded() {
		return breakfastIncluded;
	}

	/**
	 * @return room details formatted for CSV file
	 */
	public String toCSV()
	{
		return getType() + "_" + getOccupancy() + "_" + isBreakfastIncluded();
	}

	@Override
	public String toString()
	{
		String breakfast = "/ Breakfast not included";
		if (isBreakfastIncluded())
		{
			breakfast = "/ Breakfast included";
		}
		return getType() + " " + breakfast;
	}
}
