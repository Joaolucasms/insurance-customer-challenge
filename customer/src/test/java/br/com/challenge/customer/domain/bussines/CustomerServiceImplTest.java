package br.com.challenge.customer.domain.bussines;

import br.com.challenge.customer.domain.contract.AddressService;
import br.com.challenge.customer.domain.entity.Address;
import br.com.challenge.customer.domain.entity.Customer;
import br.com.challenge.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.UUID;
import java.util.Optional;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    @Autowired
    private CustomerServiceImpl customerService;

    @Mock
    private AddressService addressService;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    public void findById(){
        Customer customer = createCustomer();

        when(customerRepository.findByExternalId(customer.getExternalId())).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.findById(customer.getExternalId());
        assertEquals(customer.getExternalId(), foundCustomer.getExternalId());
        assertEquals(customer.getName(), foundCustomer.getName());
        assertEquals(customer.getBirthDate(), foundCustomer.getBirthDate());
        assertEquals(customer.getPhone(), foundCustomer.getPhone());

        verify(customerRepository, times(1)).findByExternalId(customer.getExternalId());
    }

    @Test
    public void save(){
        Customer customer = createCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(addressService.save(
                any(),
                any(),
                any(),
                any(),
                any())
        ).thenReturn(createAddress());

        Customer savedCustomer = customerService.save(
                "name",
                new Date(),
                "12345678910",
                "street",
                "addressNumber",
                "zipCode",
                "complement"
        );

        assertEquals( customer.getName(), savedCustomer.getName());

        verify(customerRepository, times(1)).save(any());
        verify(addressService, times(1)).save(any(), any(), any(), any(), any());
    }

    @Test
    public void update(){
        Customer customer = createCustomer();
        String newName = "new name";
        String newPhone = "new phone";
        Date newBirthDate = new Date(2023, Calendar.JANUARY, 1);

        when(customerRepository.findByExternalId(customer.getExternalId())).thenReturn(Optional.of(customer));

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer updatedCustomer = customerService.update(customer.getExternalId(), newName, newBirthDate, newPhone);

        assertEquals(newName, updatedCustomer.getName());
        assertEquals(newPhone, updatedCustomer.getPhone());
        assertEquals(newBirthDate, updatedCustomer.getBirthDate());
        verify(customerRepository, times(1)).findByExternalId(customer.getExternalId());
        verify(customerRepository, times(1)).save(any());


    }

    @Test
    public void delete(){
        UUID id = UUID.randomUUID();
        when(customerRepository.deleteByExternalId(id)).thenReturn(1L);

        customerService.deleteById(id);

        verify(customerRepository, times(1)).deleteByExternalId(id);
    }

    @Test
    public void findAll(){
        Customer customer = createCustomer();
        when(customerRepository.findAll()).thenReturn(new ArrayList<>(){{add(customer);}});

        List<Customer> customers = customerService.findAll();

        assertTrue(customers.contains(customer));
        assertEquals(1, customers.size());

        verify(customerRepository, times(1)).findAll();
    }

    private Customer createCustomer() {
        return new Customer("name", new Date(), "12345678912");
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


}