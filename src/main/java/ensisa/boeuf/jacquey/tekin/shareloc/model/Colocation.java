package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class Colocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany
    private ArrayList<User> members;
    @OneToOne
    private User manager;
    @OneToMany
    private ArrayList<Service> services;
    @OneToMany
    private ArrayList<AchievedService> achievedServices;

    public Colocation(String name, User manager) {
        this.name = name;
        this.manager = manager;
    }

    public Colocation() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public User getManager() {
        return manager;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<AchievedService> getAchievedServices() {
        return achievedServices;
    }

    @Override
    public String toString() {
        return "Colocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
