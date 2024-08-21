package quarkus.web;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.model.Book;
import quarkus.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository bookRepository;

    @GET
    public List<Book> index() {
        return bookRepository.listAll();
    }

    @GET
    @Path("/filter")
    public List<Book> filter() {
        return bookRepository.list("genre", "Tragicomedia");
    }

    @GET
    @Path("/page")
    public List<Book> filterPage() {
        return bookRepository.list("numPages >= 400");
    }

    @GET
    @Path("/pageparam")
    public List<Book> filterPageParam(@QueryParam("numPages") Integer numPages) {
        if (numPages == null) {
            return bookRepository.listAll();
        } else {
            return bookRepository.list("numPages >= ?1", numPages);
        }
        //return bookRepository.list("numPages >= 400");
    }

    @GET
    @Path("/title")
    public List<Book> filterTitle(@QueryParam("q") String query) {
        if (query == null) {
            return bookRepository.listAll(Sort.by("pubDate", Sort.Direction.Descending));
        } else {
            String filter = "%" + query + "%";
            Sort crit = Sort.by("pubDate", Sort.Direction.Ascending);
            return bookRepository.list("title ILIKE ?1 OR description ILIKE ?2", crit, filter, filter);
        }
    }

    @POST
    public Book insert(Book insertBook) {
        bookRepository.persist(insertBook);
        return insertBook;
    }

    @GET
    @Path("{id}")
    public Book retrive(@PathParam("id") Long id) {
        var book = bookRepository.findById(id);
        if (book != null) {
            return book;
        }
        throw new NoSuchElementException("No hay libro con el Id " + id + ".");
    }

    // Primera forma para borrar
    /*@DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Long id) {
        if (bookRepository.deleteById(id)) {
            return "Se ha borrado bien";
        } else {
            return "No se ha podido borrar";
        }
    }*/

    // Borrado silencioso
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        bookRepository.deleteById(id);
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id, Book book) {
        var updateBook = bookRepository.findById(id);
        if (updateBook != null) {
            updateBook.setTitle(book.getTitle());
            updateBook.setNumPages(book.getNumPages());
            updateBook.setPubDate(book.getPubDate());
            updateBook.setDescription(book.getDescription());
            bookRepository.persist(updateBook);
            return updateBook;
        }
        throw new NoSuchElementException("No hay libro con el Id " + id + ".");
    }

}
