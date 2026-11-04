# Serial Package Service

Bounded context: **Serial Packages** — Serialized package format catalogue.

Alignment: GS1 serialization posture; ISO 15378 packaging data. Lifecycle transitions take no body. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/serial-packages` |
| Owns data | SerialPackage records |

Run locally: `mvn -pl services/serial-package-service quarkus:dev`.
