package Reservation;

import java.util.ArrayList;

public class Stays extends Reservations{

    Stays(Reservations reservations) {
        deleteReservations(reservations);
        ArrayList<Reservations> stays = readFromCSV("Stays.csv");
        stays.add(reservations);
        writeToCSV("Stays.csv",stays,true);
    }
}
