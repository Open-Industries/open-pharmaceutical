# Drug Product Service

Bounded context: **Drug Products** — Drug product registry (ATC-coded).

Alignment: ICH Q10 product lifecycle posture. Lifecycle transitions take no body; secret identifiers,
where the domain has them, are masked at creation and never stored or returned in clear.
All data is synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/drug-products` |
| Owns data | DrugProduct records |
| Publishes | `product.created`, lifecycle events (see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/drug-product-service quarkus:dev`.
