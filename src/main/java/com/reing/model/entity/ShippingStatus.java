package com.reing.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping_status")
@NoArgsConstructor
@AllArgsConstructor
public class ShippingStatus {

    @Id
    @Column(name = "id_shipping_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShippingStatus;

    private String status;

    public Long getIdShippingStatus() {
        return idShippingStatus;
    }

    public void setIdShippingStatus(Long idShippingStatus) {
        this.idShippingStatus = idShippingStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
