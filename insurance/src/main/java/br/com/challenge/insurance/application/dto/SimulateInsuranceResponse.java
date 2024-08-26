package br.com.challenge.insurance.application.dto;

import br.com.challenge.insurance.enums.InsuranceType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SimulateInsuranceResponse(
        UUID id,
        UUID customerId,
        InsuranceType insuranceType,
        BigDecimal amountToReceive,
        BigDecimal amountToBePaid,
        LocalDate validUntil
) {
}
