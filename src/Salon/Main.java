package Salon;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean going = true;
        loadAppointmentsFromFile();

        System.out.println("  VELKOMMEN TIL HARRY`S SALON");
        System.out.println("      ÅBNINGSTIDER:        ");

        System.out.println("      MANDAG-FREDAG: 10-18   ");
        System.out.println("      LØRDAG: Lukket        ");
        System.out.println("      SØNDAG: Lukket         ");


        while (going) {
            System.out.println("Harry's Salon");
            System.out.println("1. Se aftaler");
            System.out.println("2. Opret ny aftale");
            System.out.println("3. Slet eksisterende aftale");
            System.out.println("4. Se åbningstider ");
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
                    deleteAppointmentByName();
                    break;

                case 4:
                    OpeningHours hours = new OpeningHours();
                    hours.visAabningstider();
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

        System.out.print("Indtast dato (f.eks. 10/10-2025): ");
        String date = input.nextLine();

        System.out.print("Indtast tidspunkt (f.eks. 17:00): ");
        String time = input.nextLine();

        Appointment newApp = new Appointment(name, date, time);
        appointments.add(newApp);
        saveAppointmentsToFile();

        System.out.println("Aftale oprettet!");
    }

    public static void deleteAppointmentByName() {
        System.out.print("Indtast kundens navn på aftalen du vil slette: ");
        String name = input.nextLine();

        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getName().equalsIgnoreCase(name)) {
                appointments.remove(i);
                saveAppointmentsToFile();
                System.out.println("Aftale med " + name + " slettet!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Ingen aftale fundet med navnet " + name + ".");
        }
    }

    public static void saveAppointmentsToFile() {
        try {
            FileWriter fil = new FileWriter("src/Salon/appointments.txt", true);
            PrintWriter ud = new PrintWriter(fil);
            for (Appointment app : appointments) {
                ud.println(app.getName() + ":" + app.getDate() + ":" + app.getTime());
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
                if (parts.length == 3) {
                    appointments.add(new Appointment(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
            ind.close();
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning: " + e.getMessage());
        }
    }
}

