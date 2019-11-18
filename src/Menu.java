import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import L4.Hotel;
import L4.Room;
import People.Customer;
import People.Supervisor;
import Reservation.Reservations;
import com.sun.java.util.jar.pack.Instruction;

import static Reservation.ReservationSystem.readReservation;

public class Menu {
    
    LocalDate dateToday;
    Scanner scanner = new Scanner(System.in);
    Reservations reservations = new Reservations();
    Hotel hotel = new Hotel();
    ArrayList<Room> rooms = new ArrayList<>();
    int numOfNights;

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("C)ustomer S)taff Q)uit");
            java.lang.String choice = scanner.next();
            if (choice.equals("C")) {
                //getOptions();
                //System.out.println("Please input Name, email, phone, address");
                String[] info = new String[4];
                info[0] = scanner.next();
                info[1] = scanner.next();
                info[2] = scanner.next();
                info[3] = scanner.next();
                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                System.out.println("Would you like to make a Reservation or Cancellation");
                String[] options = new String[]{"A) Reservation", "B) Cancellation"};
                Object option = getOptions(options);
                if (option.toString().equals("A"))
                {
                    boolean done = false;
                    while(!done) {
                        System.out.println("What room would you like");
                        option = getOptions(hotel.getRoomTypes().toArray());
                        for (int i = 0; i < hotel.getRoomTypes().size(); i++) {
                            if (option.equals(hotel.getRoomTypes().toString())) {
                                rooms.add(hotel.getRoomTypes().get(i));
                            }
                        }
                        System.out.println("Would you like to add another room? Y/N");
                        choice = scanner.next().toUpperCase();
                        if (!choice.equals("Y")) {
                            done = true;
                        }
                    }
                    System.out.println("When would you check in?");
                    LocalDate checkIn = LocalDate.parse(scanner.next());
                    System.out.println("How many nights are you saying?");
                    numOfNights = scanner.nextInt();
                    customer.makeReservation(checkIn, numOfNights, rooms, true, hotel.getRoomTypes());
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
                Object[] options = {"Supervisor", "Administrator"};
                Object option  = getOptions(options);
                switch (option.toString()) {
                    case "A":
                        options = new Object[]{"makeReservation", "makeCancellation","giveDiscount","RequestAnalysis"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "A":
                                String[] info = new String[4];
                                info[0] = scanner.next();
                                info[1] = scanner.next();
                                info[2] = scanner.next();
                                info[3] = scanner.next();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                System.out.println("Would you like to make a Reservation or Cancellation");
                                options = new Object[]{"Reservation", "B) Cancellation"};
                                option = getOptions(options);
                                if (option.toString().equals("A"))
                                {
                                    boolean done = false;
                                    while(!done) {
                                        System.out.println("What room would you like");
                                        option = getOptions(hotel.getRoomTypes().toArray());
                                        for (int i = 0; i < hotel.getRoomTypes().size(); i++) {
                                            if (option.equals(hotel.getRoomTypes().toString())) {
                                                rooms.add(hotel.getRoomTypes().get(i));
                                            }
                                        }
                                        System.out.println("Would you like to add another room? Y/N");
                                        choice = scanner.next().toUpperCase();
                                        if (!choice.equals("Y")) {
                                            done = true;
                                        }
                                    }
                                    System.out.println("When would you check in?");
                                    LocalDate checkIn = LocalDate.parse(scanner.next());
                                    System.out.println("How many nights are you saying?");
                                    numOfNights = scanner.nextInt();
                                    customer.makeReservation(checkIn, numOfNights, rooms, true, hotel.getRoomTypes());
                                }
                                break;
                            case "B":
                                System.out.println("Enter Reservation Number: ");
                                option = scanner.next();
                                if ((readReservation(option.toString(), "Reservations.csv") != null)) {
                                    Reservations re =  readReservation(option.toString(), "Reservations.csv");
                                    reservations.deleteReservations(re);
                                    System.out.println("Deleted");
                                }
                                break;
                            case "C":
                                Supervisor supervisor = new Supervisor();
                                System.out.println("Enter reservation Number");
                                choice = scanner.next();
                                supervisor.giveDiscount(readReservation(choice,"Reservations.csv"),10);
                                break;
                            case "D":
                                Supervisor supervisor1 = new Supervisor();
                                break;
                        }
                        break;
                    case "B":
                        options = new Object[]{"makeReservation", "makeCancellation"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "A":
                                String[] info = new String[4];
                                info[0] = scanner.next();
                                info[1] = scanner.next();
                                info[2] = scanner.next();
                                info[3] = scanner.next();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                System.out.println("Would you like to make a Reservation or Cancellation");
                                options = new Object[]{"Reservation", "B) Cancellation"};
                                option = getOptions(options);
                                if (option.toString().equals("A"))
                                {
                                    boolean done = false;
                                    while(!done) {
                                        System.out.println("What room would you like");
                                        option = getOptions(hotel.getRoomTypes().toArray());
                                        for (int i = 0; i < hotel.getRoomTypes().size(); i++) {
                                            if (option.equals(hotel.getRoomTypes().toString())) {
                                                rooms.add(hotel.getRoomTypes().get(i));
                                            }
                                        }
                                        System.out.println("Would you like to add another room? Y/N");
                                        choice = scanner.next().toUpperCase();
                                        if (!choice.equals("Y")) {
                                            done = true;
                                        }
                                    }
                                    System.out.println("When would you check in?");
                                    LocalDate checkIn = LocalDate.parse(scanner.next());
                                    System.out.println("How many nights are you saying?");
                                    numOfNights = scanner.nextInt();
                                    customer.makeReservation(checkIn, numOfNights, rooms, true, hotel.getRoomTypes());
                                }
                                break;
                            case "B":
                                System.out.println("Enter Reservation Number: ");
                                option = scanner.next();
                                if ((readReservation(option.toString(), "Reservations.csv") != null)) {
                                    Reservations re =  readReservation(option.toString(), "Reservations.csv");
                                    reservations.deleteReservations(re);
                                    System.out.println("Deleted");
                                }
                                break;
                        }
                        break;
                }
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
