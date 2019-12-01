package Sales;

import L4.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author:
 */

public class Price extends Hotel {

    private double[][] Prices;
    Scanner scanner = new Scanner(System.in);

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
     * Set the prices for the rooms
     * @param RoomTypes
     * @return prices for all rooms
     */
    public double[][] setPricesForAllRooms(ArrayList<Room> RoomTypes) {
        double[][] tempPrices = new double[7][RoomTypes.size()];
        for (int i = 0; i < RoomTypes.size();) {
            System.out.println("Input prices for the week for " + RoomTypes.get(i));
            for (int j = 0; j < 7; j++) {
                System.out.print(DayOfWeek.of(j));
                tempPrices[i][j] = Double.parseDouble(scanner.nextLine());
            }
        }
        return tempPrices;
    }

    /**
     * Get hotel prices
     * @return prices
     */
    public double[][] getPrices() {
        return Prices;
    }
}
