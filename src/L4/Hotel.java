package L4;

import Sales.Price;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Hotel extends L4 {

    private String name, location;
    double rating;
    int noOfRooms;
    ArrayList<Room> RoomTypes = new ArrayList<Room>();
    private double[][] prices;

    Hotel(){}

    Hotel(String name, String location, double rating, String[] RoomDetails, int[] noOfRooms){
        this.name = name;
        this.location = location;
        this.rating = rating;
        for (int i = 0; i < noOfRooms.length; i++) {
            StringTokenizer st = new StringTokenizer(RoomDetails[i], ",");
            createRooms(st.nextToken(), noOfRooms[i] ,Integer.parseInt(st.nextToken()),Boolean.parseBoolean(st.nextToken()));
        }
    }

    double getRating() {
        return this.rating;
    }

    double[][] getPrices() {
        return prices;
    }

    void setPrices() {
        Price price = new Price();
        //this.prices = price.setWeeklyPrices();
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
            if (i == 0) {
            RoomTypes.add(room);}
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