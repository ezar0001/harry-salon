package Salon;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class OpeningHours2 {
    private ArrayList<LocalDateTime> Tider;
    private Set<LocalDate> ferie=new HashSet<>();
    private LocalDate dato;
    private LocalTime start;
    private LocalTime slut;
    private int interval;

    public OpeningHours2(){
        Tider=new ArrayList<>();
        dato=LocalDate.now();
        start=LocalTime.of(10,0);
        slut=LocalTime.of(18,0);
        interval=30;

    }
//laver tiderne for de enkelte dage.
public void timeSlots(LocalDate dato, LocalTime start, LocalTime slut, int interval){
    if(ferie.contains(dato)){
        System.out.println("Du kan desværre ikke booke den her dato, idet der er ferielukket.");
    }
    if (dato.getDayOfWeek()==DayOfWeek.SATURDAY){
        System.out.println("Der er lukket");
    }

    if(dato.getDayOfWeek()==DayOfWeek.SUNDAY){
        System.out.println("Der er lukket");
    }
LocalDateTime current= LocalDateTime.of(dato,start);
while(!current.isAfter(LocalDateTime.of(dato,slut))){
    Tider.add(current);
    current=current.plusMinutes(interval);
}
    }
    //laver tider 5 dage hen i fremtiden.
    public void alltimeSlots(LocalTime start, LocalTime slut, int interval){
        LocalDate idag= LocalDate.now();
        for (int i=0;i<4;i++){
            LocalDate fremdato=idag.plusDays(i);
            timeSlots(fremdato, start,slut,interval);
        }
    }
    //Når man kalder den skriver man startdato, og hvor mange dage frem man har ferie, som så adder alle de datoer til ferie settet.
public void addFerie(LocalDate startdato,int dagefrem){
        for(int n=0;n<dagefrem;n++){
            ferie.add(startdato.plusDays(n));
            System.out.println("ser bare om det virker");
        }

    }
}
