<div align="center">
  
# 🚀 AUTO_API_PETSTORE_SCREENPLAY
### Taller Semana 5: Maestría en Automatización

**Autor:** Christopher Pallo  
**Entregable:** 3 de 3  
**Proyecto:** Automatización API REST (CRUD Completo) centrada en el patrón **Serenity Screenplay** con Serenity Rest, arquitectura limpia y máxima expresividad en el modelado del comportamiento del usuario sin interfaces gráficas.

<br />

### 🛠️ Technology Stack

**Automation Framework**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=java,gradle,cucumber" alt="Automation Stack" />
</a>

**Application Under Test (REST Services)**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=swagger,java" alt="App Stack" />
</a>

</div>

---

## 🎯 Contexto del Reto

Este repositorio corresponde al **Entregable 3 de 3** de la Maestría en Automatización. El objetivo es certificar el máximo dominio técnico sobre la implementación del patrón arquitectónico **Screenplay** aplicado a servicios REST, evaluando el salto paradigmático desde el POM (Page Object Model) tradicional y enfocando la validación puramente en la capa de servicios. Se demuestra la capacidad de validar el ciclo completo de vida de un recurso (CRUD) garantizando la separación de responsabilidades, alineado bajo la metodología BDD con Cucumber.

---

## 🛠️ Entorno y Prerrequisitos (Compatibilidad)

> ⚠️ **El uso de estas versiones exactas es obligatorio para compilar y ejecutar la suite correctamente.**

| Componente | Versión Requerida | Verificación |
|------------|------------------|--------------|
| **Java / JDK** | `17` (LTS) | `java -version` → `openjdk 17.x.x` |
| **Gradle** | `8.8` (distribución binaria) | `./gradlew --version` → `Gradle 8.8` |

### Java 17

El proyecto define `sourceCompatibility = JavaVersion.VERSION_17` y `targetCompatibility = JavaVersion.VERSION_17` en el `build.gradle`. Cualquier versión inferior provocará errores de compilación por features de lenguaje no soportadas. Versiones superiores (JDK 21+) pueden generar advertencias por APIs internas deprecadas.

### Gradle 8.8 (Wrapper Incluido)

El proyecto incluye el **Gradle Wrapper** (`gradlew`) preconfigurado en `gradle/wrapper/gradle-wrapper.properties` apuntando a `gradle-8.8-bin.zip`. Esto significa que **no es necesario instalar Gradle manualmente** — el wrapper descargará la versión exacta automáticamente en la primera ejecución. Esta versión fue seleccionada específicamente para eliminar los `Deprecated Gradle features` warnings que aparecen en versiones 9.x.

> 📝 **Nota del Autor:** La combinación **JDK 17 + Gradle 8.8** constituye la matriz de compatibilidad verificada para Serenity BDD 5.3.2, y Cucumber 7.18.1 con JUnit Platform 5.10.2. Esta configuración garantiza una ejecución estable, sin conflictos de dependencias ni advertencias de deprecación.

---

## 🔄 Escenario E2E: Ciclo de Vida Completo

El framework ejecuta un flujo End-to-End en capa de servicios de forma **idempotente** y **autocontenida** que garantiza la validación desde cero, sin datos residuales cruzados:

```
Registro (POST) ➜ Consulta (GET) ➜ Actualización (PUT) ➜ Eliminación (DELETE)
```

### Estrategia de Idempotencia

Cada ejecución genera identificadores inmutables a base de de **timestamps únicos**, eliminando fallos por duplicidad de registros o ids repetidos en la base de datos de PetStore con ejecuciones consecutivas:

```java
long uniqueId = System.currentTimeMillis();
```

Esta lógica reside en el modelo estructurado (`PetDataFactory.java`), manteniendo la generación dinámica encapsulada dentro de la instanciación de un patrón Builder con Screenplay.

### Flujo Gherkin (Declarativo)

```gherkin
# language: es
Esquema del escenario: CP-API - Ciclo de vida completo de una mascota en el sistema PetStore
  Dado que el gestor veterinario tiene acceso a los servicios de la PetStore
  Cuando él registra una nueva mascota en el sistema
  Y consulta los datos de la mascota registrada
  Y actualiza el estado de la mascota a sus bases operativas
  Entonces el sistema confirma la eliminación total del registro de la mascota
```

---

