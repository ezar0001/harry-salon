package Salon;

public class Appointment {
    private String name;
    private String date;
    private String time;
    private String klip;

    public Appointment(String name, String date, String time, String klip) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.klip = klip;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getKlip(){
        return klip;
    }

    public String toString() {
        return date + "kl. " + time + " - " + name + klip;
    }
}
