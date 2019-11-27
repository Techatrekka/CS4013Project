package L4;

public class Room extends Hotel {

	public String type;
	public String occupancy;
	public boolean breakfastIncluded;

	Room(){}

   public Room (String type, String occupancy, boolean breakfastIncluded) {
		this.type = type;
		this.occupancy = occupancy;
		this.breakfastIncluded = breakfastIncluded;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}

	public String getOccupancy() {
		return occupancy;
	}

	void setBreakfastIncluded(boolean breakfastIncluded) {
		this.breakfastIncluded = breakfastIncluded;
	}

	public boolean isBreakfastIncluded() {
		return breakfastIncluded;
	}

	String occupancyMin() {
		return "1-0";
	}

	String occupancyMax() {
		return occupancy;
	}

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
