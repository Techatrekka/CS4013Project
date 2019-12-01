package L4;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author:
 */


public class Billing extends Price {

    /**
     * Calculate the price of the hotel stay
     * @param hotel
     * @param rooms
     * @param stayDuration
     * @param checkIn
     * @param roomTypes
     * @param AP
     * @return price of the stay
     */
    public double calculatePrice(Hotel hotel,
                                 ArrayList<Room> rooms,
                                 int stayDuration,
                                 LocalDate checkIn, ArrayList<Room> roomTypes, boolean AP) {
        double price = 0;
        int day = checkIn.getDayOfWeek().getValue() - 1;
        double[][] prices = hotel.getPrices();
        int row = 0;
        for (Room room : rooms) {
            for (int i = 0; i < roomTypes.size(); i++) {
                if (room.equals(roomTypes.get(i))) {
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
        if (AP) {
            price *= .95;
        }
        return price;
    }
}
