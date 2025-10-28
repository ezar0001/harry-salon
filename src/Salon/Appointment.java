package Salon;

public class Appointment {
    private String name;
    private String date;
    private String time;

    public Appointment(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
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

    public String toString() {
        return name + "\t" + date + "\t " + " kl. " + time;
    }
}
