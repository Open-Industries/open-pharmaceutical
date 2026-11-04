package org.openindustries.pharma.disposition;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DispositionRuleService {

    @Inject
    DispositionRuleRepository repository;

    public DispositionRule create(CreateDispositionRuleRequest request) {
        return repository.save(DispositionRule.create(request.name(), request.productType(),
                request.maxBatchSizeUnits(), request.maxDeviationCount(), request.priority()));
    }

    public DispositionRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "DispositionRule not found: " + id));
    }

    public List<DispositionRule> list() {
        return repository.findAll();
    }

    public DispositionRule activate(UUID id) {
        DispositionRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public Evaluation evaluate(EvaluateRequest request) {
        var candidates = repository.activeFor(request.productType());
        if (candidates.isEmpty()) {
            return new Evaluation("DENIED", null,
                    "No active rule for " + request.productType());
        }
        for (DispositionRule rule : candidates) {
            if (rule.maxDeviationCount <= request.requiredMaxDeviationCount()
                    && rule.maxBatchSizeUnits >= request.requiredMaxBatchSizeUnits()) {
                return new Evaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the request");
            }
        }
        return new Evaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.productType() + " but none meets the requirement");
    }
}
