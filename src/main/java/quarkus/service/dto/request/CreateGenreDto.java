package quarkus.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDto(
        @NotBlank // validation not blank or not empty
        String name
) {
}
