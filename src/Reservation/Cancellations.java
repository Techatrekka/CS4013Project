package Reservation;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cancellations extends Reservations {

    private LocalDate cancellationDate;

    public Cancellations(LocalDate cancellationDate, Reservations reservations) {
        deleteReservations(reservations);
        ArrayList<Reservations> cancels = readFromCSV("Cancellations.csv");
        cancels.add(reservations);
        writeToCSV("Cancellations.csv",cancels,true);
    }
}