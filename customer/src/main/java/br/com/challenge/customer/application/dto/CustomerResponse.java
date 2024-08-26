package br.com.challenge.customer.application.dto;

import java.util.Date;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        Date birthDate,
        String phone,
        String street,
        String addressNumber,
        String zipCode,
        String complement
) { }
