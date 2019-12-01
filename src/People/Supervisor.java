package People;

import Reservation.*;

import java.util.ArrayList;

/**
 * @author:
 */

public class Supervisor extends Staff {

    public Supervisor(){}

    /**
     * Create a supervisor object
     * @param name
     * @param email
     * @param phone
     * @param wages
     */
    Supervisor(String name, String email, String phone, double wages) {
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.wages = wages;
    }

    /**
     * Set the wages of the supervisor
     * @param wages
     */
    private void setWages(double wages) {
        this.wages = wages;
    }

    /**
     * Get the wages of the supervisor
     * @return wages
     */
    private double getWages() {
        return this.wages;
    }

    /**
     * Give a discount on a specified reservation in a specified hotel
     * @param reservation
     * @param discount
     * @param hotel
     */
    public void giveDiscount(Reservation reservation, double discount,String hotel) {
        double cost = reservation.getTotalCost();
        double newPrice = cost * (1 - (discount/ 100));
        ArrayList<Reservation> r1 = ReservationSystem.readFromCSV(hotel+"Reservations.csv");
        for (int i = 0; i < r1.size(); i ++) {
            if (reservation.getReservationId().equals(r1.get(i).getReservationId())) {
                r1.get(i).setTotalCost(newPrice);
            }
        }
        Reservation.writeToCSV(hotel + "Reservations.csv",r1,true);
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }
}
