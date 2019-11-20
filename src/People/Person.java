package People;

import L4.Room;
import Reservation.Cancellations;
import Reservation.ReservationSystem;
import Reservation.Reservations;
import Sales.Billing;

import java.time.LocalDate;
import java.util.ArrayList;

import static Reservation.ReservationSystem.writeToCSV;

public class Person {

    protected String name;
    protected String address;
    protected String phone;
    protected String email;

    Person() {

    }

    public void makeReservation(LocalDate checkIn, int numOfNights, ArrayList<Room>
                                rooms, boolean advancedPurchase, ArrayList<Room> RoomTypes) {
        Billing bill = new Billing();
        double total = bill.calculatePrice(advancedPurchase, rooms,
                numOfNights, checkIn, RoomTypes);
        Reservations re = new Reservations();
        String reservationID = re.getNextReservationId();
        Reservations reservations = new Reservations(reservationID, name, phone, email,
        checkIn,rooms,numOfNights,total,advancedPurchase);
        ArrayList<Reservations> reservations1 = ReservationSystem.readFromCSV("Reservations.csv");
        reservations1.add(reservations);
        writeToCSV("Reservations.csv",reservations1,true);
    }

    void makeCancellation(Reservations reservation) {
        Cancellations cancel = new Cancellations(reservation.getCheckInDate(),reservation);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Address: " + getAddress() + "Phone: " + getPhone() + "Email: " + getEmail();
    }

}

