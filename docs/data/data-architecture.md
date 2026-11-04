# Data Architecture

Data view (VP-5) of the Architecture Description.

## 1. Ownership

| Data | Owner (only writer) | Store (reference → production) |
|---|---|---|
| Drug Products records | drug-product-service | in-memory → PostgreSQL `product` |
| Batch Stock records | batch-stock-service | in-memory → PostgreSQL `batch` |
| Disposition Rules records | disposition-rules-service | in-memory → PostgreSQL `disposition` |
| Serial Packages records | serial-package-service | in-memory → PostgreSQL `serialpkg` |
| Notifications records | notification-service | in-memory → PostgreSQL `notification` |

In-memory adapters exist so the platform runs anywhere with zero setup; production
persistence is configuration (`DB_KIND`, `DB_URL`) plus the JPA adapter — the domain and
contracts never change (12-Factor IV).

## 2. Privacy and identifiers

No patient data exists in this core, by design. Batch and product data is regulated operational data handled under GxP-style audit discipline (attributable, contemporaneous, append-only); all data is synthetic. Internal keys are UUIDs. All values in this repository are synthetic.

## 3. Consistency, evidence, retention

Strong within a context; eventual across contexts via CloudEvents with idempotent
consumers. Consumption and decision records are append-only evidence; corrections are
compensating records, never edits. Retention per the adopter's regulatory schedule;
personal data erasable per data-subject rights while evidential records are retained in
pseudonymized form (UUIDs only). Backups per service, encrypted, restore tested
(ISO 27002 A.8.13).
