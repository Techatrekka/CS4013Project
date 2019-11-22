package Reservation;

import L4.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;

public class ReservationSystem {

        public static void writeToCSV(String fileName, ArrayList<Reservations> reservations, boolean overwrite) {
            try {
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
                        data.append("ID,Name,Number,Email,Check in Date,Cancellation Date,No. of Rooms, Room Types, Total Cost,Deposit\n");
                    }
                    else
                    {
                        data.append("ID,Name,Number,Email,Check in Date,Stay Duration,No. of Rooms, Room Types,Total Cost,Deposit\n");
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
                    String rooms = reservation.getRoomsAsString();
                    int stayDuration = reservation.getDuration();
                    double totalCost = reservation.getTotalCost();
                    boolean advancedPurch = reservation.getAdvancedPurchase();
                    double deposit = reservation.getDeposit();
                    String checkOut = reservation.getCheckOutDate().toString();
                    data.append(ID + "," + name + "," + number + "," + email + "," +
                            checkIn + "," + checkOut + "," + rooms + "," + stayDuration + ","  + totalCost + "," + deposit + "," + advancedPurch + "\n");
                }
                printWriter.write(data.toString());
                printWriter.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        public static ArrayList<Reservations> readFromCSV(String filename) {
            ArrayList<Reservations> details = new ArrayList<>();
            ArrayList<Room> rooms = new ArrayList<>();
            try {
                File File = new File(filename);
                Scanner input = new Scanner(File);
                while (input.hasNext()) {
                    String temp = input.next();
                    String[] fields = new String[9];
                    int x = 0;
                    String roomDetails = "";
                    for (char c: temp.toCharArray()) {
                        if (c == ',') {
                            x++;
                        }
                        else if (x == 5) {
                            roomDetails += c;
                        }
                        else {
                            fields[x] += c;
                        }
                    }
                    int var = 0;
                    String[] data = new String[3];
                    for (int i = 0; i < roomDetails.length(); i++) {
                        if (var == 3) {
                            var = 0;
                            Room room = new Room(data[0], Integer.parseInt(data[1]), Boolean.getBoolean(data[2]));
                            rooms.add(room);
                        }
                        if (roomDetails.charAt(i) == ' ') {
                            var++;
                        }
                        if (roomDetails.charAt(i) != ' ') {
                            fields[var] += roomDetails.charAt(i);
                        }
                    }
                   Reservations reservations = new Reservations(fields[0], fields[1],
                            fields[2], fields[3], toLocalDate(fields[4]),rooms,
                            Integer.parseInt(fields[6]), Double.parseDouble(fields[7]), Boolean.parseBoolean(fields[8]));
                    details.add(reservations);
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

        private static LocalDate toLocalDate(String temp) {
            return LocalDate.parse(temp);
        }

        public void deleteReservations(Reservations reservations) {
            ArrayList<Reservations> list = readFromCSV("Reservations.csv");
            list.remove(reservations);
            writeToCSV("Reservations.csv",list,true);
        }
}
