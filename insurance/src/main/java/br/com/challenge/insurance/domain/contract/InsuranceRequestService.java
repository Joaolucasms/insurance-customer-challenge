package br.com.challenge.insurance.domain.contract;

import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import br.com.challenge.insurance.enums.InsuranceType;

import java.math.BigDecimal;
import java.util.UUID;

public interface InsuranceRequestService {

    InsuranceRequest simulateInsurance(UUID customerId, InsuranceType insuranceType, BigDecimal amountToReceive);

    InsuranceRequest findByExternalId(UUID externalId);

    void formalizeRequest(UUID insuranceRequestId);
}
