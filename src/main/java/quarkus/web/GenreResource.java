package quarkus.web;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.model.Genre;
import quarkus.repository.GenreRepository;
import quarkus.util.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genre")
public class GenreResource {

    @Inject
    private GenreRepository genreRepository;

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
            query.filter("name.like", Parameters.with("name", q));
        }
        query.page(p);
        return new PaginatedResponse<>(query);
    }

    @POST
    @Transactional
    public Response create(Genre genre) {
        genreRepository.persist(genre);
        return Response.created(URI.create("/genre" + genre.getId())).entity(genre).build();
    }

    @GET
    @Path("{id}")
    public Genre get(@PathParam("id") Long id) {
        return genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + ", not found"));
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Genre update(@PathParam("id") Long id, Genre inbox) {
        Genre found = genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + ", not found"));
        found.setName(inbox.getName());
        genreRepository.persist(found);
        return found;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        genreRepository.deleteById(id);
    }
}
