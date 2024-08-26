package br.com.challenge.insurance.domain.entity;

import br.com.challenge.insurance.enums.InsuranceStatus;
import br.com.challenge.insurance.enums.InsuranceType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false)
    private UUID externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "insurance_request_id", nullable = false)
    private InsuranceRequest insuranceRequest;

    @Column(name = "amount_to_be_paid", nullable = false)
    private BigDecimal amountToBePaid;

    @Column(name = "amount_to_receive", nullable = false)
    private BigDecimal amountToReceive;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceType type;

    @Column(name = "status", nullable = false)
    private InsuranceStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Insurance() {}

    public Insurance(InsuranceRequest insuranceRequest) {
        this.customer = insuranceRequest.getCustomer();
        this.insuranceRequest = insuranceRequest;
        this.amountToBePaid = insuranceRequest.getAmountToBePaid();
        this.amountToReceive = insuranceRequest.getAmountToReceive();
        this.validUntil = insuranceRequest.getValidUntil();
        this.type = insuranceRequest.getType();
        this.status = InsuranceStatus.CURRENT;
        this.createdAt = LocalDateTime.now();
        this.externalId = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public InsuranceRequest getInsuranceRequest() {
        return insuranceRequest;
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

    public InsuranceStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(InsuranceStatus status) {
        this.status = status;
    }
}
