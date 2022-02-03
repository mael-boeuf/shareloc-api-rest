package ensisa.boeuf.jacquey.tekin.shareloc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@ApiModel(value="Image", description="Image model")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] bytes;

    public Image() {
    }

    public Image(byte[] bytes) {
        this.bytes = bytes;
    }

    @ApiModelProperty(value = "Gets ID of image")
    public Long getId() {
        return id;
    }

    @ApiModelProperty(value = "Sets ID of image", dataType = "Long", required = true)
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ",bytes=" + Arrays.toString(bytes) +
                '}';
    }

}
