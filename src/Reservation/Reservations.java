package Reservation;

import L4.Room;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservations extends ReservationSystem {

    protected String reservationName, number, email, reservationId;
    protected int duration;
    protected LocalDate checkIn, checkOut;
    protected Double totalCost, deposit;
    protected boolean advancedPurchase;
    protected ArrayList<Room> rooms = new ArrayList<>();

    public Reservations() {}

    public Reservations(String reservationId, String reservationName, String number, String email,
                        LocalDate checkIn, int duration, ArrayList<Room> rooms,
                        double totalCost, boolean advancedPurchase) {
        this.reservationName = reservationName;
        this.reservationId = reservationId;
        this.number = number;
        this.email = email;
        this.duration = duration;
        this.advancedPurchase = advancedPurchase;
        this.checkIn = checkIn;
        this.rooms = rooms;
        this.totalCost = totalCost;
        this.deposit = totalCost * .15;
    }

    public String getReservationId() {
        return reservationId;
    }

    String getReservationName() {
        return reservationName;
    }

    String getPhoneNumber()
    {
        return number;
    }

    public String getNextReservationId() {
        return "" + System.currentTimeMillis() / 1000;
    }

    public void setCheckIn(LocalDate checkIn){
        this.checkIn = checkIn;
    }

    public int getDuration() {
        return duration;
    }

    public void setCheckOut(LocalDate checkOut){
        this.checkOut = checkOut;
    }

    String getReservationEmail()
    {
        return this.email;
    }

    public ArrayList<Room> getRooms() { return rooms; }

    boolean  getAdvancedPurchase(){
        return this.advancedPurchase;
    }

    void setAdvancedPurchase(boolean set)
    {
        this.advancedPurchase = set;
    }

    public LocalDate getCheckInDate()
    {
        return checkIn;
    }

    LocalDate getCheckOutDate()
    {
        return checkIn.plusDays(duration);
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double cost)
    {
        totalCost = cost;
    }

    double getDeposit()
    {
        return deposit;
    }

    public String toCSV()
    {
        return reservationId + "," + reservationName + "," + number + "," + email + "," + checkIn + "," + duration + "," + rooms.size() + "," + getRoomsAsString() + "," + totalCost + "," + deposit + "," + advancedPurchase + "\n";
    }

    protected String getRoomsAsString()
    {
        String temp = "";
        for (Room room : rooms)
        {
            temp += room.toCSV() + "/";
        }
        return temp;
    }

    @Override
    public String toString() {
        return reservationId + " " + reservationName + " " +
                checkIn + " " + checkOut + " " +
                rooms.size() + " " + totalCost;
    }
}