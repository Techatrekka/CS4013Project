package Reservation;

import L4.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Reservations extends ReservationSystem {

    private String reservationName, number, email, reservationId, numOfRooms;
    private LocalDate checkIn, checkOut;
    private Double totalCost, deposit;
    private boolean advancedPurchase;
    private HashMap<ArrayList<Room>, ArrayList<Double>> Rooms;
    public static ArrayList<Reservations> resList = new ArrayList<>();

    public Reservations() {}

    public Reservations(String reservationId, String reservationName, String number, String email,
                        LocalDate checkIn, HashMap<ArrayList<Room>, ArrayList<Double>> Rooms,
                        double totalCost, boolean advancedPurchase) {
        this.reservationName = reservationName;
        this.reservationId = reservationId;
        this.number = number;
        this.email = email;
        this.advancedPurchase = advancedPurchase;
        this.checkIn = checkIn;
        this.Rooms = Rooms;
        this.totalCost = totalCost;
        this.deposit = totalCost * .15;
    }

    public String getReservationId() {
        return reservationId;
    }

    String getReservationName() {
        return reservationName;
    }

    String getReservationNumber()
    {
        return number;
    }

    public String getNextReservationId() {
        return "" + System.currentTimeMillis() / 1000;
    }

    public void setCheckIn(LocalDate checkIn){
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut){
        this.checkOut = checkOut;
    }

    String getReservationEmail()
    {
        return this.email;
    }

    HashMap<ArrayList<Room>, ArrayList<Double>> getRooms() { return Rooms; }

    boolean  getAdvancedPurchace(){
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

    String getNumOfRooms() {
        return numOfRooms;
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
                numOfRooms + " " + totalCost;
    }
}