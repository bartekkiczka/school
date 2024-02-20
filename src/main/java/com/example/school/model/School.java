package com.example.school.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SCHOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hour_price")
    private Float hourPrice;

}
