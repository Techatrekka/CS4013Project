package People;

/**
 * @author:
 */


public class HotelDeskAdmin extends Staff {

    HotelDeskAdmin() {
    }

    /**
     * Create a hotel desk admin object
     * @param name
     * @param email
     * @param phone
     * @param wages
     */
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
