package com.cortinadampezzo.bookproject.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private Long rating;

    @Column(columnDefinition = "text")
    private String reviewText;

    @ManyToOne
    private Book book;

}
