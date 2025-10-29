package Salon;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static OpeningHours salonHours = new OpeningHours();


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
            System.out.println("5  Rediger Åbningstider");
            System.out.println("6. Se regning fra en dato");
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
                    salonHours.showHours();
                    break;

                case 5:
                    salonHours.editHour();
                    break;

                case 6:
                    try {
                        getBillFromDate();
                    }catch (IOException e){
                        System.out.println("Filen kunne ikke læses: "+ e.getMessage());
                    }
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
        //If statement: isOpenOnDate(date)
        //true: Fortsæt processen med at lave en appointment
        //false (else): Afbryd processen

        System.out.print("Indtast tidspunkt (f.eks. 17.00): ");
        String time = input.nextLine();

        System.out.println("indtast klip (Herre eller Dame)");//Ny
        String klip=input.nextLine();//ny

        Appointment newApp = new Appointment(name, date, time, klip);//klip er ny
        appointments.add(newApp);
        saveAppointmentsToFile();

        System.out.println("Aftale oprettet!");
    }

    public static void deleteAppointmentByName() {
        System.out.print("Indtast kundens navn på aftalen du vil slette: ");
        String name = input.nextLine();
        int len= appointments.size();

        boolean found = false;
        for (int i = 0; i < len; i++) {
            if (appointments.get(i).getName().equalsIgnoreCase(name)) {
                appointments.remove(i);
                System.out.println("Aftale med " + name + " slettet!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Ingen aftale fundet med navnet " + name + ".");
        }
        saveAppointmentsToFile();
    }

    public static void saveAppointmentsToFile() {
        try {
            FileWriter fil = new FileWriter("src/Salon/appointments.txt");
            PrintWriter ud = new PrintWriter(fil);
            ud.print("");

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
            if (!file.exists()){
                System.out.println("KUNNE IKKE FINDE FIL");
            }

            BufferedReader ind = new BufferedReader(new FileReader(file));
            String line;
            while ((line = ind.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(":");
                if (parts.length == 4) {//ændret fra 3 til 4
                    System.out.println(parts);
                    appointments.add(new Appointment(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));//parts 3 ny
                }
            }
            ind.close();
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning: " + e.getMessage());
        }
    }

//    public static void getBillFromDate() {
//        System.out.print("Indtast datoen du vil slå op: ");
//        String date = input.nextLine();
//        int len= appointments.size();
//        int count=0;
//
//        boolean found = false;
//        for (int i = 0; i < len; i++) {
//            if (appointments.get(i).getDate().equalsIgnoreCase(date)) {
//                found = true;
//                count++;
//            }
//        }
//
//        if (!found) {
//            System.out.println("Ingen kunder på denne dato: " + date);
//        }
//        System.out.println("Samlede beløb indtjent på "  + date + ":"+ "\t" + count*250 + " kr.");
//    }

    public static void getBillFromDate() throws IOException {
        System.out.println("Indtast startdatoen: (dd/mm-yyyy): ");
        String startDatestring = input.nextLine();

        System.out.println("Indtast slutdato: (dd/mm-yyyy)");
        String slutDatostring = input.nextLine();

        DateTimeFormatter omformat = DateTimeFormatter.ofPattern("dd/MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDatestring, omformat); //nu er string omformatteret til localdate
        LocalDate slutDate = LocalDate.parse(slutDatostring,omformat);

        FileReader fil = new FileReader("src/Salon/appointments.txt");
        BufferedReader ind = new BufferedReader(fil);
        String linje;


        int Herre=0;
        int Dame=0;

        while ((linje =ind.readLine())!= null) {
            String[] parts = linje.split(":");

            if (parts.length < 5) {
                System.out.println("Skipper ugyldig line: "+linje);
                continue;
            }

            try {
                LocalDate datoBid = LocalDate.parse(parts[1].trim(), omformat);

                if ((datoBid.isEqual(startDate) || datoBid.isAfter(startDate)) &&
                        (datoBid.isEqual(slutDate) || datoBid.isBefore(slutDate))) {

                    System.out.println(linje);

                    String klip=parts[4].trim();
                    if (klip.equalsIgnoreCase("Herre"))Herre++;
                    else if (klip.equalsIgnoreCase("Dame")) Dame++;

                }

            }catch(DateTimeParseException e){
                System.out.println("Ugyldig dato på line: "+linje);
            }
        }
        ind.close();
        System.out.println("Antal Herre:"+Herre+"Antal Dame:"+Dame);
        System.out.println("Samlet indtjeneste for perioden = "+(Herre*200)*0.75+" + "+(Dame*300)*0.75);
    }

}
