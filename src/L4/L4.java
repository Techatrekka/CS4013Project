package L4;

import java.io.*;
import java.util.ArrayList;

public class L4 {
    ArrayList<Hotel> L4 = new ArrayList<>();
    File L4Chain = new File("L4.csv");

    public boolean addHotel(String name, String location, int rating, String[] roomDetails, int[] numOfRooms) {
        Hotel h = new Hotel(name, location, rating, roomDetails, numOfRooms);
        L4.add(h);
        return true;
    }

    public boolean removeHotel(int index) {
        L4.remove(index);
        return true;
    }

    public String[] hotelList() {
        String[] temp = new String[L4.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = L4.get(i).getName();
        }
        return temp;
    }

    public ArrayList<Hotel> getL4() {
        return L4;
    }

    public static void writeToCSV(String fileName, ArrayList<Hotel> hotels, boolean overwrite) {
        try {
            File file = new File(fileName);
            StringBuffer data = new StringBuffer("");
            PrintWriter printWriter = null;
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }catch (IOException e) {
                System.out.println("File creation error");
            }
            if (file.exists() && !overwrite) {
                printWriter = new PrintWriter(new FileOutputStream(file, true));
            }
            data.append("Hotel Name, Location, No. of Rooms, Rating\n");
            // write data (, == new column && \n == new row)
            for (Hotel hotel: hotels)
            {
                String name = hotel.getName();
                String location = hotel.getLocation();
               // String numRooms = String.valueOf(hotel.getNoOfRooms());
                String rating = String.valueOf(hotel.getRating());
               // data.append(name + "," + location + "," + numRooms + "," + rating + "," + hotel.getRoomTypes());
            }
            printWriter.write(data.toString());
            printWriter.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static void writeHotelDetailsToCSV(String filename, ArrayList<Hotel> hotels) {
        try {
            File file = new File(filename);
            StringBuffer data = new StringBuffer("");
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(file);
            data.append("Hotel Name, Hotel type, Room type, Number of Rooms, Occupancy-min, Occupancy-max, Rates\n");
            data.append(",,,,Adult-Child,Adult-Child,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday\n");
            for (int i = 0; i < hotels.size(); i++) {
                Hotel hotel = hotels.get(i);
                data.append(hotels.get(i).getName()).append(",").append(hotels.get(i).getRating()).append(",")
                        .append(hotel.getRoomTypes().get(0)).append(",").append(hotel.noOfRooms[0])
                        .append(",1-0,").append(hotel.getRoomTypes().get(0).occupancy).append(",")
                        .append(hotel.prices[i][0]).append(",").append(hotel.prices[i][1]).append(",")
                        .append(hotel.prices[i][2]).append(",").append(hotel.prices[i][3]).append(",")
                        .append(hotel.prices[i][4]).append(",").append(hotel.prices[i][5]).append(",")
                        .append(hotel.prices[i][6]);
                for (int j = 1; j < hotel.RoomTypes.size(); j++) {
                    data.append(",," + hotel.getRoomTypes().get(j)+ "," + hotel.noOfRooms[j]+",1-0,"+
                            hotel.getRoomTypes().get(j).occupancy +"," +hotel.prices[i][0] + "," +hotel.prices[i][1]+ "," +
                            hotel.prices[i][2] +"," + hotel.prices[i][3] + "," +hotel.prices[i][4] + "," +
                            hotel.prices[i][5] + "," + hotel.prices[i][6]);
                }
            }
            printWriter.write(data.toString());
            printWriter.close();
        }catch (IOException e) {
            System.out.println("It broke");
        }
    }
}
