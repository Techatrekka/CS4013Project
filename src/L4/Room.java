package L4;

/**
 * @author:
 */

public class Room extends Hotel {

	public String type;
	public String occupancy;
	public boolean breakfastIncluded;

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
	public String getType() {
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
	public String getOccupancy() {
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
	public boolean isBreakfastIncluded() {
		return breakfastIncluded;
	}

	String occupancyMin() {
		return "1-0";
	}

	String occupancyMax() {
		return occupancy;
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
