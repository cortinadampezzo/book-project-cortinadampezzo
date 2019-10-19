package com.cortinadampezzo.bookproject.service;

import com.cortinadampezzo.bookproject.model.Book;
import com.cortinadampezzo.bookproject.model.Review;
import com.cortinadampezzo.bookproject.repository.BookRepo;
import com.cortinadampezzo.bookproject.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Review> getReviewList() {
        return reviewRepo.findAll(Sort.by(Sort.Order.desc("rating")));
    }

    public Review getReviewById(Long id) {
        return reviewRepo.getById(id);
    }

    public List<Review> getReviewByBook(Long id) {
        return bookRepo.getById(id).getReviews();
    }

    public Review addNewReview(Long rating, String reviewText, Long id) {

        Book book = bookRepo.getById(id);

        Review review = Review.builder()
                .rating(rating)
                .reviewText(reviewText)
                .book(book)
                .build();
        reviewRepo.saveAndFlush(review);

        return review;

    }

    public Review editReview(Long id, Long rating, String reviewText) {

        Review review = reviewRepo.getById(id);
        review.setRating(rating);
        review.setReviewText(reviewText);
        reviewRepo.saveAndFlush(review);
        return review;

    }

    public void deleteReview(Long id) {

        reviewRepo.deleteById(id);

    }

}
