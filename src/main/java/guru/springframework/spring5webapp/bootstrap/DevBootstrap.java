package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Publisher harper = new Publisher("Harper Collins", "Hanoi");
        publisherRepository.save(harper);

        Author eric = new Author("Erric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);

        Author rod = new Author("Rod", "Johnson");
        Publisher morx = new Publisher("Morx", "HCM");
        Book noEJB = new Book("J2EE Development without EJB", "23444", morx);
        rod.getBooks().add(noEJB);

        authorRepository.save(eric);
        authorRepository.save(rod);
        publisherRepository.save(harper);
        publisherRepository.save(morx);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);
    }

}
