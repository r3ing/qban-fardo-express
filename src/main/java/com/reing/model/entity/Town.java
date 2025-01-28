package com.reing.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "town")
@NoArgsConstructor
@AllArgsConstructor
public class Town {

    @Id
    @Column(name = "id_town")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTown;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
}
