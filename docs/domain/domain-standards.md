# Domain Standards — Pharmaceutical

Alignment mapping for this core. *Aligned to* throughout: GxP compliance is achieved by
validated systems and quality organizations; this repository provides the platform and
its evidence patterns, and claims no GxP, Part 11 or Annex 11 certification.

## Concept mapping

| Platform concept | Domain standard | Mapping |
|---|---|---|
| Products & batches | ICH Q10 (PQS) / Q8 (development) | Product registry; batch stock integrity |
| Disposition | ICH Q9 (quality risk management) | Rules context with reasoned decisions |
| Records & audit | 21 CFR Part 11 / EU Annex 11 posture | Append-only records, attributable audit logging |
| Computerized-system life cycle | GAMP 5 | Gated life cycle in `docs/lifecycle/` |
| Serialization | GS1 / DSCSA posture | Serial package catalogue; verification at the boundary |
| Packaging | ISO 15378 | Packaging format data |

## Scope boundaries

No patient data, no clinical data, no e-signature execution: signatures and submissions
happen in validated adopter systems.
