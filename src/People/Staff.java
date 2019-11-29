package People;

import Reservation.Reservation;

import java.time.LocalDate;

public class Staff extends Person {

    protected double wages;

    void checkIn(Reservation reservation, LocalDate date) {
        reservation.setCheckIn(date);
    }

    void checkOut(Reservation reservation, LocalDate date) {
        reservation.setCheckOut(date);
    }

    String checkAvailability(LocalDate date) {
        return null;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }
}