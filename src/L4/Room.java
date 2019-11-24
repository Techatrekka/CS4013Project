package L4;

public class Room extends Hotel {

    public String type;
    public int occupancy;
    public boolean breakfastIncluded;

    Room(){}

   public Room (String type, int occupancy, boolean breakfastIncluded) {
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

    void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    int getOccupancy() {
        return occupancy;
    }

    void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    int occupancyMin() {
        return 1;
    }

    int occupancyMax() {
        return occupancy;
    }

    @Override
    public String toString(){
        return getType() + " " + getOccupancy() + " " + isBreakfastIncluded();
    }
}