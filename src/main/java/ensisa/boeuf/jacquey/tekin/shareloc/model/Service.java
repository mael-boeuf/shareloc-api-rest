package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table
@ApiModel(value="Service", description="Service model")
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

    @ApiModelProperty(value = "Gets ID of service")
    public long getId() {
        return id;
    }

    @ApiModelProperty(value = "Gets title of service")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Sets title of service", dataType = "String", required = true)
    public void setTitle(String title) {
        this.title = title;
    }

    @ApiModelProperty(value = "Gets description of service")
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "Sets description of service", dataType = "String", required = true)
    public void setDescription(String description) {
        this.description = description;
    }

    @ApiModelProperty(value = "Gets cost of service")
    public int getCost() {
        return cost;
    }

    @ApiModelProperty(value = "Sets cost of service", dataType = "int", required = true)
    public void setCost(int cost) {
        this.cost = cost;
    }

    @ApiModelProperty(value = "Gets colocation of service")
    public Colocation getColocation() {
        return colocation;
    }

    @ApiModelProperty(value = "Sets colocation of service", dataType = "Colocation", required = true)
    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    @ApiModelProperty(value = "Gets vote for of service")
    public int getForVote() {
        return forVote;
    }

    @ApiModelProperty(value = "Sets vote for of service", dataType = "int", required = true)
    public void setForVote(int forVote) {
        this.forVote = forVote;
    }

    @ApiModelProperty(value = "Gets vote against of service")
    public int getAgainstVote() {
        return againstVote;
    }

    @ApiModelProperty(value = "Sets vote against of service", dataType = "int", required = true)
    public void setAgainstVote(int againstVote) {
        this.againstVote = againstVote;
    }

    @ApiModelProperty(value = "Gets users voted in service")
    public ArrayList<User> getUsersVoted() {
        return usersVoted;
    }

    @ApiModelProperty(value = "Adds user voted in service", dataType = "User", required = true)
    public void addUserVoted(User user) {
        this.usersVoted.add(user);
    }

    @ApiModelProperty(value = "Gets number of votes in service")
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
