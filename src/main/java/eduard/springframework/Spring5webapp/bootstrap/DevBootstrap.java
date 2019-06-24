package eduard.springframework.Spring5webapp.bootstrap;

import eduard.springframework.Spring5webapp.model.Author;
import eduard.springframework.Spring5webapp.model.Book;
import eduard.springframework.Spring5webapp.model.Publisher;
import eduard.springframework.Spring5webapp.repositories.AuthorRepository;
import eduard.springframework.Spring5webapp.repositories.BookRepository;
import eduard.springframework.Spring5webapp.repositories.PublishereRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublishereRepository publishereRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublishereRepository publishereRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publishereRepository = publishereRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publishereRepository.save(publisher);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rot = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher);
        rot.getBooks().add(noEJB);

        authorRepository.save(rot);
        bookRepository.save(noEJB);

    }
}
