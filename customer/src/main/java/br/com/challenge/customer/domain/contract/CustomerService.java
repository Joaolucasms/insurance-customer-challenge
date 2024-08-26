package br.com.challenge.customer.domain.contract;

import br.com.challenge.customer.domain.entity.Customer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer findById(UUID id);

    Customer save(String name, Date birthDate, String phone, String street, String addressNumber, String zipCode, String complement);

    Customer update(UUID id, String name, Date birthDate, String phone);

    void deleteById(UUID id);

    List<Customer> findAll();
}
