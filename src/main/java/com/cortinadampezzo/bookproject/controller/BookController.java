package com.cortinadampezzo.bookproject.controller;

import com.cortinadampezzo.bookproject.model.Book;
import com.cortinadampezzo.bookproject.model.Read;
import com.cortinadampezzo.bookproject.model.Review;
import com.cortinadampezzo.bookproject.service.ReadService;
import com.cortinadampezzo.bookproject.service.BookService;
import com.cortinadampezzo.bookproject.service.ReviewService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReadService readService;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/")
    public String getBooks() throws IOException {

        Map<String, Object> context = new HashMap<>();
        context.put("books", bookService.getBookList(0, 2));
        context.put("reads", readService.getReadList());
        context.put("reviews", reviewService.getReviewList());
        context.put("numberOfPages", Math.round(bookService.getNumberOfBooks() / 2));

        String template = Resources.toString(Resources.getResource("templates/index.html"), Charsets.UTF_8);
        return new Jinjava().render(template, context);

    }

    @GetMapping("/list")
    public String getBooksByPage(@RequestParam("page") int page, @RequestParam("size") int size) throws IOException {

        Map<String, Object> context = new HashMap<>();
        context.put("books", bookService.getBookList(page, size));
        context.put("reads", readService.getReadList());
        context.put("reviews", reviewService.getReviewList());
        context.put("numberOfPages", Math.round(bookService.getNumberOfBooks() / size));

        String template = Resources.toString(Resources.getResource("templates/index.html"), Charsets.UTF_8);
        return new Jinjava().render(template, context);

    }


    @RequestMapping(value = "/add-new-book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void addNewBook(Book book, HttpServletResponse response) throws IOException {

        bookService.addNewBook(book.getAuthor(), book.getTitle(), book.getPublisher(), book.getYear(), book.getPages(), book.getIsbn(), book.getDate(), book.getPlace(), book.isAlreadyRead(), book.isOwned());
        response.sendRedirect("/");

    }

    @RequestMapping(value = "/book/{id}/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void editBook(@PathVariable(value = "id") Long id, Book book, HttpServletResponse response) throws IOException {

        bookService.editBook(id, book.getAuthor(), book.getTitle(), book.getPublisher(), book.getYear(), book.getPages(), book.getIsbn(), book.getDate(), book.getPlace(), book.isAlreadyRead(), book.isOwned());
        response.sendRedirect("/book/" + id);

    }

    @PostMapping("/book/{id}/delete")
    public void deleteBook(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {
        bookService.deleteBook(id);
        response.sendRedirect("/");
    }


    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(value = "id") Long id) throws IOException {

        Map<String, Object> context = new HashMap<>();
        Book book = bookService.getBookById(id);
        context.put("book_details", book);

        List<Read> read = readService.getReadByBook(id);
        context.put("read_details", read);

        List<Review> review = reviewService.getReviewByBook(id);
        context.put("review_details", review);

        String template = Resources.toString(Resources.getResource("templates/book_details.html"), Charsets.UTF_8);

        return new Jinjava().render(template, context);

    }

    @GetMapping("/sort")
    public String sortBooks(@RequestParam("sortBy") String sortBy, @RequestParam("orderBy") Sort.Direction orderBy) throws IOException {

        Map<String, Object> context = new HashMap<>();
        context.put("books", bookService.sortBooks(sortBy, orderBy));
        context.put("reads", readService.getReadList());
        context.put("reviews", reviewService.getReviewList());

        String template = Resources.toString(Resources.getResource("templates/index.html"), Charsets.UTF_8);

        return new Jinjava().render(template, context);

    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword) throws IOException {

        Map<String, Object> context = new HashMap<>();
        context.put("books", bookService.getBookBySearchKeyword(keyword));
        context.put("reads", readService.getReadList());
        context.put("reviews", reviewService.getReviewList());

        String template = Resources.toString(Resources.getResource("templates/index.html"), Charsets.UTF_8);

        return new Jinjava().render(template, context);

    }

    @RequestMapping(value = "/book/{id}/own", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void setOwnedBook(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {

        bookService.setOwned(id);
        response.sendRedirect("/");

    }

    @RequestMapping(value = "/book/{id}/notown", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void setNotOwnedBook(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {

        bookService.setNotOwned(id);
        response.sendRedirect("/");

    }

}
