package L4;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel extends L4 {

    private String name, location;
    double rating;
    int noOfRooms;
    ArrayList<Room> RoomTypes = new ArrayList<Room>();

    Hotel(){}

    Hotel(String name, String location, double rating, int noOfRooms){
        this.name = name;
        this.location = location;
        this.noOfRooms = noOfRooms;
        this.rating = rating;
    }

    double getRating() {
        return this.rating;
    }

    String getName(){
        return this.name;
    }

    String getLocation(){
        return this.location;
    }

    int getNoOfRooms() {return noOfRooms;}

    void createRooms(String type, int numOfRooms,
                     int occupancy,boolean BF)  {
        for (int i = 0; i < numOfRooms; i++) {
            Room room = new Room(type,occupancy,BF);
            RoomTypes.add(room);
        }
    }

    public ArrayList<Room> getRoomTypes() {
        return RoomTypes;
    }

    /*assume all rooms are empty*/

    String checkAvailability(LocalDate checkIn,
                             LocalDate checkOut) {
       return null;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Location: " +
                getLocation() +"Rating: " + getRating() +
                "Number of Rooms: " + getNoOfRooms();
    }
}