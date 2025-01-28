package com.reing.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "province")
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @Column(name = "id_province")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProvince;

    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Town> towns;

    public Long getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Long idProvince) {
        this.idProvince = idProvince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
