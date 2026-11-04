package org.openindustries.pharma.disposition;

import java.util.UUID;

/**
 * DispositionRule aggregate — a decision rule of this core: for one productType, it guarantees a
 * capacity (maxBatchSizeUnits) and a threshold (maxDeviationCount), with a precedence priority.
 * Only ACTIVE rules decide; every decision names its rule and its reason (audit).
 */
public class DispositionRule {
    public UUID id;
    public String name;
    public String productType;
    public long maxBatchSizeUnits;
    public int maxDeviationCount;
    public int priority;
    public String status;

    public static DispositionRule create(String name, String productType, long maxBatchSizeUnits, int maxDeviationCount, int priority) {
        DispositionRule r = new DispositionRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.productType = productType;
        r.maxBatchSizeUnits = maxBatchSizeUnits;
        r.maxDeviationCount = maxDeviationCount;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
