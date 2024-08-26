package br.com.challenge.insurance.domain.entity;

import br.com.challenge.insurance.enums.InsuranceType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "insurance_request")
public class InsuranceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private UUID externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "amount_to_be_paid", nullable = false)
    private BigDecimal amountToBePaid;

    @Column(name = "amount_to_receive", nullable = false)
    private BigDecimal amountToReceive;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceType type;

    public InsuranceRequest(Customer customer, BigDecimal amountToBePaid, BigDecimal amountToReceive, LocalDate validUntil, InsuranceType type) {
        this.customer = customer;
        this.amountToBePaid = amountToBePaid;
        this.amountToReceive = amountToReceive;
        this.validUntil = validUntil;
        this.type = type;
    }
    public InsuranceRequest(){}

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getAmountToBePaid() {
        return amountToBePaid;
    }

    public BigDecimal getAmountToReceive() {
        return amountToReceive;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public InsuranceType getType() {
        return type;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmountToBePaid(BigDecimal amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public void setAmountToReceive(BigDecimal amountToReceive) {
        this.amountToReceive = amountToReceive;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public void setType(InsuranceType type) {
        this.type = type;
    }
}
