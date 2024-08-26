package br.com.challenge.customer.domain.contract;

import br.com.challenge.customer.domain.entity.Address;
import br.com.challenge.customer.domain.entity.Customer;

public interface AddressService {

    Address save(String street, String addressNumber, String zipCode, String complement, Customer customer);
}
