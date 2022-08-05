package MyBnB.models.basic;

import java.io.Serializable;
import java.time.LocalDate;

public class User {
    public enum Field {
        ID("id"),
        NAME("name"),
        BIRTHDATE("birthdate"),
        OCCUPATION("occupation"),
        SIN("sin");
        private final String value;
        Field (final String value) { this.value = value; }
        @Override
        public String toString() { return this.value; }
    }

    // fields names should be exact same as table columns
    private int id;
    private String name;
    private LocalDate birthdate;
    private String occupation;
    private String sin;

    public User() {
    }

    // need this for post request - to get data back in user format
    public User(int id, String name, LocalDate birthdate, String occupation, String sin){
        super();
        this.id = id;
        this.name=name;
        this.birthdate=birthdate;
        this.occupation=occupation;
        this.sin=sin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }
}
