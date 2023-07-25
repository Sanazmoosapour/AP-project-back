package Objects;

import java.util.ArrayList;
import java.util.List;

public class user {
    private String name="";
    private String familyName="";
    private transient String password="";
    private String email="";
    private double cash;
    private List<book> books=new ArrayList<>();

    public user() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public List<book> getBooks() {
        return books;
    }

    public void setBooks(List<book> books) {
        this.books = books;
    }
}
