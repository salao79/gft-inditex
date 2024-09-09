# Proyecto de prueba Inditex

## Cómo probar:

### NOTA:

#### LOS DATOS DE PRUEBA SE CARGAN AUTOMATICAMENTE CON EL METODO:

com.gft.inditex.infrastructure.adapters.output.database.sql.repository.PrecioOutputJpaAdapter.popularDatos()

### POSTMAN:

Se puede iniciar el programa, importar la colección de Postman "ColeccionPostman.json" y peticionar directamente a la API.

### SWAGGER:
Se puede iniciar el programa y peticionar a la API desde http://localhost:8080/swagger-ui/index.html#/

### TESTS UNITARIOS DOMINIO:

Se pueden ejecutar los tests unitarios en la carpeta:

src/test/java/com/gft/inditex/domain/service/PrecioServiceUnitTest.java

### TESTS INTEGRACION DOMINIO (los del txt de requisitos):

Se pueden ejecutar los tests de integracion en la carpeta:

src/test/java/com/gft/inditex/domain/PrecioIntegrationTest.java

### TESTS INTEGRACION EXCEPCIONES REST:

Se pueden ejecutar los tests de integracion del controller en:

src/test/java/com/gft/inditex/domain/controller/ControllerIntegrationTest.java
