package Sales;

import L4.*;
import java.util.ArrayList;

public class Price {

    public static double[][] setWeeklyPrices(Room room, double[] NewPrices, double[][] OldPrices, ArrayList<Room> RoomTypes) {
        int row = 0;
        for (int i = 0; i < RoomTypes.size(); i++) {
            if (room.toString().equals(RoomTypes.get(i).toString())) {
                row = i;
            }
        }
        for (int i = 0; i < NewPrices.length; i++) {
            OldPrices[row][i] = NewPrices[i];
        }
        return OldPrices;
    }
}
