package quarkus.web;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.model.Genre;
import quarkus.repository.GenreRepository;
import quarkus.service.dto.request.CreateGenreDto;
import quarkus.service.dto.request.UpdateGenreDto;
import quarkus.service.dto.response.GenreResponseDto;
import quarkus.util.GenreMapper;
import quarkus.service.dto.response.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genre")
public class GenreResource {

    private final GenreRepository genreRepository;
    private final GenreMapper mapper;

    @Inject
    public GenreResource(GenreRepository genreRepository, GenreMapper mapper) {
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @GET
    public List<Genre> list() {
        return genreRepository.listAll();
    }

    @GET
    @Path("/page")
    public List<Genre> listPage(
            @QueryParam("p") @DefaultValue("1") int page
    ) {
        Page p = new Page(page - 1,5);
        return genreRepository.findAll(Sort.descending("createAt"))
                .page(p)
                .list();
    }

    @GET
    @Path("/pageResponse")
    public PaginatedResponse<Genre> listPageResponse(
            @QueryParam("p") @DefaultValue("1") int page,
            @QueryParam("q") String q
    ) {
        Page p = new Page(page - 1,5);
        var query = genreRepository.findAll(Sort.descending("createAt"));

        if (q != null) {
            var nameLike = "%" + q + "%";
            query.filter("name.like", Parameters.with("name", nameLike));
        }
        query.page(p);
        return new PaginatedResponse<>(query);
    }

    @GET
    @Path("/pageNewResponse")
    public PaginatedResponse<GenreResponseDto> listPageNewResponse(
            @QueryParam("p") @DefaultValue("1") int page,
            @QueryParam("q") String q
    ) {
        PanacheQuery<Genre> query = genreRepository.findPage(page);
        PanacheQuery<GenreResponseDto> present = query.project(GenreResponseDto.class);
        if (q != null) {
            var nameLike = "%" + q + "%";
            present.filter("name.like", Parameters.with("name", nameLike));
        }
        return new PaginatedResponse<>(present);
    }

    @POST
    @Transactional
    public Response create(CreateGenreDto genre) {
        var entity = mapper.fromCreate(genre);
        genreRepository.persist(entity);
        var representation = mapper.present(entity);
        return Response.created(URI.create("/genre" + entity.getId())).entity(representation).build();
    }

    @GET
    @Path("{id}")
    public GenreResponseDto get(@PathParam("id") Long id) {
        return genreRepository
                .findByIdOptional(id)
                .map(mapper::present)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + ", not found"));
    }

    @PUT
    @Path("{id}")
    @Transactional
    public GenreResponseDto update(@PathParam("id") Long id, UpdateGenreDto inbox) {
        Genre found = genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + ", not found"));
        mapper.update(inbox, found);
        genreRepository.persist(found);
        return mapper.present(found);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        genreRepository.deleteById(id);
    }
}
