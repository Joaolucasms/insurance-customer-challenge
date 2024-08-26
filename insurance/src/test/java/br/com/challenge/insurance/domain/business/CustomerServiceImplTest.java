package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.entity.Customer;
import br.com.challenge.insurance.integration.customer.CustomerHttp;
import br.com.challenge.insurance.integration.customer.dto.CustomerResponse;
import br.com.challenge.insurance.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerHttp customerHttp;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    @DisplayName("find by id when client exists")
    void findById1() {
        Customer customer = createCustomer();

        when(customerRepository.findByExternalId(customer.getExternalId())).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findById(customer.getExternalId());

        assertEquals(customer.getExternalId(), foundCustomer.getExternalId());

        verify(customerRepository, times(1)).findByExternalId(customer.getExternalId());
        verify(customerRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("find by id when client doesn't exists")
    void findById2() {
        Customer customer = createCustomer();

        when(customerRepository.findByExternalId(any())).thenReturn(Optional.empty());
        when(customerHttp.findCustomer(customer.getExternalId())).thenReturn(new CustomerResponse(
                customer.getExternalId(),
                "name",
                new Date(),
                "phone",
                "street",
                "addressNumber",
                "zipCode",
                "complement"
        ));
        when(customerRepository.save(any())).thenReturn(customer);

        Customer foundCustomer = customerService.findById(customer.getExternalId());
        assertEquals(customer.getExternalId(), foundCustomer.getExternalId());

        verify(customerRepository, times(1)).findByExternalId(customer.getExternalId());
        verify(customerRepository, times(1)).save(any());

    }

    private Customer createCustomer() {
        return new Customer(UUID.randomUUID());
    }
}