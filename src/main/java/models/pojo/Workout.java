package models.pojo;

import java.util.Date;

/**
 * Created by Olga on 03.03.2017.
 */
public class Workout {

    private int id;
    private int id_exersise;
    private String workout;
    private Date date;
    private int intensity;
    private String duration;

    public Workout() {
    }

    public Workout( int id, int id_exersise, String workout, Date date, String duration, int intensity ) {
        this.id = id;
        this.id_exersise = id_exersise;
        this.workout = workout;
        this.date = date;
        this.intensity = intensity;
        this.duration = duration;
    }

    public int getId_exersise() {
        return id_exersise;
    }

    public void setId_exersise(int id_exersise) {
        this.id_exersise = id_exersise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
