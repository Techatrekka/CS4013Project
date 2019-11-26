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
                String numRooms = String.valueOf(hotel.getNoOfRooms());
                String rating = String.valueOf(hotel.getRating());
                data.append(name + "," + location + "," + numRooms + "," + rating + "," + hotel.getRoomTypes());
            }
            printWriter.write(data.toString());
            printWriter.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
