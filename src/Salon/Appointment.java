package Salon;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String klip;

    public Appointment(String name, LocalDate date, LocalTime time, String klip) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.klip = klip;
    }

    public String getName(){
        return name;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public String getKlip(){
        return klip;
    }

    public String toString() {
        return date + " kl. " + time + " - " + name+" " + klip;
    }
}
