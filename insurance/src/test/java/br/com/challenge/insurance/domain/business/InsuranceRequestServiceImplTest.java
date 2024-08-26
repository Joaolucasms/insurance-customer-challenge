package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.contract.CustomerService;
import br.com.challenge.insurance.domain.contract.InsuranceService;
import br.com.challenge.insurance.domain.entity.Customer;
import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import br.com.challenge.insurance.enums.InsuranceType;
import br.com.challenge.insurance.repository.InsuranceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InsuranceRequestServiceImplTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private InsuranceRequestRepository insuranceRequestRepository;
    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceRequestServiceImpl insuranceRequestService;


    @Test
    void simulateInsurance() {
        Customer customer = createCustomer();
        InsuranceRequest insuranceRequest = createInsuranceRequest();

        when(customerService.findById(customer.getExternalId())).thenReturn(customer);
        when(insuranceRequestRepository.save(any())).thenReturn(insuranceRequest);

        InsuranceRequest newInsuranceRequest = insuranceRequestService.simulateInsurance(
                customer.getExternalId(),
                InsuranceType.BRONZE,
                BigDecimal.TEN
        );

        verify(customerService, times(1)).findById(customer.getExternalId());
        verify(insuranceRequestRepository, times(1)).save(any());
        assertEquals(insuranceRequest.getCustomer().getExternalId(), newInsuranceRequest.getCustomer().getExternalId());
        assertEquals(insuranceRequest.getAmountToBePaid(), newInsuranceRequest.getAmountToBePaid());

    }

    @Test
    void findByExternalId() {
        InsuranceRequest insuranceRequest = createInsuranceRequest();

        when(insuranceRequestRepository.findByExternalId(insuranceRequest.getExternalId())).thenReturn(Optional.of(insuranceRequest));
        InsuranceRequest result = insuranceRequestService.findByExternalId(insuranceRequest.getExternalId());

        assertEquals(insuranceRequest.getExternalId(), result.getExternalId());
    }

    @Test
    void formalizeRequest() {
        UUID id = UUID.randomUUID();
        when(insuranceService.formalizeInsurance(id)).thenReturn(any());

        insuranceRequestService.formalizeRequest(id);

        verify(insuranceService, times(1)).formalizeInsurance(id);
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