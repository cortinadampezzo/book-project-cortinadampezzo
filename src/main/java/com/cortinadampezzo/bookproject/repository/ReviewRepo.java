package com.cortinadampezzo.bookproject.repository;

import com.cortinadampezzo.bookproject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepo extends JpaRepository<Review, Long> {

    Review getById(Long id);

}

