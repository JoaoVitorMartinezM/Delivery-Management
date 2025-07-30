package com.devmarcelino.delivery.management.api.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDto(@NotBlank String name, @Min(1) @NotNull Integer quantity) {
    
}
