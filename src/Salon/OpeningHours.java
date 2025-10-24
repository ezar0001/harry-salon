package Salon;
import java.util.LinkedHashMap;


public class OpeningHours {
    public static void main(String[] args) {
OpeningHours hours = new OpeningHours();
hours.visAabningstider();
    }


    private LinkedHashMap<String, String>openingHours;

    //Constructor
    public OpeningHours() {
        openingHours = new LinkedHashMap<>();
        opretAabningstider();
    }

    private void opretAabningstider() {
        openingHours.put("Mandag", "10:00 - 18:00");
        openingHours.put("Tirsdag", "10:00 - 18:00");
        openingHours.put("Onsdag", "10:00 - 18:00");
        openingHours.put("Torsdag", "10:00 - 18:00");
        openingHours.put("Fredag", "10:00 - 18:00");
        openingHours.put("Lørdag", "Lukket");
        openingHours.put("Søndag", "Lukket");
    }

    public void visAabningstider(){
        System.out.println("   SALONENS ÅBNINGSTIDER   ");
        for (String dag : openingHours.keySet()){
            System.out.println(dag + ": "+ openingHours.get(dag));
        }
    }
}