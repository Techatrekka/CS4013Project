package Sales;

import L4.Hotel;
import L4.Room;
import java.time.LocalDate;
import java.util.ArrayList;

public class Billing extends Price {

    public double calculatePrice(Hotel hotel,
                                 ArrayList<Room> rooms,
                                 int stayDuration,
                                 LocalDate checkIn, ArrayList<Room> roomTypes, boolean AP) {
        double price = 0;
        int day = checkIn.getDayOfWeek().getValue() - 1;
        double[][] prices = hotel.getPrices();
        int row = 0;
        for (int numOfRooms = 0; numOfRooms < rooms.size(); numOfRooms++) {
            for (int i = 0; i < roomTypes.size(); i++) {
                if (rooms.get(numOfRooms).equals(roomTypes.get(i))) {
                    row = i;
                }
            }
            for (int j = 0; j < stayDuration; j++) {
                if (day > 6) {
                    day = 0;
                }
                price += prices[row][day];
                day++;
            }
        }
        if (AP == true) {
            price *= .95;
        }
        return price;
    }
}