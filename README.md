
# API REST Ejemplo con Jakarta EE y MySQL

Este proyecto es un ejemplo de cómo crear una API REST utilizando Jakarta EE y una base de datos MySQL. La aplicación se puede ejecutar utilizando Docker Compose, que configurará tanto la aplicación Jakarta EE como la base de datos MySQL en contenedores separados.

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Configuración con Docker Compose](#configuración-con-docker-compose)
- [Endpoints de la API](#endpoints-de-la-api)
  - [Obtener Todas las Personas](#obtener-todas-las-personas)
  - [Obtener una Persona por ID](#obtener-una-persona-por-id)
  - [Crear una Persona](#crear-una-persona)
  - [Actualizar una Persona](#actualizar-una-persona)
  - [Eliminar una Persona](#eliminar-una-persona)
- [Notas de Configuración](#notas-de-configuración)
- [Ejecutar Pruebas](#ejecutar-pruebas)
- [Licencia](#licencia)

## Descripción del Proyecto

Este proyecto es una plantilla que muestra cómo crear y configurar una API REST utilizando Jakarta EE, Payara Micro, y MySQL, todo funcionando perfectamente en contenedores Docker. El propósito principal de esta plantilla es servir como punto de partida para desarrolladores que buscan una configuración lista para usar con estas tecnologías. Dado que hay poca documentación disponible en internet sobre este tipo de configuración, este proyecto pretende llenar ese vacío, proporcionando una solución completamente funcional y bien documentada.

## Estructura del Proyecto

- `org.alvarowau.model`: Contiene la entidad `Person`.
- `org.alvarowau.repository`: Contiene la clase `PersonRepository`, que maneja las operaciones de persistencia.
- `org.alvarowau.service`: Contiene la clase `HelloService`, que proporciona un servicio simple de saludo.
- `org.alvarowau.controller`: Contiene las clases `PersonController` y `DemoController`, que definen los endpoints REST.
- `org.alvarowau.util`: Contiene la clase `EntityManagerProducer`, que maneja la creación de `EntityManager` y `EntityManagerFactory`.

## Requisitos Previos

- Docker y Docker Compose instalados.
- Java SE 8+ (si se desea ejecutar sin Docker).
- Maven (el proyecto incluye el Maven Wrapper, por lo que no es estrictamente necesario).

## Configuración con Docker Compose

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```

2. Ejecuta Docker Compose:

   ```bash
   docker-compose up --build
   ```

   Esto iniciará los contenedores para la aplicación Jakarta EE y MySQL. La aplicación estará disponible en [http://localhost:8080/api-rest-ejemplo/rest/person](http://localhost:8080/api-rest-ejemplo/rest/person).

## Endpoints de la API

### Obtener Todas las Personas

- **URL**: `/rest/person`
- **Método HTTP**: `GET`
- **Descripción**: Devuelve una lista de todas las personas en la base de datos.

**Ejemplo de Respuesta:**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com"
  }
]
```

### Obtener una Persona por ID

- **URL**: `/rest/person/{id}`
- **Método HTTP**: `GET`
- **Descripción**: Devuelve los detalles de una persona específica por su ID.

**Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Respuesta de Error:**
```json
{
  "error": "Person with id 1 not found."
}
```

### Crear una Persona

- **URL**: `/rest/person`
- **Método HTTP**: `POST`
- **Descripción**: Crea una nueva persona en la base de datos.

**Cuerpo de la Solicitud:**
```json
{
  "name": "Alice Johnson",
  "email": "alice.johnson@example.com"
}
```

**Ejemplo de Respuesta:** 201 Created con la ubicación del nuevo recurso.

### Actualizar una Persona

- **URL**: `/rest/person/{id}`
- **Método HTTP**: `PUT`
- **Descripción**: Actualiza los detalles de una persona existente.

**Cuerpo de la Solicitud:**
```json
{
  "name": "Alice Johnson",
  "email": "alice.johnson@newdomain.com"
}
```

**Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "name": "Alice Johnson",
  "email": "alice.johnson@newdomain.com"
}
```

### Eliminar una Persona

- **URL**: `/rest/person/{id}`
- **Método HTTP**: `DELETE`
- **Descripción**: Elimina una persona por su ID.

**Ejemplo de Respuesta:** 204 No Content si la eliminación es exitosa.

**Respuesta de Error:**
```json
{
  "error": "Person with id 1 not found."
}
```

## Notas de Configuración

- `glassfish-resources.xml`: Define el JDBC Connection Pool y JDBC Resource que conecta la aplicación a MySQL.
- `persistence.xml`: Configura la unidad de persistencia `default-pu` para interactuar con la base de datos MySQL.

**Modificaciones en `glassfish-resources.xml`:**
Si estás utilizando un nombre de base de datos, usuario o contraseña diferente, asegúrate de actualizar estos valores en el archivo `glassfish-resources.xml` antes de ejecutar la aplicación.

**Modificaciones en `persistence.xml`:**
Asegúrate de que el nombre del `jta-data-source` en `persistence.xml` coincide con el nombre JNDI del recurso JDBC definido en `glassfish-resources.xml`.

## Ejecutar Pruebas

Puedes probar la API utilizando herramientas como Postman o cURL. Aquí hay algunos ejemplos de comandos cURL:

**Obtener todas las personas:**
```bash
curl -X GET http://localhost:8080/api-rest-ejemplo/rest/person
```

**Crear una nueva persona:**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name":"Alice Johnson","email":"alice.johnson@example.com"}' http://localhost:8080/api-rest-ejemplo/rest/person
```

**Actualizar una persona:**
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Alice Johnson","email":"alice.johnson@newdomain.com"}' http://localhost:8080/api-rest-ejemplo/rest/person/1
```

**Eliminar una persona:**
```bash
curl -X DELETE http://localhost:8080/api-rest-ejemplo/rest/person/1
```

## Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
