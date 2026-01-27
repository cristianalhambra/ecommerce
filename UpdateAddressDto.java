package com.tienda.ecommerce.auth.dto;

public record UpdateAddressDto(
        String fullName,
        String street,
        String city,
        String postalCode,
        String country
) {}
