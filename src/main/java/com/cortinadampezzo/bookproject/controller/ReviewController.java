package com.cortinadampezzo.bookproject.controller;

import com.cortinadampezzo.bookproject.model.Review;
import com.cortinadampezzo.bookproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/book/{id}/review")
    public void addReview(@PathVariable(value="id") Long id, Review review, HttpServletResponse response) throws IOException {

        reviewService.addNewReview(review.getRating(), review.getReviewText(), id);
        response.sendRedirect("/book/" + id);

    }

    @GetMapping("/review/{id}")
    public Review getReview(@PathVariable(value = "id") Long id) throws IOException {

        return reviewService.getReviewById(id);

    }


    @RequestMapping(value = "book/{id}/review/{reviewId}/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void editReview(@PathVariable(value = "id") Long id, Review review, @PathVariable(value = "reviewId") Long reviewId, HttpServletResponse response) throws IOException {

        reviewService.editReview(reviewId, review.getRating(), review.getReviewText());
        response.sendRedirect("/book/" + id);

    }

    @PostMapping("book/{id}/review/{reviewId}/delete")
    public void deleteReview(@PathVariable(value = "id") Long id, @PathVariable(value = "reviewId") Long reviewId, HttpServletResponse response) throws IOException {

        reviewService.deleteReview(reviewId);
        response.sendRedirect("/book/" + id);

    }


}