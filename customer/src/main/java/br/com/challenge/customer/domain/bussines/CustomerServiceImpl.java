package br.com.challenge.customer.domain.bussines;

import br.com.challenge.customer.domain.contract.AddressService;
import br.com.challenge.customer.domain.contract.CustomerService;
import br.com.challenge.customer.domain.entity.Address;
import br.com.challenge.customer.domain.entity.Customer;
import br.com.challenge.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final AddressService addressService;

    public CustomerServiceImpl(AddressService addressService, CustomerRepository customerRepository) {
        this.addressService = addressService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(UUID id) {
        return customerRepository.findByExternalId(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public Customer save(String name, Date birthDate, String phone, String street, String addressNumber, String zipCode, String complement) {
        Customer customer = new Customer(name, birthDate, phone);
        customerRepository.save(customer);
        Address address = addressService.save(street, addressNumber, zipCode, complement, customer);

        customer.setAddress(address);
        return customer;
    }

    @Override
    @Transactional
    public Customer update(UUID id, String name, Date birthDate, String phone) {
        Customer customer = findById(id);
        customer.setName(name);
        customer.setBirthDate(birthDate);
        customer.setPhone(phone);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteById(UUID id) {
        customerRepository.deleteByExternalId(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
