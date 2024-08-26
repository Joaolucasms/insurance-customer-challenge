package br.com.challenge.insurance.domain.business;

import br.com.challenge.insurance.domain.contract.CustomerService;
import br.com.challenge.insurance.domain.entity.Customer;
import br.com.challenge.insurance.integration.customer.CustomerHttp;
import br.com.challenge.insurance.integration.customer.dto.CustomerResponse;
import br.com.challenge.insurance.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerHttp customerHttp;
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerHttp customerHttp) {
        this.customerRepository = customerRepository;
        this.customerHttp = customerHttp;
    }

    @Override
    public Customer findById(UUID id) {
        Customer customer;
        customer = this.customerRepository.findByExternalId(id).orElse(null);
        if (customer == null) {
            CustomerResponse externalCustomer = findExternalCustomer(id);
            customer = save(externalCustomer.id());
        }

        return customer;
    }

    private Customer save(UUID externalId){
        Customer customer = new Customer(externalId);
        return customerRepository.save(customer);
    }

    private CustomerResponse findExternalCustomer(UUID externalId){
        CustomerResponse externalCustomer = customerHttp.findCustomer(externalId);

        if (externalCustomer == null) {throw new IllegalArgumentException();}
        return externalCustomer;
    }
}
