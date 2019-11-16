import java.time.LocalDate;
import java.util.Scanner;
import People.Customer;

public class Menu {
    
    LocalDate dateToday;
    Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("C)ustomer S)taff Q)uit");
            String choice = scanner.next();
            if (choice.equals("C")) {
                //getOptions();
                choice = scanner.next();
                Customer customer = new Customer();
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

    void details() {
        boolean done = false;
        String[] info = new String[6];
        while (!done) {
            System.out.println("Please input Name");
            info[0] = scanner.next();
            System.out.println("Please input checkIn date");
            info[1] = scanner.next();
            System.out.println("Please input checkOut date");
            info[2] = scanner.next();
            System.out.println("Please select number of rooms");
            info[3] = scanner.next();
            for (int i = 0; i < Integer.parseInt(info[3]); i++) {
                System.out.println("Please select type of room(s)");
                //getOptions();
            }
            System.out.println("is the info correct yes/no");
            String choice = scanner.next();
            if (choice.equals("yes")) {
                
            } else {
                done = true;
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
