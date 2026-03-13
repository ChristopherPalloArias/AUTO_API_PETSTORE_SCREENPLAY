# Reglas de Proyecto: AUTO_API_PETSTORE_SCREENPLAY
Contexto
Este es un proyecto de automatización de API (Backend) de ciclo completo (CRUD) utilizando Java, Gradle, Serenity BDD y Cucumber. El servicio objetivo es la PetStore Swagger ubicada en https://petstore.swagger.io/v2.

Estándares de Arquitectura (Obligatorio)
Patrón de Diseño: Se debe usar EXCLUSIVAMENTE Screenplay Pattern integrado con Serenity Rest. Está prohibido el uso de Page Object Model o referencias a WebDrivers/UI.

Responsabilidad Única (SRP): Cada clase Task debe representar una única acción de negocio correspondiente a un único verbo HTTP (POST, GET, PUT, DELETE).

Estándares de Código Limpio (Regla de Oro)
Cero Comentarios: Existe una prohibición ABSOLUTA de incluir código comentado o comentarios de documentación (ej. // o /* */) dentro de las clases Java. El código debe ser 100% autodescriptivo.

Nomenclatura Semántica: Nombres de clases, métodos y variables deben ser descriptivos, claros y obligatoriamente en idioma INGLÉS en todo el código fuente Java.

Estándares de BDD y Gherkin
Idioma: Los archivos .feature deben escribirse en ESPAÑOL (# language: es).

Estilo Declarativo: Los escenarios Gherkin deben enfocarse en el comportamiento de negocio. NUNCA en interacciones de red. Prohibido usar frases técnicas como "hago un POST al endpoint", "envío un JSON" o "valido el status code 200".

## Estrategia de Datos y Peticiones REST
- **Modelos (POJOs):** La construcción de los Request Bodies (JSON) se debe hacer a través de clases modelo encapsuladas en el paquete model/ (ej. Pet, Category, Tag). Se confía en la serialización automática de RestAssured.
- **Datos Dinámicos (Fábrica de Datos):** Para evitar colisiones en las pruebas, los IDs y URLs deben generarse de forma dinámica (ej. usando System.currentTimeMillis()) mediante una clase factoría (PetDataFactory).

## Estructura de Serenity BDD Screenplay (API)
Tasks (Acciones): Deben implementar la interfaz Task. Deben ejecutar las interacciones nativas de Serenity Rest (Post.to(), Get.resource(), Put.to(), Delete.from()). Deben instanciarse siempre a través de un método estático utilizando Tasks.instrumented(). Dentro de los Tasks está prohibido incluir aserciones (Asserts).

Questions (Validaciones): Deben implementar la interfaz Question<T>. Son las únicas responsables de extraer el estado del sistema, típicamente leyendo la respuesta HTTP almacenada en memoria global: SerenityRest.lastResponse().statusCode().

Step Definitions: Actúan únicamente como puente ("glue code") entre Cucumber y los Actores. Deben orquestar el flujo utilizando exclusivamente theActorInTheSpotlight().attemptsTo(...) y validar los resultados usando actor.should(seeThat(...)).