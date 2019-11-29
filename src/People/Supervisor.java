package People;

import Reservation.*;

import java.util.ArrayList;

public class Supervisor extends Staff {

    public Supervisor(){}


    Supervisor(String name, String email, String phone, double wages) {
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.wages = wages;
    }

    private void setWages(double wages) {
        this.wages = wages;
    }

    private double getWages() {
        return this.wages;
    }

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

    void requestDataAnalysis() {
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }
}
