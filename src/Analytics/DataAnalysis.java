package Analytics;

import L4.Hotel;
import L4.Room;
import Reservation.Reservation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import static Reservation.ReservationSystem.readFromCSV;

/**
 * @author: Sean Lynch
 */

public class DataAnalysis {

    /**
     * Returns the revenue of the specified hotel for a specified, fixed period of time.
     * The revenue is calculated by getting the total cost of each stay in the hotel and adding them together.
     * @param dateFrom
     * @param dateTO
     * @param hotel
     * @return revenue
     */

    private static double revenueOfAFixedPeriod(LocalDate dateFrom, LocalDate dateTO, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTO) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
                i--;
            }
        }
        double price = 0;
        for (Reservation reservation : reservations) {
            price += reservation.getTotalCost();
        }
        return price;
    }

    /**
     * Returns the number of rooms occupied in the specified hotel over the specified period of time.
     * @param dateFrom
     * @param dateTo
     * @param hotel
     * @return number of rooms occupied
     */
    private static int numberOfRoomsOccupied(LocalDate dateFrom, LocalDate dateTo, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i  = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTo) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
                i--;
            }
        }
        int number = 0;
        for (Reservation reservation : reservations) {
            ArrayList<Room> rooms = reservation.getRooms();
            number += rooms.size();
        }
        return number;
    }

    /**
     * Returns the most commonly booked room in the specified hotel over the specified period of time.
     * @param dateFrom
     * @param dateTo
     * @param RoomTypes
     * @param hotel
     * @return most common type of room
     */
    private static Room mostCommonRoomType(LocalDate dateFrom, LocalDate dateTo, ArrayList<Room> RoomTypes, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i  = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTo) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
                i--;
            }
        }
        HashMap<Room, Integer> rooms = new HashMap<>();
        for (int i = 0; i < reservations.size(); i++) {
            for (int j = 0; j < reservations.get(i).getRooms().size(); j++) {
                for (int x = 0; x < RoomTypes.size(); x++) {
                    if (reservations.get(i).getRooms().get(j).type.equals(RoomTypes.get(x).type)) {
                        if (rooms.containsKey(RoomTypes.get(x))) {
                            int num = rooms.get(RoomTypes.get(x));
                            num++;
                            rooms.put(RoomTypes.get(x), num);
                        } else {
                            rooms.put(RoomTypes.get(x), 1);
                        }
                    }
                }
            }
        }
        return Collections.max(rooms.entrySet(),
                HashMap.Entry.comparingByValue()).getKey();
    }

    /**
     * Writes the data analysis to a CSV file, which includes revenue over a fixed period, the most common room type
     * and the number of rooms occupied.
     * @param from
     * @param to
     * @param hotel
     */
    public static void writeDataAnalyticsToCSV(LocalDate from, LocalDate to, Hotel hotel) {
        try {
            File file = new File("DataAnalytics.csv");
            if (!file.exists()) file.createNewFile();
            StringBuffer data = new StringBuffer();
            data.append("For fixed period from " + from + " to " + to + "\n");
            data.append("Revenue of a fixed period " + revenueOfAFixedPeriod(from, to, hotel) + "\n");
            data.append("Most common room types " + mostCommonRoomType(from, to, hotel.getRoomTypes(), hotel).type + "\n");
            data.append("Number of rooms occupied " + numberOfRoomsOccupied(from, to, hotel) + "\n");
            data.append("Brought to you by Best Solutions Ltd.");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(data.toString());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Data analytics file exception.");
        }
        System.out.println("Data Analysis complete.");
    }
}
