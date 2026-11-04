# Services

One independently deployable Quarkus microservice per bounded context. Every service
follows the same hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`,
returns RFC 9457 problem details, and is configured only through environment variables
(Twelve-Factor). Lifecycle transitions take no body; `@Consumes` appears only on
endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Drug Products → Batch Stock → Disposition Rules → Open APIs.

| Service | Port | Alignment |
|---|---|---|
| `drug-product-service` | 8081 | ICH Q10 product lifecycle posture |
| `batch-stock-service` | 8082 | Batch disposition discipline (ICH Q10) |
| `disposition-rules-service` | 8083 | ICH Q9 quality risk management posture |
| `serial-package-service` | 8084 | GS1 serialization posture; ISO 15378 packaging data |
| `notification-service` | 8085 | Platform support (shared pattern) |

Run the pilot service:

```bash
mvn -pl services/drug-product-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
