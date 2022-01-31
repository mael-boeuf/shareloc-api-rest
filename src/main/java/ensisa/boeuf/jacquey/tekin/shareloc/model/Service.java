package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.*;

@Entity
@Table
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private int cost;

    public Service(String title, String description, int cost) {
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

    public Service() {
        super();
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
}
