package com.cortinadampezzo.bookproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Read {

    @Id
    @GeneratedValue
    private Long id;

    private String startDate;
    private String endDate;

    @ManyToOne
    private Book book;

}
