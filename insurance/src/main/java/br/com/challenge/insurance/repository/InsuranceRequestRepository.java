package br.com.challenge.insurance.repository;

import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuranceRequestRepository extends JpaRepository<InsuranceRequest, Long> {
    Optional<InsuranceRequest> findByExternalId(UUID externalId);
}
