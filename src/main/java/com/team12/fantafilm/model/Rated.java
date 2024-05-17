package com.team12.fantafilm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
//@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rated")
public class Rated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "rated",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> listMovie;

    @Column(name = "description", length = 560)
    private String description;
}
