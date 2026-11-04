package org.openindustries.pharma.functions.gmp;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares open deviations and documentation completeness against batch targets; one finding per breach.
 * Compares measured values with targets and returns a verdict with one finding per
 * breach — machine-checkable assurance evidence.
 */
public class Function {

    public record Kpi(String batchId, int measuredOpenDeviations, int targetOpenDeviations,
                      double measuredDocumentationPct, double targetDocumentationPct) {
    }

    public record Verdict(String batchId, boolean compliant, List<String> findings) {
    }

    @Funq
    public Verdict gmpComplianceEvaluator(Kpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredOpenDeviations() > kpi.targetOpenDeviations()) {
            findings.add("openDeviations " + kpi.measuredOpenDeviations() + " exceeds target " + kpi.targetOpenDeviations());
        }
        if (kpi.measuredDocumentationPct() < kpi.targetDocumentationPct()) {
            findings.add("documentationPct " + kpi.measuredDocumentationPct() + " below target " + kpi.targetDocumentationPct());
        }
        return new Verdict(kpi.batchId(), findings.isEmpty(), findings);
    }
}
