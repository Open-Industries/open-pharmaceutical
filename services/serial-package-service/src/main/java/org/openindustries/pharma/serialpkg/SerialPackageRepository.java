package org.openindustries.pharma.serialpkg;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class SerialPackageRepository {
    private final ConcurrentHashMap<UUID, SerialPackage> store = new ConcurrentHashMap<>();

    public SerialPackage save(SerialPackage entity) {
        store.put(entity.id, entity);
        return entity;
    }

    public Optional<SerialPackage> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<SerialPackage> findAll() {
        return List.copyOf(store.values());
    }
}
