package br.com.challenge.customer.domain.bussines;

import br.com.challenge.customer.domain.contract.AddressService;
import br.com.challenge.customer.domain.entity.Address;
import br.com.challenge.customer.domain.entity.Customer;
import br.com.challenge.customer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(String street, String addressNumber, String zipCode, String complement, Customer customer) {
        Address address = new Address(street, addressNumber, zipCode, complement, customer);
        addressRepository.save(address);
        return address;
    }

}
