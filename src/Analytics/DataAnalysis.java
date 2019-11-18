package Analytics;

import L4.Room;
import Reservation.Reservations;

import java.time.LocalDate;
import java.util.ArrayList;

import static Reservation.ReservationSystem.readFromCSV;

public class DataAnalysis {

    public static double monthlyRevenue(LocalDate date) {
        ArrayList<Reservations> reservations = readFromCSV("Stays.csv");
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
    }

    public static double revenueOfAFixedPeriod(LocalDate dateFrom, LocalDate dateTO) {
        ArrayList<Reservations> reservations = readFromCSV("Stays.csv");
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

    public static int numberOfRoomsOccupied(LocalDate dateFrom, LocalDate dateTo) {
        ArrayList<Reservations> reservations = readFromCSV("stays.csv");
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
                number += rooms.get(j).occupancy;
            }
        }
        return number;
    }
}
