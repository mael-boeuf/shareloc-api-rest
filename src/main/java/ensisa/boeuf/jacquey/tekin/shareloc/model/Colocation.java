package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Colocation {

    @Id
    private String name;

    public Colocation(String name) {
        this.name = name;
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
}
