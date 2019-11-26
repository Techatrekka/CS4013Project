package L4;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel extends L4 {

    private String name, location;
    double rating;
    int[] noOfRooms;
    ArrayList<Room> RoomTypes = new ArrayList<Room>();
    double[][] prices = new double[RoomTypes.size()][7];

    public Hotel(){}

    Hotel(String name, String location, int rating, String[] RoomDetails, int[] noOfRooms){
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.noOfRooms = noOfRooms;
        createFiles();
    }

    private void createFiles() {
        try {
            File reservations = new File(name + "Reservations.csv");
            reservations.createNewFile();
            File cancellations = new File(name + "Cancellations.csv");
            cancellations.createNewFile();
            File stays = new File(name + "Stays.csv");
            stays.createNewFile();
        }catch (IOException e) {
            System.out.println("It done broke");
        }
    }

    double getRating() {
        return this.rating;
    }

    public String getName(){
        return this.name;
    }

    String getLocation(){
        return this.location;
    }

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
                "Number of Rooms: ";
    }
}
