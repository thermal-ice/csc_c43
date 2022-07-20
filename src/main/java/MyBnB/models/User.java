package MyBnB.models;

import java.io.Serializable;
import java.util.Date;

public class User {
    // fields names should be exact same as table columns
    private int id;
    private String name;
    private Date birthdate;
    private String occupation;
    private String sin;
    private int isActive;

    public User() {
        super();
    }

    // need this for post request - to get data back in user format
    public User(int id, String name, Date birthdate, String occupation, String sin, int isActive){
        super();
        this.id = id;
        this.name=name;
        this.birthdate=birthdate;
        this.occupation=occupation;
        this.sin=sin;
        this.isActive=isActive;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsActive() {
        return isActive;
    }
}
