# GMP Compliance Evaluator (serverless function)

Quarkus Funqy function on Knative (scale to zero). Compares open deviations and documentation completeness against batch targets; one finding per breach.

Invoke: `POST /gmpComplianceEvaluator` with the KPI payload.
