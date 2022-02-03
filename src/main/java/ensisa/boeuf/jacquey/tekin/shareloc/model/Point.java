package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value="Point", description="Point model")
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

    @ApiModelProperty(value = "Gets ID of point")
    public Long getId() {
        return id;
    }

    @ApiModelProperty(value = "Sets ID of point", dataType = "Long", required = true)
    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty(value = "Gets point")
    public int getPoint() {
        return point;
    }

    @ApiModelProperty(value = "Sets point", dataType = "int", required = true)
    public void setPoint(int point) {
        this.point = point;
    }

    @ApiModelProperty(value = "Gets colocation")
    public Colocation getColocation() {
        return colocation;
    }

    @ApiModelProperty(value = "Sets colocation", dataType = "Colocation", required = true)
    public void setColocation(Colocation colocation) {
        this.colocation = colocation;
    }

    @ApiModelProperty(value = "Adds point", dataType = "int", required = true)
    public void addPoint(int delta){
        this.point += delta;
    }

    @ApiModelProperty(value = "Decreases point", dataType = "int", required = true)
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
