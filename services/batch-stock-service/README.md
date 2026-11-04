# Batch Stock Service

Bounded context: **Batch Stock** — Guarded batch stock accounts and the releases against them.

Alignment: Batch disposition discipline (ICH Q10). Core invariant, enforced in code and tests: **the guarded
balance/capacity never goes negative** — over-drawing operations are rejected with `422`
and nothing is recorded. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/batch-stock` |
| Owns data | BatchStock accounts, BatchRelease records |
| Publishes | `batch.consumed`, `batch.insufficient` |

Run locally: `mvn -pl services/batch-stock-service quarkus:dev`.
