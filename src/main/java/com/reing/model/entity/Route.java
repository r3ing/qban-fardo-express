package com.reing.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "route")
@NoArgsConstructor
@AllArgsConstructor
public class Route implements Serializable {

    @Id
    @Column(name = "id_route")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoute;

    @Column(name = "route_url")
    private String routeUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "route_has_rol",
            joinColumns = @JoinColumn(name = "id_route"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles;

    public Long getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
