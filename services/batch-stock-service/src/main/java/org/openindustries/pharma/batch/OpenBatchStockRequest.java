package org.openindustries.pharma.batch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OpenBatchStockRequest(
        @NotBlank String productCode,
        @PositiveOrZero long availableUnits) {
}
