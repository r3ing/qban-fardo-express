package com.reing.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipping")
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {

    @Id
    @Column(name = "id_shipping")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipping;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(precision = 10, scale = 2)
    private BigDecimal  amount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    private String details;
    private String articles;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_shipping_status")
    private ShippingStatus shippingStatus;

}
