package org.openindustries.pharma.product;

import java.time.Instant;
import java.util.UUID;

/**
 * DrugProduct aggregate — Drug product registry (ATC-coded).
 *
 * Privacy by design (ISO/IEC 27002 A.5.34): secret identifiers are validated at
 * creation and NEVER persisted or returned in clear — only a masked form is kept.
 * All identifiers in this reference implementation are synthetic.
 */
public class DrugProduct {
    public UUID id;
        public String name;
        public String productType;
        public String atcCode;
    public String status;
    public Instant createdAt;

    public static DrugProduct create(String name, String productType, String atcCode) {
        DrugProduct e = new DrugProduct();
        e.id = UUID.randomUUID();
        e.name = name;
        e.productType = productType;
        e.atcCode = atcCode;
        e.status = "REGISTERED";
        e.createdAt = Instant.now();
        return e;
    }
}
