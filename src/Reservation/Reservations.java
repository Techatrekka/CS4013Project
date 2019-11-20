package Reservation;

import L4.Room;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservations extends ReservationSystem {

    private String reservationName, number, email, reservationId;
    private int duration;
    private LocalDate checkIn, checkOut;
    private Double totalCost, deposit;
    private boolean advancedPurchase;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Reservations() {}

    public Reservations(String reservationId, String reservationName, String number, String email,
                        LocalDate checkIn, ArrayList<Room> rooms, int duration,
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

    String getRoomsAsString() {
        String temp = "";
        for (int i = 0; i < rooms.size(); i++) {
            temp += rooms.get(i).toString();
            temp += " ";
        }
        return temp;
    }

    boolean  getAdvancedPurchase(){
        return this.advancedPurchase;
    }

    void setAdvancedPurchase(boolean set){
        this.advancedPurchase = set;
    }

    public LocalDate getCheckInDate() {
        return checkIn;
    }

    LocalDate getCheckOutDate() {
        return checkOut;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double cost) {
        totalCost = cost;
    }

    double getDeposit() {
        return deposit;
    }

    @Override
    public String toString() {
        return reservationId + " " + reservationName + " " +
                checkIn + " " + checkOut + " " +
                rooms.size() + " " + totalCost;
    }
}
