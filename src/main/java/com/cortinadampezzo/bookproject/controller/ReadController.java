package com.cortinadampezzo.bookproject.controller;

import com.cortinadampezzo.bookproject.model.Read;
import com.cortinadampezzo.bookproject.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ReadController {

    @Autowired
    private ReadService readService;

    @PostMapping("/book/{id}/read")
    public void addRead(@PathVariable(value="id") Long id, Read read, HttpServletResponse response) throws IOException {

        readService.addNewRead(read.getStartDate(), read.getEndDate(), id);
        response.sendRedirect("/book/" + id);

    }

    @GetMapping("/read/{id}")
    public Read getRead(@PathVariable(value = "id") Long id) throws IOException {

        return readService.getReadById(id);

    }

    @RequestMapping(value = "book/{id}/read/{readId}/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void editRead(@PathVariable(value = "id") Long id, Read read, @PathVariable(value = "readId") Long readId, HttpServletResponse response) throws IOException {

        readService.editRead(readId, read.getStartDate(), read.getEndDate());
        response.sendRedirect("/book/" + id);

    }

    @PostMapping("book/{id}/read/{readId}/delete")
    public void deleteRead(@PathVariable(value = "id") Long id, @PathVariable(value = "readId") Long readId, HttpServletResponse response) throws IOException {

        readService.deleteRead(readId);
        response.sendRedirect("/book/" + id);

    }


}
