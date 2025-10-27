package Salon;

import java.io.*;
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

        System.out.println("  VELKOMMEN TIL HARRY`S SALON");
        System.out.println("      ÅBNINGESTEDER:        ");

        System.out.println("      MANDAG-FREDAG: 10-18   ");
        System.out.println("      LØRDAG: lukker        ");
        System.out.println("      SØNDAG: lukker         ");


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
                    deleteAppointment();
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

        System.out.print("Indtast dato (f.eks. 2025-11-10): ");
        String date = input.nextLine();

        System.out.print("Indtast tidspunkt (f.eks. 17:00): ");
        String time = input.nextLine();

        Appointment newApp = new Appointment(name, date, time);
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