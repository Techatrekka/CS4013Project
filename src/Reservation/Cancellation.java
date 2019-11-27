package Reservation;

import L4.Room;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cancellation extends Reservation
{
    private LocalDate cancellationDate;

    public Cancellation(String reservationId, String reservationName, String number, String email, LocalDate checkIn, LocalDate cancellationDate, ArrayList<Room> rooms, double totalCost, boolean advancedPurchase)
    {
        super(reservationId, reservationName, number, email, checkIn, 0, rooms, totalCost, advancedPurchase);
        this.cancellationDate = cancellationDate;
    }

    public Cancellation(LocalDate cancellationDate, Reservation reservations,String hotelChosen)
    {
        this.cancellationDate = cancellationDate;
        deleteReservations(reservations,hotelChosen+"Cancellations.csv");
        ArrayList<Reservation> cancels = readFromCSV("Cancellations.csv");
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