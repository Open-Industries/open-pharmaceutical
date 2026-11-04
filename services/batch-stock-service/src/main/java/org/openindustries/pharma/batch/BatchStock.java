package org.openindustries.pharma.batch;

import java.util.UUID;

/**
 * BatchStock aggregate — the guarded resource account of this core: a balance/capacity that
 * consumption operations draw from. Core invariant (ADR-0004): it NEVER goes negative;
 * an operation that would overdraw it is rejected and records nothing.
 */
public class BatchStock {
    public UUID id;
        public String productCode;
    public long availableUnits;
    public String status;

    public static BatchStock open(String productCode, long availableUnits) {
        BatchStock a = new BatchStock();
        a.id = UUID.randomUUID();
        a.productCode = productCode;
        a.availableUnits = availableUnits;
        a.status = "ACTIVE";
        return a;
    }
}
