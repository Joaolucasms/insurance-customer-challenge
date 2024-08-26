package br.com.challenge.customer.domain.bussines;

import br.com.challenge.customer.domain.entity.Address;
import br.com.challenge.customer.domain.entity.Customer;
import br.com.challenge.customer.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void save(){
        Address address = createAddress();

        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address savedAddress = addressService.save("street", "addressNumber", "zipCode", "complement", createCustomer());

        assertEquals(address.getAddressNumber(), savedAddress.getAddressNumber());
        assertEquals(address.getZipCode(), savedAddress.getZipCode());
        assertEquals(address.getComplement(), savedAddress.getComplement());
    }

    private Address createAddress() {
        return new Address(
                "street",
                "addressNumber",
                "zipCode",
                "complement",
                createCustomer()
        );
    }

    private Customer createCustomer() {
        return new Customer("name", new Date(), "12345678912");
    }


}