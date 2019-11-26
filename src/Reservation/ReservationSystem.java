package Reservation;

import L4.Hotel;
import L4.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;

public class ReservationSystem
{

	public static void writeToCSV(String fileName, ArrayList<Reservations> reservations, boolean overwrite)
	{
		try
		{
			File file = new File(fileName);
			StringBuffer data = new StringBuffer("");
			PrintWriter printWriter;
			if (file.exists() && !overwrite) {
				printWriter = new PrintWriter(new FileOutputStream(file, true));
			}
			else {
				// create file and add header if doesn't exist
				printWriter = new PrintWriter(file);
				if(fileName.contains("Cancellations"))
				{
					data.append("ID,Name,Number,Email,Check in Date,Cancellation Date,No. of Rooms, Room Types, Total Cost,Deposit,Advanced\n");
				}
				else
				{
					data.append("ID,Name,Number,Email,Check in Date,Stay Duration,No. of Rooms, Room Types,Total Cost,Deposit,Advanced\n");
				}
			}

			// write data (, == new column && \n == new row)
			for (Reservations reservation : reservations)
			{
				String ID = reservation.getReservationId();
				String name = reservation.getReservationName();
				String number = reservation.getPhoneNumber();
				String email = reservation.getReservationEmail();
				String checkIn = reservation.getCheckInDate().toString();
				int stayDuration = reservation.getDuration();
				int roomNum = reservation.getRooms().size();
				String rooms = reservation.getRoomsAsString();
				double totalCost = reservation.getTotalCost();
				boolean advancedPurchase = reservation.getAdvancedPurchase();
				double deposit = reservation.getDeposit();
				data.append(ID + "," + name + "," + number + "," + email + "," +
						checkIn + "," + stayDuration + "," + roomNum + "," + rooms + ","  + totalCost + "," + deposit + "," + advancedPurchase + "\n");
			}
			printWriter.write(data.toString());
			printWriter.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**readFromCSV() reads through a csv file and returns an arrayList of Reservations
	 * Parameters: String Filename
	 * */
	public static ArrayList<Reservations> readFromCSV(String filename) {
		ArrayList<Reservations> details = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();
		try {
			File File = new File(filename);
			Scanner input = new Scanner(File);
			if (input.hasNextLine())
			input.nextLine();
			while (input.hasNext()) {
				String temp = input.nextLine();
				//number of data fields in a reservation
				String[] fields = temp.split(",", 9000);
				String[] roomDetails = fields[7].split("/", 89);
				String[] temporary = new String[roomDetails.length - 1];
				for (int i = 0; i < roomDetails.length - 1; i++) {
				    temporary[i] = roomDetails[i];
                }
				roomDetails = temporary;
				for (String room : roomDetails)
				{
					System.out.println(room);
					String[] data = room.split("_", 3);
					for (String dats : data)
					{
						System.out.println(dats);
					}
					rooms.add(new Room(data[0], Integer.parseInt(data[1]), Boolean.getBoolean(data[2])));
				}
				Reservations reservations = new Reservations(fields[0], fields[1],
						fields[2], fields[3], LocalDate.parse(fields[4]), Integer.parseInt(fields[5]),
						rooms, Double.parseDouble(fields[8]), Boolean.parseBoolean(fields[10]));
				details.add(reservations);
				//you can't code for shit, not even your dog loves you
			}
			input.close();
		}
		catch (IOException error) {
			System.out.println("IO Exception");
		}
		return details;
	}

	public static Reservations readReservation(String reservationNum, String File) {
		ArrayList<Reservations> temp = readFromCSV(File);
		String[] res = new String[9];
		Reservations reservation = new Reservations();
		for (int i = 0; i < temp.size(); i++) {
			if(reservation.getReservationId().equals(reservationNum)) {
				reservation = temp.get(i);
			}
		}
		if (reservation.equals(null)) {
			//change the print statement
			System.out.println("this bitch empty yeet");
			return null;
		}
		return new Reservations();
	}

	public void deleteReservations(Reservations reservations) {
		ArrayList<Reservations> list = readFromCSV("Reservations.csv");
		list.remove(reservations);
		writeToCSV("Reservations.csv",list,true);
	}
	
	public static void sortReservations(LocalDate today, ArrayList<Hotel> hotels)
	{
		for (Hotel hotel : hotels)
		{
			// Get our data
			ArrayList<Reservations> reservations = readFromCSV(hotel.getName() + "Reservations.csv");
			ArrayList<Reservations> stays = readFromCSV(hotel.getName() + "Stays.csv");

			// Delete Old Stays
			for (int i = 0; i < stays.size(); i++)
			{
				Reservations stay = stays.get(i);
				if (stay.getCheckOutDate().compareTo(today) < 0)
				{
					stays.remove(stay);
					i--;
				}
			}
						
			// Add new Stays
			for (int i = 0; i < reservations.size(); i++)
			{
				Reservations reservation = reservations.get(i);
				if (reservation.getCheckInDate().compareTo(today) < 0)
				{
					reservations.remove(reservation);
					stays.add(reservation);
					i--;
				}
			}

			// Save our data into CSV
			writeToCSV(hotel.getName() + "Reservations.csv", reservations, true);
			writeToCSV(hotel.getName() + "Stays.csv", stays, true);
		}
	}
}
