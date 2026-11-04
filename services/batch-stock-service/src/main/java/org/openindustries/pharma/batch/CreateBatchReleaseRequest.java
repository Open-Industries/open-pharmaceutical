package org.openindustries.pharma.batch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateBatchReleaseRequest(
        @NotBlank @Pattern(regexp = "QA_RELEASE|DISTRIBUTION|SAMPLE") String releaseType,
        @Positive long units) {
}
