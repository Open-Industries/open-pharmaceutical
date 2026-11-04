package org.openindustries.pharma.disposition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluateRequest(
        @NotBlank @Pattern(regexp = "TABLET|CAPSULE|INJECTABLE|TOPICAL") String productType,
        @Positive int requiredMaxDeviationCount,
        @Positive long requiredMaxBatchSizeUnits) {
}
