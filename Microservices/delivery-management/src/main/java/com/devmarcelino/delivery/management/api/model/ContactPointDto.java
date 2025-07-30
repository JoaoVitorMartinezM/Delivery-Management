package com.devmarcelino.delivery.management.api.model;

import jakarta.validation.constraints.NotBlank;


public record ContactPointDto(@NotBlank String zipcode, @NotBlank String street,
 @NotBlank String number, String complement, @NotBlank String name, @NotBlank String phone) {
    
}
