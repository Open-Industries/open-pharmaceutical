# Disposition Rules Service

Bounded context: **Disposition Rules** — Decision rules binding product types to batch-size and deviation envelopes.

Alignment: ICH Q9 quality risk management posture. Decisions are `ALLOWED` / `DEGRADED` / `DENIED`, always
with the matched rule and a human-readable reason for audit. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8083` (`HTTP_PORT`) |
| Base path | `/api/v1/disposition-rules` |
| Owns data | DispositionRule rules |
| Publishes | `disposition.activated` |

Run locally: `mvn -pl services/disposition-rules-service quarkus:dev`.
