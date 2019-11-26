package Reservation;

import L4.Room;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cancellations extends Reservations
{
    private LocalDate cancellationDate;

    public Cancellations(String reservationId, String reservationName, String number, String email, LocalDate checkIn, LocalDate cancellationDate, ArrayList<Room> rooms, double totalCost, boolean advancedPurchase)
    {
        super(reservationId, reservationName, number, email, checkIn, 0, rooms, totalCost, advancedPurchase);
        this.cancellationDate = cancellationDate;
    }

    public Cancellations(LocalDate cancellationDate, Reservations reservations,String hotelChosen)
    {
        this.cancellationDate = cancellationDate;
        deleteReservations(reservations,hotelChosen+"Cancellations.csv");
        ArrayList<Reservations> cancels = readFromCSV("Cancellations.csv");
        cancels.add(reservations);
        writeToCSV("Cancellations.csv",cancels,true);
    }

    public LocalDate getCancellationDate()
    {
        return cancellationDate;
    }

    @Override
    public String toCSV()
    {
        return reservationId + "," + reservationName + "," + number + "," + email + "," + checkIn + "," + cancellationDate + "," + rooms.size() + "," + getRoomsAsString() + "," + totalCost + "," + deposit + "," + advancedPurchase + "\n";
    }
}