## 📊 Dashboard de Evidencias (CI/CD & Living Documentation)

> 🚀 **Despliegue Continuo (GitHub Pages)**
>
> Este proyecto cuenta con un flujo de Integración Continua que despliega los reportes de Serenity BDD en tiempo real tras cada *push*.
> 
> 🔗 **Enlace Oficial:** [https://christopherpalloarias.github.io/AUTO_API_PETSTORE_SCREENPLAY/](https://christopherpalloarias.github.io/AUTO_API_PETSTORE_SCREENPLAY/)
> 
> 📸 **Instrucción de Visualización:** Dentro del reporte, en el paso de **registro**, podrás encontrar la evidencia visual (URL) de la mascota creada dinámicamente.

---

## 🏗️ Arquitectura Screenplay

El framework está estructurado en capas semánticas que constituyen el núcleo del patrón Screenplay adaptado nativamente para servicios web REST (Serenity Rest):

| Capa | Paquete | Responsabilidad |
|---|---|---|
| 🎭 **Actores** | `stepdefinitions` | Cast estándar que otorga la habilidad `CallAnApi` de manera intrínseca al Actor |
| ✅ **Tasks** | `tasks` | Orquestan peticiones de negocio de alto nivel (CreatePet, GetPet, UpdatePet, DeletePet) |
| ⚡ **Interactions** | `interactions` | Manipulación granular de *headers*, tokens o directivas de Rest Assured (Opcional) |
| 📦 **Model** | `model` | Clases de representación de entidad serializables al payload REST (`PetDataFactory`) |
| ❓ **Questions** | `questions` | Consultan los nodos observables en los cuerpos de respuesta y *Status Codes* HTTP |

```
src/test/java/com/petstore/qa/
├── model/                # Data factories y POJOs con Patrón Builder (PetDataFactory)
├── tasks/                # Tasks SRP (CreatePet, GetPet, UpdatePet, DeletePet)
├── questions/            # Questions API (ServerResponseCode, PetDetails)
├── stepdefinitions/      # Glue BDD: wasAbleTo() + attemptsTo() + seeThat()
└── runners/              # CucumberTestSuite (JUnit Platform 5)
```

---

## 🏆 Cumplimiento de Rúbrica (Clean Code & SOLID)

Este framework ha sido auditado exhaustivamente y cumple al 100% con los criterios mandatorios:

### Clean Code

- [x] **Zero Comments (Regla de Oro):** Ausencia total de `//`, `/* */` y `/** */` en todas las clases Java desarrolladas. El código es su propia fuente documental.
- [x] **Nomenclatura Semántica 100% en Inglés:** Clases (`CreatePet`, `DeletePet`), métodos (`withDetails`, `forValidation`) y variables — todo concebido en inglés técnico de desarrollo.
- [x] **Zero UI/Selenium Leakage:** En el proyecto no existen referencias hacia WebDrivers, locators o targets. Completamente agnóstico al DOM.

### SOLID

- [x] **S — Single Responsibility:** Cada Task ejecuta **una sola acción en verbo HTTP** y lógica de un contexto subyacente (`CreatePet` solo opera POST, `GetPet` solo opera GET).
- [x] **O — Open/Closed:** Nuevos tipos de mapeos o cuerpos estructurados se pueden iterar sin alterar la abstracción madre configurada.
- [x] **L — Liskov Substitution:** Las tareas resuelven mediante polimorfismo extendiéndose de la interfaz base `Task` y respondiendo consistentemente al `performAs()`.
- [x] **I — Interface Segregation:** Cumplimiento de las interfaces mínimas y nativas en Screenplay: `performAs()` en lógicas, `answeredBy()` para validaciones HTTP.
- [x] **D — Dependency Inversion:** Resolución invertida del dominio principal. Cero APIs hardcodeadas. La constante recae en un `.conf` para inyección ambiental a nivel test.

### Screenplay Pattern

- [x] **`Tasks.instrumented()`:** Todas las Tasks usan `Tasks.instrumented(Class.class, args)` para habilitar el AOP de Serenity y documentar el `@Step`.
- [x] **Glue Limpio:** Las StepDefinitions se apoyan orgánicamente en `given()`, `when()` (con sus `attemptsTo()`) y `then()` encadenados con los respectivos `should(seeThat(...))`.
- [x] **OnStage Setup:** Uso ineludible y correcto del casting a nivel Global en el bloque de preparación de Suite BDD.
- [x] **Interactions Delegadas Nativas:** Operaciones internas manejadas por Serenity REST como `Post.to()` y `Put.to()`.

---

## ⚙️ Gestión de Configuración

### `serenity.conf`

| Propiedad | Valor | Propósito |
|-----------|-------|-----------|
| `base.url` | `https://petstore.swagger.io/v2` | URL base de la API externa consumida directamente por las habilidades de `CallAnApi` |
| `serenity.logging` | `VERBOSE` | Nivel de bitácora transaccional para atrapar *payloads* y rastrearlos en la documentación. |

### `build.gradle`

| Aspecto | Detalle |
|---------|---------|
| **Serenity BDD & Rest** | `5.3.2` (Versión estable con integraciones asertivas Rest Assured) |
| **Cucumber** | `7.18.1` (JUnit Platform Engine) |
| **JUnit Platform** | `5.10.2` |
| **Java** | `17` (source + target compatibility) |
| **Reportes** | `single-page-html` + render visual transaccional Serenity |

---

## ⚡ Instrucciones de Clonado y Setup (Entorno Local)

La suite de pruebas valida interacciones *End-to-End* en los servicios REST orgánicos, operando en tiempo real contra un ecosistema público en la nube.

### Paso 1: Clonar este Repositorio de Pruebas (El Framework)

```bash
git clone https://github.com/ChristopherPalloArias/AUTO_API_PETSTORE_SCREENPLAY.git
cd AUTO_API_PETSTORE_SCREENPLAY
```

### Paso 2: Verificación del Endpoint (La Aplicación)

A diferencia de un ecosistema en *Docker Local*, el flujo del negocio evalúa las transacciones contra la nube (PetStore Swagger).

1. La plataforma base y su catalogación reside y puede verificarse desde la UI abstracta de Swagger en:
   [https://petstore.swagger.io/#/](https://petstore.swagger.io/#/)
2. El framework de Serenity consumirá dinámicamente el URL base en la nube a través del atributo inyectado en la configuración. No necesitas arrancar *Docker* o servicios remotos de tu lado para comenzar los tests.

---

## ▶️ Ejecución y Generación de Reportes

Para despachar la suite en tu máquina de desarrollo de manera silenciosa pero reportada, e invocar el proceso de agregación y empaquetado final de Serenity:

```bash
./gradlew clean test aggregate
```

Al concluir exitosamente los 4 verbos validados, el framework volcará un **Living Documentation**, evidenciando los *EndPoints* consumidos, los *Request Bodies* enviados (formato JSON) intercepcionando peticiones a cabalidad, validado desde la perspectiva inmersiva del Actor en idioma Español.

* **El index maestro del informe lo encuentras en:**
  ```text
  target/site/serenity/index.html
  ```
*(Abre el archivo desde tu explorador web de preferencia o mediante plugins transaccionales de visualización directa).*

---

## 🧩 Consideraciones Técnicas y Retos de API (REST)

* **Serialización de los Cuerpos de Petición con POJOs:**  
  La construcción del Payload de la petición (POST y PUT) no se realiza armando `Strings` estáticos de JSON que se rompen con facilidad. Se estructuró bajo el enfoque orientado a Objetos a través de Clases Modelo (POJOs / Data Classes), enviando esta representación mediante Serenity Rest para orquestar la transformación JSON robustamente.

* **Validación Idempotente Dinámica de Contextos Sensibles:**  
  Para mantener la idempotencia real, las validaciones extraen el `timestamp` persistido temporalmente para asegurar que los endpoints (`/pet/{petId}`) atacan únicamente tu recurso sin dañar el ecosistema general del Taller, demostrando control avanzado a nivel parametrización.

* **Cadenas Asíncronas Rest:**  
  Las llamadas son seguidas implícitamente de aserciones `lastResponse()`. Las Questions `ServerResponseCode` analizan con un *then().statusCode()* sin fugas de responsabilidades en la definición de tareas.

* **Gestión Flexible de URLs para CI/CD:**  
  Las tareas confían fuertemente en que RestAssured es instruido temporalmente con los datos de `serenity.conf`. Esta base unificada es medular en despliegues automatizados porque facilita sobreescribir dicha directiva ambiental (`-Dbase.url=...`) mitigando la constante reformulación de los binarios.
