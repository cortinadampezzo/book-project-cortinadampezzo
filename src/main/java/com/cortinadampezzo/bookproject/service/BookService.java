package com.cortinadampezzo.bookproject.service;

import com.cortinadampezzo.bookproject.model.Book;
import com.cortinadampezzo.bookproject.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book getBookById(Long book) {
        return bookRepo.getById(book);
    }

    public int getNumberOfBooks() {
        return bookRepo.findAll().size();
    }

    public Streamable<Book> getBookBySearchKeyword(String keyword) {
        return bookRepo.findByAuthorContainingIgnoreCase(keyword).and(bookRepo.findByTitleContainingIgnoreCase(keyword).and(bookRepo.findByPublisherContainingIgnoreCase(keyword)));
    }

    public Page<Book> getBookList(int page, int size) {
        return bookRepo.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("date"), Sort.Order.asc("author"), Sort.Order.asc("title"))));
    }

    public Book addNewBook(String author, String title, String publisher, Long year, Long pages, Long isbn, String date, String place, boolean alreadyRead, boolean owned) {
        Book book = Book.builder()
                .author(author)
                .title(title)
                .publisher(publisher)
                .year(year)
                .pages(pages)
                .isbn(isbn)
                .date(date)
                .place(place)
                .alreadyRead(alreadyRead)
                .owned(owned)
                .build();
        bookRepo.saveAndFlush(book);
        return book;
    }

    public Book editBook(Long id, String author, String title, String publisher, Long year, Long pages, Long isbn, String date, String place, boolean alreadyRead, boolean owned) {
        Book book = bookRepo.getById(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPages(pages);
        book.setIsbn(isbn);
        book.setDate(date);
        book.setPlace(place);
        book.setAlreadyRead(alreadyRead);
        book.setOwned(owned);
        bookRepo.saveAndFlush(book);
        return book;
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public List<Book> sortBooks(String sortBy, Sort.Direction orderBy) {
        return bookRepo.findAll(Sort.by(orderBy, sortBy));
    }

    public void setOwned(Long id) {
        Book book = bookRepo.getById(id);
        book.setOwned(true);
        bookRepo.saveAndFlush(book);
    }

    public void setNotOwned(Long id) {
        Book book = bookRepo.getById(id);
        book.setOwned(false);
        bookRepo.saveAndFlush(book);
    }

}
