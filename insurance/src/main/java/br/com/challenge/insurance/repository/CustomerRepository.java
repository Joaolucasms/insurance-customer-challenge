package br.com.challenge.insurance.repository;

import br.com.challenge.insurance.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Nullable
    Optional<Customer> findByExternalId(UUID externalId);
}
