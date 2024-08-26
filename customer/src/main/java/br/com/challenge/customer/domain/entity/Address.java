package br.com.challenge.customer.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "external_id", nullable = false)
    private UUID externalId;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "address_number", nullable = false)
    private String addressNumber;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "complement")
    private String complement;

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;

    public Address(String street, String addressNumber, String zipCode, String complement, Customer customer) {
        this.externalId = UUID.randomUUID();
        this.street = street;
        this.addressNumber = addressNumber;
        this.zipCode = zipCode;
        this.complement = complement;
        this.customer = customer;
    }

    public Address() {}

    public Long getAddressId() {
        return addressId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getStreet() {
        return street;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
