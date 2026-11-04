package org.openindustries.pharma.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateDrugProductRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "TABLET|CAPSULE|INJECTABLE|TOPICAL") String productType,
        @NotBlank String atcCode) {
}
