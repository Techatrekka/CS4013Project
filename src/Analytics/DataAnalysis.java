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

import static Reservation.ReservationSystem.readFromCSV;

public class DataAnalysis {

   /* public static double monthlyRevenue(LocalDate date, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i = 0; i < reservations.size(); i++) {
            if (!reservations.get(i).getCheckInDate().getMonth().equals(date.getMonth())) {
                reservations.remove(i);
            }
        }
        double price = 0;
        for (int i = 0; i < reservations.size(); i++) {
            price += reservations.get(i).getTotalCost();
        }
        return price;
    }*/

    public static double revenueOfAFixedPeriod(LocalDate dateFrom, LocalDate dateTO, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTO) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
            }
        }
        double price = 0;
        for (int i = 0; i < reservations.size(); i++) {
            price += reservations.get(i).getTotalCost();
        }
        return price;
    }

    //needs to be reviewed, it doesn't answer the question
    public static int numberOfRoomsOccupied(LocalDate dateFrom, LocalDate dateTo, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i  = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTo) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
            }
        }
        int number = 0;
        for (int i = 0; i < reservations.size(); i++) {
            ArrayList<Room> rooms = reservations.get(i).getRooms();
            for (int j = 0; j < rooms.size(); j++) {
                number += Integer.parseInt(rooms.get(j).occupancy);
            }
        }
        return number;
    }

    public static Room mostCommonRoomType(LocalDate dateFrom, LocalDate dateTo, ArrayList<Room> RoomTypes, Hotel hotel) {
        String name = hotel.getName() + "Stays.csv";
        ArrayList<Reservation> reservations = readFromCSV(name);
        for (int i  = 0; i < reservations.size(); i++) {
            if (!(reservations.get(i).getCheckInDate().isBefore(dateTo) &&
                    reservations.get(i).getCheckInDate().isAfter(dateFrom))) {
                reservations.remove(i);
            }
        }
        HashMap<String, Integer> rooms = new HashMap<>();
        String[] types = new String[RoomTypes.size()];
        for (int i = 0; i < RoomTypes.size(); i++) {
            types[i] = RoomTypes.get(i).getName();
        }
        for (int i = 0; i < reservations.size(); i++) {
            ArrayList<Room> Room = reservations.get(i).getRooms();
            for (int j = 0; j < Room.size(); j++) {
                if (rooms.get(Room.get(j).type) != null) {
                    int num = rooms.get(Room.get(j).type);
                    num ++;
                    rooms.put(Room.get(j).type, num);
                }
                else {
                    rooms.put(Room.get(j).type, 0);
                    String[] temp = new String[types.length+1];
                    if (types.length == 0) {
                        for (int x = 0; x < types.length; x++) {
                            types[x] = temp[x];
                        }
                    }
                    temp[types.length] = Room.get(j).type;
                }
            }
        }
        String highest = types[0];
        for (String room: types) {
            if (rooms.get(highest) < rooms.get(room)) {
                highest = room;
            }
        }
        for (int i = 0; i < RoomTypes.size(); i++) {
            if (RoomTypes.get(i).type.equals(highest)) {
                return RoomTypes.get(i);
            }
        }
        return null;
    }

    public static void writeDataAnalyticsToCSV(LocalDate from, LocalDate to, Hotel hotel) {
        try {
            File file = new File("DataAnalytics.csv");
            if (!file.exists()) file.createNewFile();
            StringBuffer data = new StringBuffer("");
            data.append("For fixed period from " + from + " to " + to + "\n");
            data.append("Revenue of a fixed period " + revenueOfAFixedPeriod(from, to, hotel) + "\n");
            //data.append("Most common room types " + mostCommonRoomType(from, to, hotel.getRoomTypes(), hotel) + "\n");
            data.append("Number of rooms occupied " + numberOfRoomsOccupied(from, to, hotel) + "\n");
            data.append("Brought to you by Best Solutions Ltd.");
            PrintWriter printyBoi = new PrintWriter(file);
            printyBoi.write(data.toString());
            printyBoi.close();
        } catch (IOException e) {
            System.out.println("Data analytics file exception break");
        } finally {
            System.out.println("Heya");
        }

    }
}
