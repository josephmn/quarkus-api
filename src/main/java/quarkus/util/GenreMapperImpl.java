package quarkus.util;

import jakarta.enterprise.context.RequestScoped;
import quarkus.model.Genre;
import quarkus.service.dto.CreateGenreDto;
import quarkus.service.dto.UpdateGenreDto;

@RequestScoped
public class GenreMapperImpl implements GenreMapper {
    @Override
    public Genre fromCreate(CreateGenreDto dto) {
        var g = new Genre();
        g.setName(dto.name());
        return g;
    }

    @Override
    public void update(UpdateGenreDto dto, Genre genre) {
        genre.setName(dto.name());
    }
}
