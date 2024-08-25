package quarkus.util;

import quarkus.model.Genre;
import quarkus.service.dto.request.CreateGenreDto;
import quarkus.service.dto.request.UpdateGenreDto;
import quarkus.service.dto.response.GenreResponseDto;

public interface GenreMapper {

    Genre fromCreate(CreateGenreDto dto);

    void update(UpdateGenreDto dto, Genre genre);

    GenreResponseDto present(Genre g);
}
