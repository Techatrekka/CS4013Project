package People;

public class HotelDeskAdmin extends Staff {

    HotelDeskAdmin() {
    }

    HotelDeskAdmin(String name, String email, String phone, double wages) {
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.wages = wages;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }

}