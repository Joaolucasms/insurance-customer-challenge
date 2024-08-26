package br.com.challenge.customer.application.dto;

import java.util.Date;

public record CustomerRequest(
        String name,
        String phone,
        Date birthDate,
        String street,
        String addressNumber,
        String zipCode,
        String complement
) { }
