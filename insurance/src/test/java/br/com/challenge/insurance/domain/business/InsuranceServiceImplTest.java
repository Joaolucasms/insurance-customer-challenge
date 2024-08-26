package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.contract.InsuranceRequestService;
import br.com.challenge.insurance.domain.entity.Customer;
import br.com.challenge.insurance.domain.entity.Insurance;
import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import br.com.challenge.insurance.enums.InsuranceType;
import br.com.challenge.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InsuranceServiceImplTest {

    @Mock
    private InsuranceRepository insuranceRepository;
    @Mock
    private InsuranceRequestService insuranceRequestService;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Test
    void formalizeInsurance() {
        InsuranceRequest insuranceRequest = createInsuranceRequest();
        Insurance insurance = createInsurance();

        when(insuranceRequestService.findByExternalId(insuranceRequest.getExternalId())).thenReturn(insuranceRequest);
        when(insuranceRepository.save(any())).thenReturn(insurance);

        Insurance newInsurance = insuranceService.formalizeInsurance(insuranceRequest.getExternalId());

        assertEquals(insurance.getAmountToBePaid(), newInsurance.getAmountToBePaid());
        assertEquals(insurance.getType(), newInsurance.getType());
        assertEquals(insurance.getAmountToReceive(), newInsurance.getAmountToReceive());
        assertEquals(insurance.getValidUntil(), newInsurance.getValidUntil());

        verify(insuranceRequestService, times(1)).findByExternalId(insuranceRequest.getExternalId());
        verify(insuranceRepository, times(1)).save(any());
    }

    private Insurance createInsurance(){
        InsuranceRequest insuranceRequest = createInsuranceRequest();
        return new Insurance(insuranceRequest);
    }

    private InsuranceRequest createInsuranceRequest() {
        return new InsuranceRequest(
                createCustomer(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                LocalDate.now(),
                InsuranceType.BRONZE
        );
    }

    private Customer createCustomer() {
        return new Customer(UUID.randomUUID());
    }
}