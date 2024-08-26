package br.com.challenge.insurance.domain.contract;

import br.com.challenge.insurance.domain.entity.Insurance;

import java.util.UUID;

public interface InsuranceService {
    Insurance formalizeInsurance(UUID insuranceRequestId);
}
