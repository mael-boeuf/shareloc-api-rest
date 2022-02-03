package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table
@ApiModel(value="User", description="User model")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany
    private ArrayList<Colocation> colocations;
    @OneToMany
    private ArrayList<Point> points;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = new ArrayList<>();
    }

    public User() {

    }

    @ApiModelProperty(value = "Gets email of user")
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "Sets email of user", dataType = "String", required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "Gets password of user")
    public String getPassword() {
        return password;
    }

    @ApiModelProperty(value = "Sets password of user", dataType = "String", required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "Gets first name of user")
    public String getFirstName() {
        return firstName;
    }

    @ApiModelProperty(value = "Sets first name of user", dataType = "String", required = true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(value = "Gets last name of user")
    public String getLastName() {
        return lastName;
    }

    @ApiModelProperty(value = "Sets last name of user", dataType = "String", required = true)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(value = "Gets ID of user")
    public long getId() {
        return id;
    }

    @ApiModelProperty(value = "Gets colocations of user")
    public ArrayList<Colocation> getColocations() {
        return colocations;
    }

    @ApiModelProperty(value = "Adds colocation to user", dataType = "Colocation", required = true)
    public void addColocation(Colocation colocation) {
        this.colocations.add(colocation);
    }

    @ApiModelProperty(value = "Gets points of user")
    public ArrayList<Point> getPoints() {
        return this.points;
    }

    @ApiModelProperty(value = "Sets points of user", dataType = "ArrayList<Point>", required = true)
    public void setPoints(ArrayList<Point> points){
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
