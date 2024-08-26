package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.contract.InsuranceRequestService;
import br.com.challenge.insurance.domain.contract.InsuranceService;
import br.com.challenge.insurance.domain.entity.Insurance;
import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import br.com.challenge.insurance.repository.InsuranceRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final InsuranceRequestService insuranceRequestService;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, @Lazy InsuranceRequestService insuranceRequestService) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceRequestService = insuranceRequestService;
    }


    @Override
    public Insurance formalizeInsurance(UUID insuranceRequestId) {
        InsuranceRequest insuranceRequest = insuranceRequestService.findByExternalId(insuranceRequestId);

        Insurance insurance = new Insurance(insuranceRequest);
        return insuranceRepository.save(insurance);
    }
}
