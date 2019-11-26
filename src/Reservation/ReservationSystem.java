package Reservation;

import L4.Room;
import L4.Hotel;

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
			if (file.exists() && !overwrite)
			{
				printWriter = new PrintWriter(new FileOutputStream(file, true));
			}
			else
			{
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
				data.append(reservation.toCSV());
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
	 */
	public static ArrayList<Reservations> readFromCSV(String filename)
	{
		ArrayList<Reservations> details = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();
		try
		{
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
					String[] data = room.split("_", 3);
					rooms.add(new Room(data[0], data[1], Boolean.getBoolean(data[2])));
				}
				
				if (filename.contains("Cancellation"))
				{
					Cancellations cancellation = new Cancellations(fields[0], fields[1], fields[2], fields[3], LocalDate.parse(fields[4]), LocalDate.parse(fields[5]), rooms, Double.parseDouble(fields[8]), Boolean.parseBoolean(fields[10]));
					details.add((Reservations) cancellation);
				}
				else
				{
					Reservations reservations = new Reservations(fields[0], fields[1], fields[2], fields[3], LocalDate.parse(fields[4]), Integer.parseInt(fields[5]), rooms, Double.parseDouble(fields[8]), Boolean.parseBoolean(fields[10]));
					details.add(reservations);
				}
			}
			input.close();
		}
		catch (IOException error)
		{
			System.out.println("IO Exception");
		}
		return details;
	}

	public static Reservations readReservation(String reservationNum, String filename)
	{
		Reservations reservation = null;
		try
		{
			ArrayList<Room> rooms = new ArrayList<>();
			File file = new File(filename);
			Scanner input = new Scanner(file);
			if (input.hasNextLine())
			{
				input.nextLine();
			}

			while(input.hasNextLine())
			{
				String line = input.nextLine();
				String fields[] = line.split(",", 11);
				if (fields[0].equals(reservationNum))
				{
					String roomDetails[] = fields[7].split("/", 100);
					for (int i = 0; i < roomDetails.length - 1; i++)
					{
						String data[] = roomDetails[i].split("_", 3);
						rooms.add(new Room(data[0], data[1], Boolean.getBoolean(data[2])));
					}
					reservation = new Reservations(fields[0], fields[1], fields[2], fields[3], LocalDate.parse(fields[4]), Integer.parseInt(fields[5]), rooms, Double.parseDouble(fields[8]), Boolean.parseBoolean(fields[10]));
					break;
				}
			}
			input.close();
		}
		catch(IOException error)
		{
			System.out.println(error);
		}
		return reservation;
	}

	public static void deleteReservations(Reservations reservations, String hotelName) {
		ArrayList<Reservations> list = readFromCSV(hotelName + "Reservations.csv");
		list.remove(reservations);
		writeToCSV(hotelName + "Reservations.csv",list,true);
	}

	/**
	 * Makes sure that all CSV files are up to date
	 * @param today today's date used to compare check in, out and cancellation dates
	 * @param hotels list of hotels the check is happening for
	 */
	public static void checkCSV(LocalDate today, ArrayList<Hotel> hotels)
	{
		for (Hotel hotel : hotels)
		{
			// Get our data
			ArrayList<Reservations> reservations = readFromCSV(hotel.getName() + "Reservations.csv");
			ArrayList<Reservations> stays = readFromCSV(hotel.getName() + "Stays.csv");
			ArrayList<Reservations> cancellations = readFromCSV(hotel.getName() + "Cancellations.csv");

			// Delete stays from 7+ years ago
			for (int i = 0; i < stays.size(); i++)
			{
				Reservations stay = stays.get(i);
				if (stay.getCheckOutDate().plusYears(7).compareTo(today) <= 0)
				{
					stays.remove(i);
					i--;
				}
			}

			// Delete Cancellations that happen 30+ days ago
			for (int i = 0; i < cancellations.size(); i++)
			{
				Cancellations cancellation = (Cancellations) cancellations.get(i);
				if (cancellation.getCancellationDate().plusDays(30).compareTo(today) <= 0)
				{
					cancellations.remove(i);
					i--;
				}
			}

			// Add new Stays
			for (int i = 0; i < reservations.size(); i++)
			{
				Reservations reservation = reservations.get(i);
				if (reservation.getCheckInDate().compareTo(today) <= 0)
				{
					reservations.remove(i);
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