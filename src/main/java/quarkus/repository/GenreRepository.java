package quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.model.Genre;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
}
