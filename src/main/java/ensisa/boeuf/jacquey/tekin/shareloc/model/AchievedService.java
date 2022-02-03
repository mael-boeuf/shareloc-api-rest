package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Table
@ApiModel(value="AchievedService", description="Achieved service model")
public class AchievedService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private ArrayList<User> to;
    @OneToOne
    private User from;
    private Timestamp date;
    @OneToOne
    private Image picture;
    private boolean valid;
    @OneToOne
    private Service service;

    public AchievedService(ArrayList<User> to, User from, Timestamp date, Image picture, boolean valid, Service service) {
        this.to = to;
        this.from = from;
        this.date = date;
        this.picture = picture;
        this.valid = valid;
        this.service = service;
    }

    public AchievedService() {

    }

    @ApiModelProperty(value = "Gets ID of achieved service")
    public long getId() {
        return id;
    }

    @ApiModelProperty(value = "Gets beneficiaries of achieved service")
    public ArrayList<User> getTo() {
        return to;
    }

    @ApiModelProperty(value = "Add beneficiary user in achieved service", dataType = "User", required = true)
    public void addUserTo(User to) {
        this.to.add(to);
    }

    @ApiModelProperty(value = "Gets author of achieved service")
    public User getFrom() {
        return from;
    }

    @ApiModelProperty(value = "Sets author of achieved service", dataType = "User", required = true)
    public void setFrom(User from) {
        this.from = from;
    }

    @ApiModelProperty(value = "Gets declaration date of achieved service")
    public Timestamp getDate() {
        return date;
    }

    @ApiModelProperty(value = "Sets declaration date of achieved service", dataType = "Timestamp", required = true)
    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ApiModelProperty(value = "Gets picture of achieved service")
    public Image getPicture() {
        return picture;
    }

    @ApiModelProperty(value = "Sets picture of achieved service", dataType = "Image", required = true)
    public void setPicture(Image picture) {
        this.picture = picture;
    }

    @ApiModelProperty(value = "Gets validation of achieved service")
    public boolean isValid() {
        return valid;
    }

    @ApiModelProperty(value = "Sets validation of achieved service", dataType = "boolean", required = true)
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @ApiModelProperty(value = "Gets service of achieved service")
    public Service getService() {
        return service;
    }

    @ApiModelProperty(value = "Sets service of achieved service", dataType = "Service", required = true)
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
