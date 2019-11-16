package People;

import L4.Room;
import Reservation.Cancellations;
import Reservation.Reservations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Person {

    protected String name;
    protected String address;
    protected String phone;
    protected String email;

    Person() {

    }

    void makeReservation(String reservationName, String number, String email,
                         LocalDate checkIn, int numOfNights, HashMap<ArrayList<Room>,
                            ArrayList<Double>> rooms, boolean advancedPurchase) {
        Reservations reservations = new Reservations();
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

