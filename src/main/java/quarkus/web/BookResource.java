package quarkus.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import quarkus.model.Book;
import quarkus.repository.BookRepository;

import java.util.List;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository repo;

    @GET
    public List<Book> index() {
        return repo.listAll();
    }

    @POST
    public Book insert(Book insertBook) {
        //assert insertBook.getId() == null;
        repo.persist(insertBook);
        //assert insertBook.getId() != null;
        return insertBook;
    }
}
