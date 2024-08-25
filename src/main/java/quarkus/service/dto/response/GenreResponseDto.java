package quarkus.service.dto.response;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record GenreResponseDto(Long id, String name) {
}
