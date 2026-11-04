package org.openindustries.pharma.serialpkg;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateSerialPackageRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "BOTTLE|BLISTER|VIAL|BOX") String packageType,
        @Positive int unitsPerPackage) {
}
