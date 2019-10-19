package com.cortinadampezzo.bookproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String title;
    private String publisher;
    private Long year;
    private Long pages;
    private Long isbn;
    private String date;
    private String place;
    private boolean alreadyRead;
    private boolean owned;

    @Singular
    @JsonIgnoreProperties({"book"})
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Read> reads;

    @Singular
    @JsonIgnoreProperties({"book"})
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
