package quarkus.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@FilterDef(
        name = "name.like",
        parameters = @ParamDef(name = "name", type = String.class)
)
@Filter(name = "name.like", condition = "LOWER(name) LIKE LOWER(:name)")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @JsonProperty("genreName")
//    @JsonAlias({"genreName", "name"})
    private String name;
    @CreationTimestamp
//    @JsonIgnore
    private LocalDateTime createAt;
    @UpdateTimestamp
//    @JsonIgnore
    private LocalDateTime updateAt;

    public int getClassification() {
        return name.length();
    }
}
