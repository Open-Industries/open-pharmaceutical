# ADR-0005: Privacy by design; all reference data is synthetic

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

A public reference core must be safe to clone, demo and audit. ISO 27002 A.5.34.

## Decision

No patient data exists in this core, by design. Batch and product data is regulated operational data handled under GxP-style audit discipline (attributable, contemporaneous, append-only); all data is synthetic. Internal keys are UUIDs and no real personal or confidential data ever enters this repository.

## Consequences

Correlation with production records happens through the adopter's governed processes, not this API.
