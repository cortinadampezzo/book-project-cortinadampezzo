package com.cortinadampezzo.bookproject.service;

import com.cortinadampezzo.bookproject.model.Book;
import com.cortinadampezzo.bookproject.model.Read;
import com.cortinadampezzo.bookproject.repository.ReadRepo;
import com.cortinadampezzo.bookproject.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ReadRepo readRepo;

    public List<Read> getReadList() {
        return readRepo.findAll(Sort.by(Sort.Order.desc("endDate"), Sort.Order.desc("startDate")));
    }

    public Read getReadById(Long id) {
        return readRepo.getById(id);
    }

    public List<Read> getReadByBook(Long id) {
        return bookRepo.getById(id).getReads();
    }

    public Read addNewRead(String startDate, String endDate, Long id) {

        Book book = bookRepo.getById(id);

        Read read = Read.builder()
                .startDate(startDate)
                .endDate(endDate)
                .book(book)
                .build();
        readRepo.saveAndFlush(read);

        book.setAlreadyRead(true);
        bookRepo.saveAndFlush(book);

        return read;

    }

    public Read editRead(Long id, String startDate, String endDate) {

        Read read = readRepo.getById(id);
        read.setStartDate(startDate);
        read.setEndDate(endDate);
        readRepo.saveAndFlush(read);
        return read;
    }

    public void deleteRead(Long id) {

        readRepo.deleteById(id);

    }

}
