package People;

import L4.Hotel;
import L4.Room;
import Reservation.Cancellation;
import Reservation.ReservationSystem;
import Reservation.Reservation;
import Sales.Billing;

import java.time.LocalDate;
import java.util.ArrayList;

import static Reservation.ReservationSystem.writeToCSV;

public class Person {

	protected String name;
	protected String phone;
	protected String email;

	Person() {

	}

	public void makeReservation(LocalDate checkIn, int numOfNights, ArrayList<Room>
								rooms, boolean advancedPurchase, ArrayList<Room> RoomTypes, Hotel Hotel, String hotel) {
		Billing bill = new Billing();
		double total = bill.calculatePrice(Hotel, rooms,
				numOfNights, checkIn, RoomTypes, advancedPurchase);
		Reservation re = new Reservation();
		String reservationID = re.getNextReservationId();
		System.out.println(reservationID);
		Reservation reservations = new Reservation(reservationID, name, phone, email,
		checkIn,numOfNights,rooms,total,advancedPurchase);
		ArrayList<Reservation> reservations1 = ReservationSystem.readFromCSV(hotel+"Reservations.csv");
		reservations1.add(reservations);
		writeToCSV(hotel+"Reservations.csv",reservations1,true);
	}

	void makeCancellation(Reservation reservation, String hotel) {
		Cancellation cancel = new Cancellation(reservation, hotel);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString(){
		return "Name: " + getName() + "Phone: " + getPhone() + "Email: " + getEmail();
	}
}