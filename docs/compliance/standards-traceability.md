# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-08); tests; SLOs |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| ICH Q8/Q9/Q10 | Pharmaceutical development, quality risk, PQS | `docs/domain/domain-standards.md` mapping |
| ISPE GAMP 5 | GMP computerized systems | Life-cycle and verification posture |
| FDA 21 CFR Part 11 / EU Annex 11 | Electronic records & signatures | Audit-trail and record-integrity posture (no certification claimed) |
| GS1 / DSCSA | Serialization & traceability | Package serialization posture |
| ISO 15378 | GMP for primary packaging materials | Packaging catalogue posture |
