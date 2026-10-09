# SuperMarket Leo

API REST que simula las operaciones de un supermercado: productos por categoría y compras de clientes, con autenticación por JWT.

## Stack

- Java 11 y Spring Boot 2.7 (Gradle)
- Spring Data JPA + PostgreSQL
- Spring Security + JWT (`jjwt`)
- MapStruct para convertir entre entidades y objetos de dominio
- Swagger / OpenAPI (`springdoc`)
- JUnit 5 (H2 en memoria para los tests)

## Arquitectura

El código separa el **dominio** de la **persistencia**: los servicios trabajan con objetos de dominio (`ProductVO`, `PurchaseVO`) e interfaces de repositorio, y la capa de persistencia las implementa con JPA y traduce las entidades con mappers de MapStruct.

```
controller/     → API REST
domain/         → servicios, objetos de dominio e interfaces de repositorio
persistence/    → entidades JPA, repositorios y mappers
security/       → filtro JWT y configuración de Spring Security
```

## Cómo ejecutar

1. Crear la base `supermarket` en PostgreSQL y cargar [`resources/schema.sql`](resources/schema.sql) y [`resources/data.sql`](resources/data.sql).

2. Definir las variables obligatorias:
   ```bash
   export JWT_SECRET="un-secreto-largo-y-aleatorio"
   export SUPERMARKET_USER="admin"
   export SUPERMARKET_PASSWORD="una-contraseña"
   ```

3. Arrancar:
   ```bash
   ./gradlew bootRun
   ```

La API queda en `http://localhost:8080/superMarketServices` y Swagger en `http://localhost:8080/superMarketServices/swagger-ui/index.html`.

## Variables de entorno

| Variable | Valor por defecto | Para qué sirve |
|---|---|---|
| `JWT_SECRET` | — (obligatoria) | Clave para firmar los tokens. |
| `SUPERMARKET_USER` | — (obligatoria) | Usuario único de la demo. |
| `SUPERMARKET_PASSWORD` | — (obligatoria) | Su contraseña. |
| `DB_URL` | `jdbc:postgresql://localhost:5432/supermarket` | URL de la base. |
| `DB_USERNAME` | `postgres` | Usuario de la base. |
| `DB_PASSWORD` | el de `application-dev.yml` en `dev`; obligatoria en `pro` | Contraseña de la base. |

El perfil activo por defecto es `dev`.

## Autenticación

`POST /auth/authenticate` con `{"username": "...", "password": "..."}` devuelve `{"jwt": "..."}`. Las demás peticiones envían `Authorization: Bearer <token>`.

## Endpoints

| Ruta | Método | Descripción |
|---|---|---|
| `/auth/authenticate` | `POST` | Obtiene el token (público) |
| `/products/all` | `GET` | Lista los productos |
| `/products/{id}` | `GET` | Producto por id |
| `/products/category/{categoryId}` | `GET` | Productos de una categoría |
| `/products/save` | `POST` | Crea un producto |
| `/products/delete/{id}` | `DELETE` | Elimina un producto |
| `/purchases/all` | `GET` | Lista las compras |
| `/purchases/client/{id}` | `GET` | Compras de un cliente |
| `/purchases/save` | `POST` | Registra una compra |

## Tests

```bash
./gradlew test
```

No necesitan PostgreSQL: usan H2 en memoria.
