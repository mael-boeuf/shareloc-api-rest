package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class AchievedService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private ArrayList<User> to;
    @OneToOne
    private User from;
    private int date;   // date format : ddmmyyyy
    private String picture;
    private boolean valid;
    @OneToOne
    private Service service;

    public AchievedService(ArrayList<User> to, User from, int date, String picture, boolean valid, Service service) {
        this.to = to;
        this.from = from;
        this.date = date;
        this.picture = picture;
        this.valid = valid;
        this.service = service;
    }

    public AchievedService() {

    }

    public long getId() {
        return id;
    }

    public ArrayList<User> getTo() {
        return to;
    }

    public void addUserTo(User to) {
        this.to.add(to);
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "AchievedService{" +
                "to=" + to +
                ", from=" + from +
                ", date=" + date +
                ", picture='" + picture + '\'' +
                ", valid=" + valid +
                '}';
    }
}
