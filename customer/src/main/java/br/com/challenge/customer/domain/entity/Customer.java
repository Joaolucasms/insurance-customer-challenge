package br.com.challenge.customer.domain.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private UUID externalId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(mappedBy = "customer")
    private Address address;

    public Customer (String name, Date birthDate, String phone) {
        this.externalId = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Customer() {}

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
