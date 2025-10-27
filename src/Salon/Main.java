package Salon;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean going = true;
        loadAppointmentsFromFile();
        OpeningHours2 OpeningHours =new OpeningHours2();
        while (going) {
            System.out.println("Harry's Salon");
            System.out.println("1. Se aftaler");
            System.out.println(" 2. Opret ny aftale");
            System.out.println("3. Slet eksisterende aftale");
            System.out.println("0. Luk program ned");
            System.out.println("Vælg: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewAppointments();
                    break;

                case 2:
                    newAppointment();
                    break;

                case 3:
                    deleteAppointment();
                    break;

                case 0:
                    System.out.println("Programmet lukker ned.");
                    going = false;
                    break;

                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }
    public static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("Der er ingen aftaler endnu.");
        } else {
            System.out.println("Alle aftaler");
            for (int i = 0; i < appointments.size(); i++) {
                System.out.println((i + 1) + ". " + appointments.get(i));
            }
        }
    }

    public static void newAppointment() {
        System.out.print("Indtast kundens navn: ");
        String name = input.nextLine();

        System.out.print("Indtast dato (f.eks. 2025-11-10): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        System.out.print("Indtast tidspunkt (f.eks. 17:00): ");
        LocalTime time = LocalTime.parse(input.nextLine());

        System.out.println("Indtast hvilket klip du vil have (herre eller dame)");
        String klip=input.nextLine();

        Appointment newApp = new Appointment(name, date, time, klip);
        appointments.add(newApp);
        saveAppointmentsToFile();

        System.out.println("Aftale oprettet!");
    }


    public static void deleteAppointment() {
        viewAppointments();
        if (appointments.isEmpty()) return;

        System.out.print("Indtast nummeret: ");
        int phoneNr = input.nextInt();
        input.nextLine();

        if (phoneNr > 0 && phoneNr <= appointments.size()) {
            appointments.remove(phoneNr - 1);
            System.out.println("Aftale slettet!");
        } else {
            System.out.println("Ugyldigt nummer.");
        }
    }

    public static void saveAppointmentsToFile() {
        try {
            FileWriter fil = new FileWriter("src/Salon/appointments.txt");
            PrintWriter ud = new PrintWriter(fil);
            for (Appointment app : appointments) {
                ud.println(app.getName() + ":" + app.getDate() + ":" + app.getTime()+":"+app.getKlip());
            }
            ud.close();
        } catch (IOException e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }


    public static void loadAppointmentsFromFile() {
        try {
            File file = new File("src/Salon/appointments.txt");
            if (!file.exists()) return;

            BufferedReader ind = new BufferedReader(new FileReader(file));
            String line;
            while ((line = ind.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    appointments.add(new Appointment(parts[0].trim(), LocalDate.parse(parts[1].trim()), LocalTime.parse(parts[2].trim()), parts[3].trim()));
                }
            }
            ind.close();
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning: " + e.getMessage());
        }
    }


}