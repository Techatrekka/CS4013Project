package Reservation;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cancellations extends Reservations {

    private LocalDate cancellationDate;

    public Cancellations(LocalDate cancellationDate, Reservations reservations,String hotelChosen) {
        this.cancellationDate = cancellationDate;
        deleteReservations(reservations,hotelChosen+"Cancellations.csv");
        ArrayList<Reservations> cancels = readFromCSV("Cancellations.csv");
        cancels.add(reservations);
        writeToCSV("Cancellations.csv",cancels,true);
    }
}