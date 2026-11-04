package org.openindustries.pharma.serialpkg;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SerialPackageService {

    @Inject
    SerialPackageRepository repository;

    public SerialPackage create(CreateSerialPackageRequest request) {
        return repository.save(SerialPackage.create(request.name(), request.packageType(), request.unitsPerPackage()));
    }

    public SerialPackage get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "SerialPackage not found: " + id));
    }

    public List<SerialPackage> list() {
        return repository.findAll();
    }

    public SerialPackage verify(UUID id) {
        SerialPackage entity = get(id);
        if (!"PRINTED".equals(entity.status)) {
            throw new DomainException(409, "Only a PRINTED serialpackage can transition via verify");
        }
        entity.status = "VERIFIED";
        return repository.save(entity);
    }
}
