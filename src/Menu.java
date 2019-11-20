import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import L4.Hotel;
import L4.L4;
import L4.Room;
import People.Customer;
import People.Supervisor;
import Reservation.Reservations;

import static Reservation.ReservationSystem.readReservation;

public class Menu {
    
    LocalDate dateToday;
    Reservations reservations = new Reservations();
    Hotel hotel = new Hotel();
    ArrayList<Room> rooms = new ArrayList<>();
    int numOfNights;

    public void initialise() {
        L4 chain = new L4();
        chain.addHotel("MiggysParadise","Mamma Mia Land", 5,
                new String[]{"Bachelor suite, 1, true", "Two to tango, 2, true",
                        "bring the family suite, 5, true"},
                new int[]{2,3,3});
        chain.addHotel("SeanVille","HoesAin'tLoyalCentral",4,
                new String[]{"I went to the gym once Suite, 1, true"}, new int[]{300});
        chain.addHotel("WickyNicky'sEmporium","LoserVille", -1,
                new String[]{"Slow Head Turn Deluxe, 1, false"}, new int[] {1});
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("C)ustomer S)taff Q)uit");
            String choice = scanner.next();
            choice = choice.toUpperCase();
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
                Object[] options = new Object[]{"Reservation", "Cancellation"};
                Object option = getOptions(options);
                assert option != null;
                if (option.equals("Reservation"))
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
                        options = new Object[]{"Reservation", "Cancellation","giveDiscount","RequestAnalysis"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "Reservation":
                                String[] info = new String[4];
                                info[0] = scanner.nextLine();
                                info[1] = scanner.nextLine();
                                info[2] = scanner.nextLine();
                                info[3] = scanner.nextLine();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                System.out.println("Would you like to make a Reservation or Cancellation");
                                options = new Object[]{"Reservation", "B) Cancellation"};
                                option = getOptions(options);
                                assert option != null;
                                if (option.equals("A"))
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
                            case "Cancellation":
                                System.out.println("Enter Reservation Number: ");
                                option = scanner.next();
                                if ((readReservation(option.toString(), "Reservations.csv") != null)) {
                                    Reservations re =  readReservation(option.toString(), "Reservations.csv");
                                    reservations.deleteReservations(re);
                                    System.out.println("Deleted");
                                }
                                break;
                            case "giveDiscount":
                                Supervisor supervisor = new Supervisor();
                                System.out.println("Enter reservation Number");
                                choice = scanner.next();
                                supervisor.giveDiscount(readReservation(choice,"Reservations.csv"),10);
                                break;
                            case "requestAnalysis":
                                Supervisor supervisor1 = new Supervisor();
                                break;
                        }
                        break;
                    case "B":
                        options = new Object[]{"Reservation", "Cancellation"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "Reservation":
                                String[] info = new String[4];
                                info[0] = scanner.next();
                                info[1] = scanner.next();
                                info[2] = scanner.next();
                                info[3] = scanner.next();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                System.out.println("Would you like to make a Reservation or Cancellation");
                                options = new Object[]{"Reservation", "Cancellation"};
                                option = getOptions(options);
                                if (option.toString().equals("Reservation"))
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
                            case "Cancellation":
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
        Scanner scanner = new Scanner(System.in);
        if (options.length == 0)
		{
			return null;
		}
        char c = 'A';
        for (int i = 0; i < options.length; i++) {
            System.out.println(c + ") " + options[i]);
            c++;
        }
		char x = scanner.next().toUpperCase().charAt(0);
        int h = x - 65;
		return options[h];
	}
}
