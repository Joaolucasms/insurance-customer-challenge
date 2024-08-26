package br.com.challenge.insurance.domain.contract;

import br.com.challenge.insurance.domain.entity.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer findById(UUID id);
}
