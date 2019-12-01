package Reservation;

import L4.Room;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author:
 */

public class Cancellation extends Reservation
{
    private LocalDate cancellationDate;

    /**
     * Create a cancellation object
     * @param reservationId
     * @param reservationName
     * @param number
     * @param email
     * @param checkIn
     * @param cancellationDate
     * @param rooms
     * @param totalCost
     * @param advancedPurchase
     */
    public Cancellation(String reservationId, String reservationName, String number, String email, LocalDate checkIn,
                        LocalDate cancellationDate, ArrayList<Room> rooms, double totalCost, boolean advancedPurchase)
    {
        super(reservationId, reservationName, number, email, checkIn, 0, rooms, totalCost, advancedPurchase);
        this.cancellationDate = cancellationDate;
    }

    /**
     * Create a cancellation object
     * @param reservation
     * @param hotelChosen
     */
    public Cancellation(Reservation reservation,String hotelChosen)
    {
		ArrayList<Reservation> cancels = readFromCSV(hotelChosen+"Cancellations.csv");
		ArrayList<Reservation> reservations = readFromCSV(hotelChosen+"Reservations.csv");
		reservations.remove(reservation); // idk if this will work we might need a .equals method in the reservations class as remove goes by == i believe
        cancels.add(reservation);
		writeToCSV(hotelChosen+"Cancellations.csv",cancels,true);
		writeToCSV(hotelChosen+"Reservations.csv", reservations, true); // needs to be left as "Reservations" so there is only one file for reservations for the hotel
		
    }

    /**
     * Get the cancellation date
     * @return the cancellation date
     */
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
