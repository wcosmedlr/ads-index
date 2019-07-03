package hello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="picture",
        indexes = {@Index(columnList="url")})
@Component
public class Picture {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_picture")
    @Column(name="picture_id")
    private long id;

    @Column(name="url", nullable = false)
    private String url;

    @Column(name="quality", nullable = false)
    private String quality;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "pictures")
    @JsonIgnore
    private List<Ad> ads;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
