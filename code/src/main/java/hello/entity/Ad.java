package hello.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hello.service.PictureService;
import hello.util.PictureDeserializer;
import hello.util.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ad")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,  generator="seq_ad")
    @Column(name="ad_id")
    @JsonView(Views.User.class)
    private long id;

    @Column(name="description", nullable = false)
    @JsonView(Views.User.class)
    private String description;

    @Column(name="typology", nullable = false)
    @JsonView(Views.User.class)
    private String typology;

    @Column(name="house_size")
    @JsonView(Views.User.class)
    private Integer houseSize;

    @Column(name="garden_size")
    @JsonView(Views.User.class)
    private Integer gardenSize;

    @Column(name="evaluation")
    @JsonIgnore
    private Integer evaluation;

    @Column(name="dateIrrelevantChange")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(Views.Quality.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date dateIrrelevantChange;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ad_picture",
            joinColumns = { @JoinColumn(name = "ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "picture_id") })
    @JsonDeserialize(using = PictureDeserializer.class)
    @JsonView(Views.User.class)
    private List<Picture> pictures = new ArrayList<>();;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDateIrrelevantChange() {
        return dateIrrelevantChange;
    }

    public void setDateIrrelevantChange(Date dateIrrelevantChange) {
        this.dateIrrelevantChange = dateIrrelevantChange;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
