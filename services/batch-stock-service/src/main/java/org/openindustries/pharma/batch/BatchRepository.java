package org.openindustries.pharma.batch;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class BatchRepository {
    private final ConcurrentHashMap<UUID, BatchStock> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<BatchRelease> records = new CopyOnWriteArrayList<>();

    public BatchStock saveAccount(BatchStock account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<BatchStock> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public BatchRelease saveRecord(BatchRelease record) {
        records.add(record);
        return record;
    }

    public List<BatchRelease> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
