package People;

/**
 * @author: Michele Cavaliere
 */

public class Customer extends Person {

    public Customer() {}

    /**
     * Create a customer object
     * @param name
     * @param email
     * @param phone
     */
    public Customer(String name, String email, String phone) {
        super.name = name;
        super.email = email;
        super.phone = phone;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "Email: " + getEmail() + "Phone: " + getPhone();
    }
}
