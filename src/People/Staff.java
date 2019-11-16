package People;

import Reservation.Reservations;

import java.time.LocalDate;

public class Staff extends Person {

    protected double wages;

    void checkIn(Reservations reservation, LocalDate date) {
        reservation.setCheckIn(date);
    }

    void checkOut(Reservations reservation, LocalDate date) {
        reservation.setCheckOut(date);
    }

    String checkAvailability(LocalDate date) {
        return null;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Address: " + getAddress() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }
}