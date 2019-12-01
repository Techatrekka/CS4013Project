package L4;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author:
 */

public class Hotel extends L4 {

    private String name, location;
    private String rating;
    int[] noOfRooms;
    private ArrayList<Room> RoomTypes = new ArrayList<Room>();
    double[][] prices;

    public Hotel(){}

    /**
     * Creates a hotel object.
     * @param name
     * @param location
     * @param rating
     * @param RoomDetails
     * @param noOfRooms
     */
    Hotel(String name, String location, String rating, String[] RoomDetails, int[] noOfRooms){
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.noOfRooms = noOfRooms;
        createRooms(RoomDetails,noOfRooms);
        createFiles();
        prices = new double[noOfRooms.length][7];
        getPriceFromCSV();
    }

    /**
     * Creates the reservations, cancellations and stays CSV files for the system.
     */
    private void createFiles() {
        try {
            File reservations = new File(name + "Reservations.csv");
            reservations.createNewFile();
            File cancellations = new File(name + "Cancellations.csv");
            cancellations.createNewFile();
            File stays = new File(name + "Stays.csv");
            stays.createNewFile();
        }catch (IOException e) {
            System.out.println("File creation error");
        }
    }

    /**
     *
     * @return an array of prices
     */
    public double[][] getPrices() {
        return prices;
    }

    /**
     * Reads the prices from the CSV file.
     */
    public void getPriceFromCSV() {
        try {
            double[][] prices = this.prices;
            String temp = "";
            File file = new File("L4.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                temp = scanner.nextLine();
                if (temp.contains(name)) {
                    for (int i = 1; i < getRoomTypes().size(); i++) {
                        temp += scanner.nextLine();
                    }
                    String[] info = temp.split(",");
                    int x = 0;
                    String[] price = new String[7*getRoomTypes().size()];
                    for (String s : info) {
                        if (s.contains(".")) {
                            price[x] = s;
                            x++;
                        }
                    }
                    int counter = 0;
                    for (int i = 0; i < price.length/7; i++) {
                        for (int j = 0; j < 7; j++) {
                            prices[i][j] = Double.parseDouble(price[counter]);
                            counter++;
                        }
                    }
                }
            }
        }catch (IOException e) {
            System.out.println("Error in getting prices");
        }
    }

    /**
     * @return hotel rating
     */
    String getRating() {
        return this.rating;
    }

    /**
     * @return hotel name
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return hotel location
     */
    String getLocation(){
        return this.location;
    }

    /**
     * Creates rooms in the hotel
     * @param RoomDetails
     * @param numOfRooms
     */
    void createRooms(String[] RoomDetails, int[] numOfRooms) {
        for (int i = 0; i < numOfRooms.length; i++) {
            for (int j = 0; j < numOfRooms[i]; j++) {
                String[] dataFields = RoomDetails[i].split(",");
                Room room = new Room(dataFields[0], dataFields[1], Boolean.parseBoolean(dataFields[2]));
                if (j == 0) {
                    RoomTypes.add(room);
                }
            }
        }
    }

    /**
     * @return room types
     */
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
