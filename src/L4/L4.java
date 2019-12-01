package L4;

import java.io.*;
import java.util.ArrayList;

/**
 * @author:
 */

public class L4 {
    ArrayList<Hotel> L4 = new ArrayList<>();

    /**
     * Add a hotel to the arraylist L4.
     * @param name
     * @param location
     * @param rating
     * @param roomDetails
     * @param numOfRooms
     */
    public void addHotel(String name, String location, String rating, String[] roomDetails, int[] numOfRooms) {
        Hotel h = new Hotel(name, location, rating, roomDetails, numOfRooms);
        L4.add(h);
    }

    /**
     * Removes a hotel from the arraylist L4.
     * @param index
     */
    public void removeHotel(int index) {
        L4.remove(index);
    }

    /**
     * Returns a list of hotels in the l4 chain.
     * @return array of hotels
     */
    public String[] hotelList() {
        String[] temp = new String[L4.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = L4.get(i).getName();
        }
        return temp;
    }

    /**
     * @return arraylist
     */
    public ArrayList<Hotel> getL4() {
        return L4;
    }

    /**
     * Writes the details of the specified hotel to a CSV file
     * @param filename
     * @param hotels
     */
    public static void writeHotelDetailsToCSV(String filename, ArrayList<Hotel> hotels) {
        try {
            File file = new File(filename);
            StringBuffer data = new StringBuffer("");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }catch (IOException e) {
                System.out.println("File creation error");
            }
            PrintWriter printWriter = new PrintWriter(file);
            data.append("Hotel Name, Hotel type, Room type, Number of Rooms, Occupancy-min, Occupancy-max, Rates\n");
            data.append(",,,,Adult-Child,Adult-Child,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday\n");
            for (Hotel hotel : hotels) {
                data.append(hotel.getName()).append(",").append(hotel.getRating()).append(",");
                for (int j = 0; j < hotel.prices.length; j++) {
                    if (j != 0) data.append(",,");
                    data.append(hotel.getRoomTypes().get(j)).append(",").append(hotel.noOfRooms[j])
                            .append(",1-0,").append(hotel.getRoomTypes().get(j).occupancy).append(",")
                            .append(hotel.prices[j][0]).append(",").append(hotel.prices[j][1]).append(",")
                            .append(hotel.prices[j][2]).append(",").append(hotel.prices[j][3]).append(",")
                            .append(hotel.prices[j][4]).append(",").append(hotel.prices[j][5]).append(",")
                            .append(hotel.prices[j][6]).append("\n");
                }
            }
            printWriter.write(data.toString());
            printWriter.close();
        }catch (IOException e) {
            System.out.println("Error in writing to csv");
        }
    }
}
