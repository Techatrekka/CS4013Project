import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Analytics.DataAnalysis;
import L4.Hotel;
import L4.L4;
import L4.Room;
import People.Customer;
import People.Supervisor;
import Reservation.ReservationSystem;
import Reservation.Reservation;

import static Reservation.ReservationSystem.readReservation;
import static Reservation.ReservationSystem.checkCSV;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	String[] hotels;
	String hotelChosen;
	LocalDate dateToday = LocalDate.now();
	Reservation reservations = new Reservation();
	Hotel hotel = new Hotel();
	ArrayList<Room> rooms = new ArrayList<>();
	int numOfNights;
	L4 chain = new L4();
	String[] YesNo = new String[]{"Yes", "No"};

	public void initialise() {
		chain.addHotel("Mighele's Paradise", "Tipperary", "5",
				new String[]{"Bachelor Suite,1,true", "Two to Tango Suite,2,true",
						"Bring the Family Suite,5,true"},new int[]{20, 30, 30});
		chain.addHotel("SeanVille Suites", "Limerick", "4",
				new String[]{"Single Room,1,true", "Double Room,2,false", "Family Room,5,true"}, new int[]{30, 40, 30});
		chain.addHotel("Marcin's Motel", "Clare", "3",
				new String[]{"Deluxe Room,3,true", "Single Room,1,false", "Double Room,2,false"}, new int[]{5, 10, 8});
		hotels = chain.hotelList();
		checkCSV(dateToday,chain.getL4());
		L4.writeHotelDetailsToCSV("L4.csv",chain.getL4());
	}

	public void run() {
		initialise();
		boolean AP = true;
		boolean run = true;
		while (run) {
			System.out.println("Welcome to the L4 Hotel System!");
			System.out.println("Please choose the hotel you would like to access: ");
			hotelChosen = getOptions(hotels).toString();
			for (int i = 0; i < chain.getL4().size(); i++) {
				if (hotelChosen.equals(chain.getL4().get(i).getName())) {
					hotel = chain.getL4().get(i);
				}
			}
			System.out.println("Please choose a user: ");
			System.out.println("A) Customer B) Staff C) Quit");
			String choice = scanner.nextLine();
			choice = choice.toUpperCase();
			switch (choice) {
				case "A": {
					System.out.println("Please input: Name, Email, Phone");
					String info = scanner.nextLine();
					String[] split = info.split(",");
					Customer customer = new Customer(split[0], split[1], split[2]);
					System.out.println("Would you like to make a Reservation or a Cancellation?");
					Object[] options = new Object[]{"Reservation", "Cancellation"};
					Object option = getOptions(options);
					assert option != null;
					if (option.equals("Reservation")) {
						makeReservation(customer, AP);
						System.out.println("Would you like to use the system again?");
						option = getOptions(YesNo);
						if (option.equals("No")) {
							run = false;
						}
					} else {
						makeCancellation();
					}
					break;
				}
				case "B": {
					System.out.println("Please choose a staff member: ");
					Object[] options = {"Supervisor", "Administrator"};
					Object option = getOptions(options);
					switch (option.toString()) {
						case "Supervisor":
							System.out.println("What would you like to do?");
							options = new Object[]{"Make a reservation", "Make a cancellation", "Give a discount", "Request a data analysis"};
							option = getOptions(options);
							switch (option.toString()) {
								case "Make a reservation":
									System.out.println("Please input customer's details as follows: Name, Email, Phone");
									String info = scanner.nextLine();
									String[] split = info.split(",");
									Customer customer = new Customer(split[0], split[1], split[2]);
									System.out.println("Advanced Purchase?");
									option = getOptions(YesNo);
									if (option.toString().equals("Yes")) AP = true;
									makeReservation(customer, AP);
									System.out.println("Would you like to use the system again?");
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
								case "Make a cancellation":
									makeCancellation();
									System.out.println("Would you like to use the system again?");
									option = null;
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
								case "Give a discount":
									Supervisor supervisor = new Supervisor();
									System.out.println("Enter reservation number: ");
									choice = scanner.nextLine();
									System.out.println("Enter discount amount: ");
									double discount = Double.parseDouble(scanner.nextLine());
									supervisor.giveDiscount(readReservation(choice, hotelChosen + "Reservations.csv"),
											discount,hotelChosen);
									System.out.printf("A %%%4.2f discount has been applied to reservation number %s\n\n",
											discount, choice);
									System.out.println("Would you like to use the system again?");
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
								case "Request a data analysis":
									System.out.println("Enter dates for analytics ");
									String dateFormat   = "dd-MM-yyyy";
									System.out.println("from(dd-mm-yyyy)");
									LocalDate from = LocalDate.parse(scanner.nextLine(),DateTimeFormatter.ofPattern(dateFormat));
									System.out.println("to (dd-mm-yyyy)");
									LocalDate to = LocalDate.parse(scanner.nextLine(),DateTimeFormatter.ofPattern(dateFormat));
									DataAnalysis.writeDataAnalyticsToCSV(from,to,hotel);
									System.out.println("Would you like to use the system again?");
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
							}
							break;
						case "Administrator":
							System.out.println("Would you like to make a Reservation or a Cancellation?");
							options = new Object[]{"Make a reservation", "Make a cancellation"};
							option = getOptions(options);
							switch (option.toString()) {
								case "Make a reservation":
									System.out.println("Please input customer's details as follows: Name, Email, Phone");
									String info = scanner.nextLine();
									String[] split = info.split(",");
									Customer customer = new Customer(split[0], split[1], split[2]);
									System.out.println("Advanced Purchase?");
									option = getOptions(YesNo);
									if (option.toString().equals("Yes")) AP = true;
									makeReservation(customer, AP);
									System.out.println("Would you like to use the system again?");
									option = null;
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
								case "Make a cancellation":
									makeCancellation();
									System.out.println("Would you like to use the system again?");
									option = getOptions(YesNo);
									if (option.equals("No")) {
										run = false;
									}
									break;
							}
							break;
					}
					break;
				}
				case "C":
					run = false;
					break;
				default:
					System.out.println("Choice not recognised, choose again!");
					break;
			}
		}
		L4.writeHotelDetailsToCSV("L4.csv",chain.getL4());
	}

	private Object getOptions(Object options[]) {
		if (options.length == 0) {
			return null;
		}
		char c = 'A';
		for (int i = 0; i < options.length; i++) {
			System.out.println(c + ") " + options[i]);
			c++;
		}
		String p = scanner.nextLine();
		char x = p.toUpperCase().charAt(0);
		int h = x - 65;
		return options[h];
	}

	private void makeReservation(Customer customer, Boolean AP) {
		boolean done = false;
		Object option = null;
		rooms.removeAll(rooms);
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
			System.out.println("Would you like to add another room?");
			option = getOptions(YesNo);
			if (option.equals("No")) {
				done = true;
			}
		}
		System.out.println("When would you like to check in?(dd-mm-yyyy)");
		String dateFormat   = "dd-MM-yyyy";
		LocalDate checkIn = LocalDate.parse(scanner.nextLine(),DateTimeFormatter.ofPattern(dateFormat));
		System.out.println("How many nights are you staying?");
		numOfNights = Integer.parseInt(scanner.nextLine());
		customer.makeReservation(checkIn, numOfNights, rooms, AP, hotel.getRoomTypes(), hotel, hotelChosen);
	}

	private void makeCancellation() {
		Object option;
		System.out.println("Enter Reservation Number: ");
		option = scanner.nextLine();
		if ((readReservation(option.toString(), hotelChosen + "Reservations.csv") != null)) {
			Reservation re = readReservation(option.toString(), hotelChosen + "Reservations.csv");
			ReservationSystem.deleteReservation(re,hotelChosen);
			System.out.println("Reservation has been deleted.");
		}
	}
}
