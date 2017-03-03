package models.pojo;


/**
 * Created by Olga on 12.12.2016.
 */
public class Exersise extends Table {
    private String name;
    private int id;
    private int repeats;
    private int sets;
    public Exersise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public Exersise(int id, String name, int sets, int repeats) {
        this.id = id;
        this.name= name;
        this.sets = sets;
        this.repeats = repeats;

    }




    }







