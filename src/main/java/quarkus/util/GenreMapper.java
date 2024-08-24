package quarkus.util;

import quarkus.model.Genre;
import quarkus.service.dto.CreateGenreDto;
import quarkus.service.dto.UpdateGenreDto;

public interface GenreMapper {

    Genre fromCreate(CreateGenreDto dto);

    void update(UpdateGenreDto dto, Genre genre);

}
