package org.openindustries.pharma.functions.deviation;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores batch risk signals (deviations, temperature excursions, complaints) with an auditable rationale.
 * Transparent, auditable rules: NORMAL (< 35), WARNING (35-69), CRITICAL (>= 70);
 * the rationale is returned with the score for audit (ISO/IEC 27002 A.8.15).
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    public record Event(String batchId, int deviationIndex, int excursionIndex, int complaintIndex) {
    }

    public record Score(String batchId, int score, String decision, String rationale) {
    }

    @Funq
    public Score deviationRiskScorer(Event event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.deviationIndex() >= 70) {
            score += 40;
            why.append("deviationIndex >= 70; ");
        }
        if (event.excursionIndex() >= 60) {
            score += 30;
            why.append("excursionIndex >= 60; ");
        }
        if (event.complaintIndex() >= 50) {
            score += 30;
            why.append("complaintIndex >= 50; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new Score(event.batchId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
