package Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;

public class ReservationSystem {

        static void writeToCSV(String fileName, ArrayList<Reservations> reservations, boolean overwrite) {
            try {
                File file = new File(fileName);
                StringBuffer data = new StringBuffer("");
                PrintWriter printWriter;
                if (file.exists() && !overwrite)
                {
                    printWriter = new PrintWriter(new FileOutputStream(file, true));
                }
                else
                {
                    // create file and add header if doesnt exist
                    printWriter = new PrintWriter(file);
                    if(fileName.equals("Cancellations.csv"))
                    {
                        data.append("ID,Name,Number,Email,Check in Date,Cancellation Date,No. of Rooms,Total Cost,Deposit\n");
                    }
                    else
                    {
                        data.append("ID,Name,Number,Email,Check in Date,Check out Date,No. of Rooms,Total Cost,Deposit\n");
                    }
                }

                // write data (, == new column && \n == new row)
                for (Reservations reservation : reservations)
                {
                    String ID = reservation.getReservationId();
                    String name = reservation.getReservationName();
                    String number = reservation.getReservationNumber();
                    String email = reservation.getReservationEmail();
                    String checkIn = reservation.getCheckInDate().toString();
                    String checkOut = reservation.getCheckOutDate().toString();
                    String numOfRooms = reservation.getNumOfRooms();
                    double totalCost = reservation.getTotalCost();
                    double deposit = reservation.getDeposit();

                    data.append(ID + "," + name + "," + number + "," + email + "," +
                            checkIn + "," + checkOut + "," + numOfRooms + "," + totalCost + "," + deposit + "\n");
                }
                printWriter.write(data.toString());
                printWriter.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        static ArrayList<Reservations> readFromCSV(String filename) {
            ArrayList<Reservations> details = new ArrayList<>();
            try {
                File File = new File(filename);
                Scanner input = new Scanner(File);
                while (input.hasNext()) {
                    String temp = input.next();
                    String[] fields = new String[9];
                    int x = 0;
                    for (char c: temp.toCharArray()) {
                        if (c == ',') {
                            x++;
                        }
                        else {
                            fields[x] += c;
                        }
                    }
                    //Reservations tempyboi = new Reservations(fields[0], fields[1], fields[2], fields[3], toLocalDate(fields[4]),toLocalDate(fields[5]), fields[6], Double.parseDouble(fields[7]), Boolean.parseBoolean(fields[8]));
                    //details.add(tempyboi);
                    //  details.add(temp);
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
