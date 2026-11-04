# Open Pharmaceutical — Reference Pharmaceutical Core Platform

A **standard, generic pharmaceutical core platform** that any organization in this vertical
can deploy and adapt: built as **microservices and serverless functions** on
**Java 21 + Quarkus**, aligned with **The Twelve-Factor App**, documented under the ISO
architecture and quality standards, and aligned with the domain standards of the
vertical (see the traceability matrix). Everything is *aligned to* the cited standards —
**no certification, conformance or regulatory-compliance claim is made**. All data is
**synthetic**.

> Reference implementation: small, complete and runnable. The rules that matter in this
> domain are enforced in code and covered by tests — start with the invariant:
> units released from a batch never exceed the batch's available stock — a release that would overrun it is rejected (422) and records nothing; batch disposition is always evidenced.

## Standards alignment

| Standard / framework | What it governs here | Where |
|---|---|---|
| ISO/IEC/IEEE **42010** | Architecture description | `docs/architecture/architecture-description.md` |
| ISO/IEC **25010** (SQuaRE) | Product quality model and scenarios | `docs/architecture/quality-attributes.md` |
| ISO/IEC/IEEE **15288 & 12207** | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC **27001 & 27002** | Information security | `docs/security/` |
| **The Twelve-Factor App** | Cloud-native methodology | `docs/architecture/twelve-factor.md` |
| ICH Q8/Q9/Q10 | Pharmaceutical development, quality risk, PQS | `docs/domain/domain-standards.md` mapping |
| ISPE GAMP 5 | GMP computerized systems | Life-cycle and verification posture |
| FDA 21 CFR Part 11 / EU Annex 11 | Electronic records & signatures | Audit-trail and record-integrity posture (no certification claimed) |
| GS1 / DSCSA | Serialization & traceability | Package serialization posture |
| ISO 15378 | GMP for primary packaging materials | Packaging catalogue posture |

Full traceability: `docs/compliance/standards-traceability.md`.

## The platform at a glance

### Microservices (Quarkus, `services/`)

| Service | Port | Alignment | Responsibility |
|---|---|---|---|
| `drug-product-service` | 8081 | ICH Q10 product lifecycle posture | Drug product registry (ATC-coded). |
| `batch-stock-service` | 8082 | Batch disposition discipline (ICH Q10) | Guarded batch stock accounts and the releases against them. |
| `disposition-rules-service` | 8083 | ICH Q9 quality risk management posture | Decision rules binding product types to batch-size and deviation envelopes. |
| `serial-package-service` | 8084 | GS1 serialization posture; ISO 15378 packaging data | Serialized package format catalogue. |
| `notification-service` | 8085 | Platform support (shared pattern) | Channel-agnostic dispatch of domain notifications from events. |

### Serverless functions (Quarkus Funqy + Knative, `functions/`)

| Function | Trigger | Responsibility |
|---|---|---|
| `deviation-risk-scorer` | Event / HTTP | Scores batch risk signals (deviations, temperature excursions, complaints) with an auditable rationale. |
| `gmp-compliance-evaluator` | Event / HTTP | Compares open deviations and documentation completeness against batch targets; one finding per breach. |

## Design principles

1. **One bounded context per service**, identical hexagonal layout everywhere.
2. **The domain invariant is code, not prose** — units released from a batch never exceed the batch's available stock — a release that would overrun it is rejected (422) and records nothing; batch disposition is always evidenced.
3. **Privacy by design** — No patient data exists in this core, by design. Batch and product data is regulated operational data handled under GxP-style audit discipline (attributable, contemporaneous, append-only); all data is synthetic.
4. **Database per service, API-first (OpenAPI), events as CloudEvents.**
5. **Adapt by configuration** — rules, thresholds, tariffs/rates and providers are data
   and configuration, not code forks (see *Adaptation guide*).

## Quick start

Prerequisites: Java 21, Maven 3.9+, optionally Docker/Podman and Knative/OpenShift.

```bash
mvn verify                                    # build and test everything
mvn -pl services/drug-product-service quarkus:dev
docker compose up -d                          # local PostgreSQL, Kafka, Keycloak
```

## Deployment

- **Kubernetes / OpenShift**: `kubectl apply -k deploy/kubernetes/`.
- **Serverless**: Knative Services in `deploy/knative/` (scale to zero).
- **Local**: `docker-compose.yml` for dependencies; services run with `quarkus:dev`.

## Adaptation guide

| You want to… | Change only… |
|---|---|
| Adjust domain rules and thresholds | Rule data in the `disposition-rules-service` context and function configuration |
| Change the guarded capacity semantics | The `batch-stock-service` context (invariant stays: never negative) |
| Plug your IdP / providers | OIDC at the gateway; provider adapters at the boundary |
| Replace in-memory persistence | The repository port's JPA adapter on the service's own database (`DB_KIND`, `DB_URL`) |

## Documentation map

- Architecture (42010): `docs/architecture/` · Decisions: `docs/adr/`
- Domain standards: `docs/domain/domain-standards.md`
- Quality (25010), Security (27001/27002), Life cycle (15288/12207), API, Data, Operations: under `docs/`

## License

Apache-2.0 — see `LICENSE`.
