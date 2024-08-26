package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.contract.CustomerService;
import br.com.challenge.insurance.domain.contract.InsuranceRequestService;
import br.com.challenge.insurance.domain.contract.InsuranceService;
import br.com.challenge.insurance.domain.entity.Customer;
import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import br.com.challenge.insurance.enums.InsuranceType;
import br.com.challenge.insurance.repository.InsuranceRequestRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
    public class InsuranceRequestServiceImpl implements InsuranceRequestService {
   private final CustomerService customerService;
   private final InsuranceRequestRepository insuranceRequestRepository;
   private final InsuranceService insuranceService;

    public InsuranceRequestServiceImpl(CustomerService customerService, InsuranceRequestRepository insuranceRequestRepository, InsuranceService insuranceService) {
        this.customerService = customerService;
        this.insuranceRequestRepository = insuranceRequestRepository;
        this.insuranceService = insuranceService;
    }

    @Override
    public InsuranceRequest simulateInsurance(UUID customerId, InsuranceType insuranceType, BigDecimal amountToReceive) {
        Customer customer = customerService.findById(customerId);
        LocalDate validUntil = calculateValitUntil(insuranceType);
        BigDecimal amountToBePaid = calculateAmountToBePaid(insuranceType, amountToReceive);

        InsuranceRequest insuranceRequest = new InsuranceRequest(
                customer,
                amountToBePaid,
                amountToReceive,
                validUntil,
                insuranceType
        );
        return insuranceRequestRepository.save(insuranceRequest);
    }

    @Override
    public InsuranceRequest findByExternalId(UUID externalId) {
        return insuranceRequestRepository.findByExternalId(externalId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void formalizeRequest(UUID insuranceRequestId) {
        insuranceService.formalizeInsurance(insuranceRequestId);
    }

    private LocalDate calculateValitUntil(InsuranceType insuranceType) {
        return LocalDate.now().plusDays(365L * insuranceType.getValidFor());
    }

    private BigDecimal calculateAmountToBePaid(InsuranceType insuranceType, BigDecimal amountToReceive) {
        return amountToReceive.multiply(insuranceType.getTaxRate());
    }
}
