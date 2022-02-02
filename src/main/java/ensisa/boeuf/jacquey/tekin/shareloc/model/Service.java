package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private int cost;
    @OneToOne
    private Colocation colocation;
    private int forVote;
    private int againstVote;
    @OneToMany
    private ArrayList<User> usersVoted;

    public Service(String title, String description, int cost, Colocation colocation) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.colocation = colocation;
        this.forVote = 0;
        this.againstVote = 0;
        this.usersVoted = new ArrayList<>();
    }

    public Service() {

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Colocation getColocation() {
        return colocation;
    }

    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    public int getForVote() {
        return forVote;
    }

    public void setForVote(int forVote) {
        this.forVote = forVote;
    }

    public int getAgainstVote() {
        return againstVote;
    }

    public void setAgainstVote(int againstVote) {
        this.againstVote = againstVote;
    }

    public ArrayList<User> getUsersVoted() {
        return usersVoted;
    }

    public void addUserVoted(User user) {
        this.usersVoted.add(user);
    }

    public boolean countingVote() {
        return forVote > againstVote;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
