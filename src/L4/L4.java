package L4;

import java.util.ArrayList;

public class L4 {
    ArrayList<Hotel> L4 = new ArrayList<>();
    int rating;

    void setRating(int rating) {
        this.rating = rating;
    }

    boolean addHotel(String name, String location, double rating, int noOfRooms) {
        Hotel h = new Hotel(name, location, rating, noOfRooms);
        L4.add(h);
        return true;
    }

    boolean removeHotel(int index) {
        L4.remove(index);
        return true;
    }
}