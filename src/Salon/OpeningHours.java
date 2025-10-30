package Salon;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Simpelt program til ejeren af salonen.
 * Ejeren kan se og redigere åbningstider for hver ugedag.
 */
public class OpeningHours {

    // Gemmer ugedage og deres åbningstider i et LinkedHashMap (bevarer rækkefølgen)
    private LinkedHashMap<String, String> hours;
    private Scanner input = new Scanner(System.in);

    // Constructor – opretter standard åbningstider
    public OpeningHours() {
        hours = new LinkedHashMap<>();

        hours.put("Mandag", "10:00 - 18:00");
        hours.put("Tirsdag", "10:00 - 18:00");
        hours.put("Onsdag", "10:00 - 18:00");
        hours.put("Torsdag", "10:00 - 18:00");
        hours.put("Fredag", "10:00 - 18:00");
        hours.put("Lørdag", "Lukket");
        hours.put("Søndag", "Lukket");
    }

    // Viser alle åbningstider
    public void showHours() {
        System.out.println("\n   SALONENS ÅBNINGSTIDER   ");
        for (String day : hours.keySet()) {
            System.out.println(day + ": " + hours.get(day));
        }
    }

    // Lader ejeren ændre åbningstid for en bestemt dag
    public void editHour() {
        System.out.println("Log in med kodeord:");
        String kodeord =input.nextLine();
        if (kodeord.equals("HairyHarry")){
            System.out.println("Adgang tilladt");
        }else{
            System.out.println("Forkert kodeord");
            return;
        }
        System.out.print("\nSkriv dagen du vil ændre (fx 'Mandag'): ");
        String day = input.nextLine();

        if (!hours.containsKey(day)) {
            System.out.println("Dagen findes ikke. Husk stort begyndelsesbogstav (fx Mandag).");
            return;
        }

        System.out.print("Indtast ny åbningstid (fx '09:00 - 17:00' eller 'Lukket'): ");
        String newTime = input.nextLine();

        hours.put(day, newTime);
        System.out.println(" Åbningstiden for " + day + " er nu ændret til: " + newTime);
    }

    // Main – hvor programmet starter
    public static void main(String[] args) {
        OpeningHours salon = new OpeningHours();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("     HARRY'S SALON - EJER MENU    ");

        while (running) {
            System.out.println("\n1. Se åbningstider");
            System.out.println("2. Rediger åbningstider");
            System.out.println("0. Luk programmet");
            System.out.print("Vælg: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1" -> salon.showHours();
                case "2" -> salon.editHour();
                case "0" -> {
                    System.out.println("Programmet lukker. Farvel");
                    running = false;
                }
                default -> System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }

        input.close();
    }
}


