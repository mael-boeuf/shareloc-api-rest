package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table
@ApiModel(value="Colocation", description="Colocation model")
public class Colocation implements Serializable {

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

    @ApiModelProperty(value = "Gets name of colocation")
    public String getName() {
        return name;
    }

    @ApiModelProperty(value = "Sets name of colocation", dataType = "String", required = true)
    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "Gets ID of colocation")
    public long getId() {
        return id;
    }

    @ApiModelProperty(value = "Gets members of colocation")
    public ArrayList<User> getMembers() {
        return members;
    }

    @ApiModelProperty(value = "Adds member in colocation", dataType = "User", required = true)
    public void addMember(User member) {
        this.members.add(member);
    }

    @ApiModelProperty(value = "Gets manager of colocation")
    public User getManager() {
        return manager;
    }

    @ApiModelProperty(value = "Sets manager of colocation", dataType = "User", required = true)
    public void setManager(User manager) {
        this.manager = manager;
    }

    @ApiModelProperty(value = "Gets services of colocation")
    public ArrayList<Service> getServices() {
        return services;
    }

    @ApiModelProperty(value = "Adds service in colocation", dataType = "Service", required = true)
    public void addService(Service service) {
        this.services.add(service);
    }

    @ApiModelProperty(value = "Gets achieved services of colocation")
    public ArrayList<AchievedService> getAchievedServices() {
        return achievedServices;
    }

    @ApiModelProperty(value = "Adds achieved service in colocation", dataType = "AchievedService", required = true)
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
