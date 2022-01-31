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
        this.members = new ArrayList<>();
        this.services = new ArrayList<>();
        this.achievedServices = new ArrayList<>();
        addMember(manager);
    }

    public Colocation() {

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

    public void addMember(User member) {
        this.members.add(member);
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public ArrayList<AchievedService> getAchievedServices() {
        return achievedServices;
    }

    public void addAchievedService(AchievedService achievedService) {
        this.achievedServices.add(achievedService);
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
