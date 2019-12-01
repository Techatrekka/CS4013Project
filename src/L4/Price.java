package L4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Sean Lynch
 */

public class Price extends Hotel {

    private double[][] Prices;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Set the prices for the rooms
     * @param room
     * @param NewPrices
     * @param OldPrices
     * @param RoomTypes
     * @return room prices
     */
    public double[][] setWeeklyPricesForRoom(Room room, double[] NewPrices,
                                      double[][] OldPrices, ArrayList<Room> RoomTypes) {
        int row = 0;
        for (int i = 0; i < RoomTypes.size(); i++) {
            if (room.toString().equals(RoomTypes.get(i).toString())) {
                row = i;
            }
        }
        System.arraycopy(NewPrices, 0, OldPrices[row], 0, NewPrices.length);
        this.Prices = OldPrices;
        return OldPrices;
    }

    /**
     * Get hotel prices
     * @return prices
     */
    public double[][] getPrices() {
        return Prices;
    }
}
