package ensisa.boeuf.jacquey.tekin.shareloc.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int point;
    @OneToOne
    private Colocation colocation;

    public Point(Colocation colocation, int point) {
        this.colocation = colocation;
        this.point = point;
    }

    public Point() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Colocation getColocation() {
        return colocation;
    }

    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    public void addPoint(int delta){
        this.point += delta;
    }

    public void decreaseScore(int delta){
        this.point -= delta;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", point=" + point +
                ", colocation=" + colocation +
                '}';
    }

}
