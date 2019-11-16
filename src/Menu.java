import java.time.LocalDate;
import java.util.Scanner;
import People.Customer;
import Reservation.Reservations;

import static Reservation.ReservationSystem.readReservation;

public class Menu {
    
    LocalDate dateToday;
    Scanner scanner = new Scanner(System.in);
    Reservations reservations = new Reservations();

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("C)ustomer S)taff Q)uit");
            String choice = scanner.next();
            if (choice.equals("C")) {
                //getOptions();
                System.out.println("Please input Name, email, phone, address");
                String[] info = new String[4];
                info[0] = scanner.next();
                info[1] = scanner.next();
                info[2] = scanner.next();
                info[3] = scanner.next();
                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                System.out.println("Would you like to make a Reservation or Cancellation");
                Object option = getOptions(new String[]{"A) Reservation", "B) Cancellation"});
                if (option.toString().equals("A"))
                {

                }
                else {
                    System.out.println("Enter Reservation Number: ");
                    option = scanner.next();
                    if ((readReservation(option.toString(), "Reservations.csv") != null)) {
                        Reservations re =  readReservation(option.toString(), "Reservations.csv");
                        reservations.deleteReservations(re);
                        System.out.println("Deleted");
                    }
                }
            }
            else if (choice.equals("S")) {

            }
            else if (choice.equals("Q")) {
                run = false;
            }
            else {
                System.out.println("Choice not recognised, choose again!");
            }
        }

    }
    
	private Object getOptions(Object options[])
	{
		if (options.length == 0)
		{
			return null;
		}
		
		int n = -1;
		while (0 > n && n >= options.length)
		{
			char c = 'A';
			for (Object option : options)
			{
				System.out.println(c + ") " + option); // Choice requires toString method 
				c++;
			}
			String input = scanner.nextLine();
			n = input.toUpperCase().charAt(0) - 'A';
		}
		return options[n];
	}
}
