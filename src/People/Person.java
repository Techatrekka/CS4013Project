package People;

import L4.Hotel;
import L4.Room;
import Reservation.ReservationSystem;
import Reservation.Reservation;
import L4.Billing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static Reservation.ReservationSystem.writeToCSV;

/**
 * @author: Nicole Berty
 */

public class Person {

	protected String name;
	String phone;
	protected String email;

	Person() {

	}

	/**
	 * Make a reservation using the following parameters. Add the reservation to the reservations CSV file using the writeToCSV method
	 * @param checkIn
	 * @param numOfNights
	 * @param rooms
	 * @param advancedPurchase
	 * @param RoomTypes
	 * @param Hotel
	 * @param hotel
	 */
	public void makeReservation(LocalDate checkIn, int numOfNights, ArrayList<Room>
			rooms, boolean advancedPurchase, ArrayList<Room> RoomTypes, Hotel Hotel, String hotel) {
		Scanner scanner = new Scanner(System.in);
		Billing bill = new Billing();
		double total = bill.calculatePrice(Hotel, rooms,
				numOfNights, checkIn, RoomTypes, advancedPurchase);
		System.out.println("Reservation Price: " + total);
		System.out.print("Continue with the Reservation?\nA) Yes\nB) No\n");
		String choice = scanner.nextLine().toUpperCase();
		if (choice.equals("B")) {
			return;
		}
		Reservation re = new Reservation();
		String reservationID = re.getNextReservationId();
		System.out.println("Your reservation number is: " + reservationID + "\n");
		Reservation reservations = new Reservation(reservationID, name, phone, email,
				checkIn,numOfNights,rooms,total,advancedPurchase);
		ArrayList<Reservation> reservations1 = ReservationSystem.readFromCSV(hotel+"Reservations.csv");
		reservations1.add(reservations);
		writeToCSV(hotel+"Reservations.csv",reservations1,true);
	}

	/**
	 * Set the name of the person
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the phone number of the person
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Set the email of the person
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the phone number of the person
	 * @return person's phone number
	 */
	String getPhone() {
		return phone;
	}

	/**
	 * Get the name of the person
	 * @return person's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the email of the person
	 * @return person's email
	 */
	public String getEmail() {
		return email;
	}

	@Override
	public String toString(){
		return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail();
	}
}
