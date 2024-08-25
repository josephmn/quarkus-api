package quarkus.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateGenreDto(
        @NotBlank
        String name
) {
}
