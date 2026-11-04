package org.openindustries.pharma.batch;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BatchService {

    @Inject
    BatchRepository repository;

    public BatchStock open(OpenBatchStockRequest request) {
        return repository.saveAccount(BatchStock.open(request.productCode(), request.availableUnits()));
    }

    public BatchStock get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "BatchStock not found: " + id));
    }

    /**
     * Draws from the account. Invariant: the balance/capacity never goes negative —
     * an over-drawing operation is rejected (422) and nothing is recorded.
     */
    public BatchRelease consume(UUID accountId, CreateBatchReleaseRequest request) {
        BatchStock account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "BatchStock is not ACTIVE");
        }
        if (request.units() > account.availableUnits) {
            throw new DomainException(422, "Insufficient capacity: requested " + request.units()
                    + " exceeds available " + account.availableUnits);
        }
        account.availableUnits -= request.units();
        repository.saveAccount(account);
        return repository.saveRecord(BatchRelease.of(accountId, request.releaseType(), request.units(), account.availableUnits));
    }

    public List<BatchRelease> recordsOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
