package People;

public class Customer extends Person {

    protected String reservationID;

    public Customer() {}

    public Customer(String name, String email, String phone, String address) {
        super.address = address;
        super.name = name;
        super.email = email;
        super.phone = phone;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Address: " + getAddress() + "Email: " + getEmail() + "Phone: " + getPhone();
    }
}