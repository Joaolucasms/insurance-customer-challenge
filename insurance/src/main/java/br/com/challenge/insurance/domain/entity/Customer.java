package br.com.challenge.insurance.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private UUID externalId;

    public Customer(UUID externalId) {
        this.externalId = externalId;
    }

    public Customer() {}

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }
}
