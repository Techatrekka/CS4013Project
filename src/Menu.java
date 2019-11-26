import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import L4.Hotel;
import L4.L4;
import L4.Room;
import People.Customer;
import People.Supervisor;
import Reservation.ReservationSystem;
import Reservation.Reservations;

import static Reservation.ReservationSystem.readReservation;
import static Reservation.ReservationSystem.sortReservations;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    String[] hotels;
    String hotelChosen;
    LocalDate dateToday = LocalDate.now();
    Reservations reservations = new Reservations();
    Hotel hotel = new Hotel();
    ArrayList<Room> rooms = new ArrayList<>();
    int numOfNights;
    L4 chain = new L4();
    public void initialise() {
        chain.addHotel("MiggysParadise", "Mamma Mia Land", "5",
                new String[]{"Bachelor suite,1,true", "Two to tango,2,true",
                        "bring the family suite,5,true"},new int[]{2, 3, 3});
        chain.addHotel("SeanVille", "HoesAin'tLoyalCentral", "4",
                new String[]{"I went to the gym once Suite,1,true"}, new int[]{300});
        chain.addHotel("WickyNicky'sEmporium", "LoserVille", "-1",
                new String[]{"Slow Head Turn Deluxe,1,false"}, new int[]{1});
        hotels = chain.hotelList();
        sortReservations(dateToday,chain.getL4());
        L4.writeHotelDetailsToCSV("L4.csv",chain.getL4());
    }

    public void run() {
        initialise();
        boolean run = true;
        while (run) {
            String[] nickyboo = new String[]{"Yes", "No"};
            hotelChosen = getOptions(hotels).toString();
            System.out.println("A) Customer B) Staff C) Quit");
            String choice = scanner.nextLine();
            choice = choice.toUpperCase();
            if (choice.equals("A")) {
                System.out.println("Please input: Name, Email, Phone, Address");
                Scanner sc = new Scanner(System.in);
                String info = sc.nextLine();
                String[] split = info.split(",");
                Customer customer = new Customer(split[0], split[1], split[2], split[3]);
                System.out.println("Would you like to make a Reservation or a Cancellation?");
                Object[] options = new Object[]{"Reservation", "Cancellation"};
                Object option = getOptions(options);
                assert option != null;
                if (option.equals("Reservation")) {
                    makeReservation(customer);
                    System.out.println("Would you like to make another reservation or cancellation? Yes/No");
                    option = null;
                    option = getOptions(nickyboo);
                    if (option.equals("No")) {
                        run = false;
                    }
                } else {
                    makeCancellation();
                }
            } else if (choice.equals("B")) {
                Object[] options = {"Supervisor", "Administrator"};
                Object option = getOptions(options);
                switch (option.toString()) {
                    case "Supervisor":
                        options = new Object[]{"Reservation", "Cancellation", "giveDiscount", "RequestAnalysis"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "Reservation":
                                String[] info = new String[4];
                                info[0] = scanner.nextLine();
                                info[1] = scanner.nextLine();
                                info[2] = scanner.nextLine();
                                info[3] = scanner.nextLine();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                makeReservation(customer);
                                System.out.println("Would you like to make another reservation or cancellation? Yes/No");
                                option = null;
                                option = getOptions(nickyboo);
                                if (option.equals("No")) {
                                    run = false;
                                }
                                break;
                            case "Cancellation":
                                makeCancellation();
                                System.out.println("Would you like to make another reservation or cancellation? Yes/No");
                                option = null;
                                option = getOptions(nickyboo);
                                if (option.equals("No")) {
                                    run = false;
                                }
                                break;
                            case "giveDiscount":
                                Supervisor supervisor = new Supervisor();
                                System.out.println("Enter reservation Number");
                                choice = scanner.nextLine();
                                supervisor.giveDiscount(readReservation(choice, hotelChosen + "Reservations.csv"), 10);
                                break;
                            case "requestAnalysis":
                                Supervisor supervisor1 = new Supervisor();
                                break;
                        }
                        break;
                    case "Administrator":
                        options = new Object[]{"Reservation", "Cancellation"};
                        option = getOptions(options);
                        switch (option.toString()) {
                            case "Reservation":
                                String[] info = new String[4];
                                info[0] = scanner.nextLine();
                                info[1] = scanner.nextLine();
                                info[2] = scanner.nextLine();
                                info[3] = scanner.nextLine();
                                Customer customer = new Customer(info[0], info[1], info[2], info[3]);
                                makeReservation(customer);
                                System.out.println("Would you like to make another reservation or cancellation? Yes/No");
                                option = null;
                                option = getOptions(nickyboo);
                                if (option.equals("No")) {
                                    run = false;
                                }
                                break;
                            case "Cancellation":
                                makeCancellation();
                                System.out.println("Would you like to make another reservation or cancellation? Yes/No");
                                option = null;
                                option = getOptions(nickyboo);
                                if (option.equals("No")) {
                                    run = false;
                                }
                                break;
                        }
                        break;
                }
            } else if (choice.equals("C")) {
                run = false;
            } else {
                System.out.println("Choice not recognised, choose again!");
            }
        }
        L4.writeHotelDetailsToCSV("L4.csv",chain.getL4());
    }

    private Object getOptions(Object options[]) {
        Scanner scanner = new Scanner(System.in);
        if (options.length == 0) {
            return null;
        }
        char c = 'A';
        for (int i = 0; i < options.length; i++) {
            System.out.println(c + ") " + options[i]);
            c++;
        }
        char x = scanner.nextLine().toUpperCase().charAt(0);
        int h = x - 65;
        return options[h];
    }

    private void makeReservation(Customer customer) {
        boolean done = false;
        String choice = "";
        Object option = null;
        while (!done) {
            System.out.println("What type of room would you like?");
            int hotelInArray = 0;
            for (int i = 0; i < chain.getL4().size(); i++) {
                if (chain.getL4().get(i).getName().equals(hotelChosen)) {
                     option = getOptions(chain.getL4().get(i).getRoomTypes().toArray());
                    hotelInArray = i;
                }
            }
            for (int i = 0; i < chain.getL4().get(hotelInArray).getRoomTypes().size(); i++) {
                assert option != null;
                if (option.equals(chain.getL4().get(hotelInArray).getRoomTypes().get(i))){
                    rooms.add(chain.getL4().get(hotelInArray).getRoomTypes().get(i));
                }
            }
            System.out.println("Would you like to add another room? Yes/No");
            choice = scanner.nextLine().toUpperCase();
            if (!choice.equals("Yes")) {
                done = true;
            }
        }
        System.out.println("When would you check in?(dd-mm-yyyy)");
        String dateFormat   = "dd-MM-yyyy";
        LocalDate checkIn = LocalDate.parse(scanner.nextLine(),DateTimeFormatter.ofPattern(dateFormat));
        System.out.println("How many nights are you staying?");
        numOfNights = scanner.nextInt();
        customer.makeReservation(checkIn, numOfNights, rooms, true, hotel.getRoomTypes(), hotelChosen);
    }

    private void makeCancellation() {
        Object option;
        System.out.println("Enter Reservation Number: ");
        option = scanner.nextLine();
        if ((readReservation(option.toString(), hotelChosen + "Reservations.csv") != null)) {
            Reservations re = readReservation(option.toString(), hotelChosen + "Reservations.csv");
            ReservationSystem.deleteReservations(re,hotelChosen+"Reservations.csv");
            System.out.println("Reservation has been deleted.");
        }
    }
}
