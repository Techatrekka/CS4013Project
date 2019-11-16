package People;

public class HotelDeskAdmin extends Staff {

    HotelDeskAdmin() {
    }

    HotelDeskAdmin(String name, String email, String phone, double wages, String address) {
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.wages = wages;
        super.address = address;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Address: " + getAddress() + "Phone: " + getPhone() + "Email: " + getEmail() + "Wage: " + this.wages;
    }

}