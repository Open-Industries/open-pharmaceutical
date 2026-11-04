package org.openindustries.pharma.batch;

import java.time.Instant;
import java.util.UUID;

/** One guarded consumption record — the auditable evidence of a draw on a BatchStock. */
public class BatchRelease {
    public UUID id;
    public UUID accountId;
    public String releaseType;
    public long units;
    public long remainingAfter;
    public Instant recordedAt;

    public static BatchRelease of(UUID accountId, String releaseType, long units, long remainingAfter) {
        BatchRelease r = new BatchRelease();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.releaseType = releaseType;
        r.units = units;
        r.remainingAfter = remainingAfter;
        r.recordedAt = Instant.now();
        return r;
    }
}
