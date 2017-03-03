package models.pojo;


import java.util.Date;

/**
 * Created by Olga on 12.12.2016.
 */
public class Person extends Table {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int id;
    private int id_phis;
    public Person() {
    }
    public Person(int id, String name, String  lastName, Date dateOfBirth, int id_phisics) {
        this.id = id;
        this.firstName= name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.id_phis= id_phisics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String firstName, String lastName, Date dateOfBirth, int id, int id_phis) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
        this.id_phis = id_phis;
    }







}
