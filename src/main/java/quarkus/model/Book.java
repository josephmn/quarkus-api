package quarkus.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book extends PanacheEntity {
    private String title;
    private int numPages;
    private LocalDate pubDate;
    private String description;
}
