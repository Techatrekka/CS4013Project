package Reservation;

import L4.Room;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author:
 */


public class Reservation extends ReservationSystem {

	String reservationName, number, email, reservationId;
	private int duration;
	LocalDate checkIn, checkOut;
	Double totalCost, deposit;
	boolean advancedPurchase;
	ArrayList<Room> rooms = new ArrayList<>();

	public Reservation() {}

	/**
	 * Create a reservation object
	 * @param reservationId
	 * @param reservationName
	 * @param number
	 * @param email
	 * @param checkIn
	 * @param duration
	 * @param rooms
	 * @param totalCost
	 * @param advancedPurchase
	 */
	public Reservation(String reservationId, String reservationName, String number, String email,
						LocalDate checkIn, int duration, ArrayList<Room> rooms,
						double totalCost, boolean advancedPurchase) {
		this.reservationName = reservationName;
		this.reservationId = reservationId;
		this.number = number;
		this.email = email;
		this.duration = duration;
		this.advancedPurchase = advancedPurchase;
		this.checkIn = checkIn;
		this.rooms = rooms;
		this.totalCost = totalCost;
		this.deposit = totalCost * .15;
	}

	/**
	 * Get the reservation id
	 * @return reservation id
	 */
	public String getReservationId() {
		return reservationId;
	}

	/**
	 * Get reservation name
	 * @return reservation name
	 */
	String getReservationName() {
		return reservationName;
	}

	/**
	 * Get phone number
	 * @return phone number
	 */
	String getPhoneNumber()
	{
		return number;
	}

	/**
	 * Calculate reservation ID by dividing the current time in milliseconds by 1000
	 * @return reservation id
	 */
	public String getNextReservationId() {
		return "" + System.currentTimeMillis() / 1000;
	}

	/**
	 * Set check in date
	 * @param checkIn
	 */
	public void setCheckIn(LocalDate checkIn){
		this.checkIn = checkIn;
	}

	/**
	 * Set the check out date
	 * @param checkOut
	 */
	public void setCheckOut(LocalDate checkOut){
		this.checkOut = checkOut;
	}

	/**
	 * Get the reservation email
	 * @return email
	 */
	String getReservationEmail()
	{
		return this.email;
	}

	/**
	 * Get the rooms
	 * @return rooms
	 */
	public ArrayList<Room> getRooms() { return rooms; }

	/**
	 * Get the duration
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}

	boolean  getAdvancedPurchase(){
		return this.advancedPurchase;
	}

	void setAdvancedPurchase(boolean set)
	{
		this.advancedPurchase = set;
	}

	public LocalDate getCheckInDate()
	{
		return checkIn;
	}

	LocalDate getCheckOutDate()
	{
		return checkIn.plusDays(duration);
	}

	public double getTotalCost()
	{
		return totalCost;
	}

	public void setTotalCost(double cost)
	{
		totalCost = cost;
	}

	double getDeposit()
	{
		return deposit;
	}

	public String toCSV()
	{
		return reservationId + "," + reservationName + "," + number + "," + email + "," + checkIn + "," + duration + "," + rooms.size() + "," + getRoomsAsString() + "," + totalCost + "," + deposit + "," + advancedPurchase + "\n";
	}

	String getRoomsAsString()
	{
		String temp = "";
		for (Room room : rooms)
		{
			temp += room.toCSV() + "/";
		}
		return temp;
	}

	@Override
	public String toString() {
		return reservationId + " " + reservationName + " " +
				checkIn + " " + checkOut + " " +
				rooms.size() + " " + totalCost;
	}
}
