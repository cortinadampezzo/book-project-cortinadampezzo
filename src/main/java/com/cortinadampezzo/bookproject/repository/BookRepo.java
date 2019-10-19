package com.cortinadampezzo.bookproject.repository;

import com.cortinadampezzo.bookproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;


public interface BookRepo extends JpaRepository<Book, Long> {

    Book getById(Long id);

    Streamable<Book> findByAuthorContainingIgnoreCase(String author);

    Streamable<Book> findByTitleContainingIgnoreCase(String title);

    Streamable<Book> findByPublisherContainingIgnoreCase(String publisher);

}
