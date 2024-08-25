package quarkus.service.dto.response;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record PaginatedResponse<E>(int page, int totalPages, List<E> data) {
    public PaginatedResponse(PanacheQuery<E> query) {
        this(query.page().index + 1, query.pageCount(), query.list());
    }
}
