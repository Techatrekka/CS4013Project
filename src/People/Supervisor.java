package People;

import Reservation.*;

public class Supervisor extends Staff {

    public Supervisor(){}


    Supervisor(String name, String email, String phone, double wages, String address) {
        super.name = name;
        super.address = address;
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

    public void giveDiscount(Reservation reservation, double discount) {
        double cost = reservation.getTotalCost();
        double newPrice = cost - (cost * discount);
        reservation.setTotalCost(newPrice);
    }

    void requestDataAnalysis() {
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Address: " + getAddress() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }
}