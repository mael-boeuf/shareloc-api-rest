package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class AchievedService {

    @OneToOne
    private User to;
    @Id
    @OneToOne
    private User from;
    private int date;   // date format : ddmmyyyy
    private String picture;
    private boolean valid;

    public AchievedService(User to, User from, int date, String picture, boolean valid) {
        this.to = to;
        this.from = from;
        this.date = date;
        this.picture = picture;
        this.valid = valid;
    }

    public AchievedService() {
        super();
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
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
