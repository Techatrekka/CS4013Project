package L4;

import java.util.ArrayList;

public class L4 {
    ArrayList<Hotel> L4 = new ArrayList<>();

    public boolean addHotel(String name, String location, int rating, String[] roomDetails, int[] numOfRooms) {
        Hotel h = new Hotel(name, location, rating, roomDetails, numOfRooms);
        L4.add(h);
        return true;
    }

    public boolean removeHotel(int index) {
        L4.remove(index);
        return true;
    }
}