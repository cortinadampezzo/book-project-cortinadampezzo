package com.cortinadampezzo.bookproject.repository;

import com.cortinadampezzo.bookproject.model.Read;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReadRepo extends JpaRepository<Read, Long> {

    Read getById(Long id);

}
