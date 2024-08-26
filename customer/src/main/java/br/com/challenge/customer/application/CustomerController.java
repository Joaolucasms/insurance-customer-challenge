package br.com.challenge.customer.application;

import br.com.challenge.customer.application.dto.CustomerRequest;
import br.com.challenge.customer.application.dto.CustomerResponse;
import br.com.challenge.customer.domain.contract.CustomerService;
import br.com.challenge.customer.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listCustomers() {
        List<CustomerResponse> customers = customerService.findAll().stream().map(
                (customer -> new CustomerResponse(
                        customer.getExternalId(),
                        customer.getName(),
                        customer.getBirthDate(),
                        customer.getPhone(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getAddressNumber(),
                        customer.getAddress().getZipCode(),
                        customer.getAddress().getComplement())
                )
        ).toList();

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable UUID id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(new CustomerResponse(
                customer.getExternalId(),
                customer.getName(),
                customer.getBirthDate(),
                customer.getPhone(),
                customer.getAddress().getStreet(),
                customer.getAddress().getAddressNumber(),
                customer.getAddress().getZipCode(),
                customer.getAddress().getComplement())
        );
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerRequest customerRequest
    ){
        Customer customer = customerService.save(
                customerRequest.name(),
                customerRequest.birthDate(),
                customerRequest.phone(),
                customerRequest.street(),
                customerRequest.addressNumber(),
                customerRequest.zipCode(),
                customerRequest.complement()
        );

        return ResponseEntity.ok(new CustomerResponse(
                        customer.getExternalId(),
                        customer.getName(),
                        customer.getBirthDate(),
                        customer.getPhone(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getAddressNumber(),
                        customer.getAddress().getZipCode(),
                        customer.getAddress().getComplement()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable UUID id,
            @RequestBody CustomerRequest customerRequest
    ){
        Customer customer = customerService.update(
                id,
                customerRequest.name(),
                customerRequest.birthDate(),
                customerRequest.phone()
        );

        return ResponseEntity.ok(new CustomerResponse(
                        customer.getExternalId(),
                        customer.getName(),
                        customer.getBirthDate(),
                        customer.getPhone(),
                        customer.getAddress().getStreet(),
                        customer.getAddress().getAddressNumber(),
                        customer.getAddress().getZipCode(),
                        customer.getAddress().getComplement()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable UUID id){
        customerService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
