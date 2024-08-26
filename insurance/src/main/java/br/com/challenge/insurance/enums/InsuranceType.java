package br.com.challenge.insurance.enums;

import java.math.BigDecimal;

public enum InsuranceType {
    GOLD(new BigDecimal("0.4"), 40),
    SILVER(new BigDecimal("0.6"), 20 ),
    BRONZE(new BigDecimal("0.8"), 10);

    private final BigDecimal taxRate;

    private final Integer validFor;

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public Integer getValidFor() {
        return validFor;
    }

    InsuranceType(BigDecimal taxRate, Integer validFor) {
        this.taxRate = taxRate;
        this.validFor = validFor;
    }
}
