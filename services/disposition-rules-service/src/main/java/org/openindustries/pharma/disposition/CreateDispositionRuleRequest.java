package org.openindustries.pharma.disposition;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateDispositionRuleRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "TABLET|CAPSULE|INJECTABLE|TOPICAL") String productType,
        @Positive long maxBatchSizeUnits,
        @Positive int maxDeviationCount,
        @Min(1) @Max(100) int priority) {
}
