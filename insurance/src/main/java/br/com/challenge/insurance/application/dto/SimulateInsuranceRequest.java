package br.com.challenge.insurance.application.dto;

import br.com.challenge.insurance.enums.InsuranceType;

import java.math.BigDecimal;
import java.util.UUID;

public record SimulateInsuranceRequest(
        UUID customerId,
        InsuranceType insuranceType,
        BigDecimal amountToReceive
){}
