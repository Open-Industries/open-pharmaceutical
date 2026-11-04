package org.openindustries.pharma.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DrugProductService {

    @Inject
    DrugProductRepository repository;

    public DrugProduct create(CreateDrugProductRequest request) {
        return repository.save(DrugProduct.create(request.name(), request.productType(), request.atcCode()));
    }

    public DrugProduct get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "DrugProduct not found: " + id));
    }

    public List<DrugProduct> list() {
        return repository.findAll();
    }

    public DrugProduct approve(UUID id) {
        DrugProduct entity = get(id);
        if ("WITHDRAWN".equals(entity.status)) {
            throw new DomainException(409, "A withdrawn drugproduct is terminal");
        }
        if ("APPROVED".equals(entity.status)) {
            throw new DomainException(409, "DrugProduct already APPROVED");
        }
        entity.status = "APPROVED";
        return repository.save(entity);
    }

    public DrugProduct withdraw(UUID id) {
        DrugProduct entity = get(id);
        if ("WITHDRAWN".equals(entity.status)) {
            throw new DomainException(409, "A withdrawn drugproduct is terminal");
        }
        if ("WITHDRAWN".equals(entity.status)) {
            throw new DomainException(409, "DrugProduct already WITHDRAWN");
        }
        entity.status = "WITHDRAWN";
        return repository.save(entity);
    }
}
