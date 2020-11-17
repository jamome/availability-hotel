# availability-hotel
API disponibilidad de Hotel

**NOTA**
Se ha modificado el esquema de bases de datos propuesto en la práctica para añadir una mejora al enunciado. El script de creación de la Base de datos se encuentra en la carpeta SQL_BD del repositorio



Requisitos:
	Java 8
	(PostgreSQL) 12.4
	Maven 3.6.3

Pasos a realizar:

1- Crear en PostgreSQL la base de datos con el script facilitado
2- Configurar la conexion (Datasource) en el fichero aplication.yml que se encuentra en /api-hotel/src/main/resources/application.yml
	2.1 - url
	2.2 - usuario 
	2.3 - password
3- Compilar y ejecutar la aplicación desde maven
	3.1 Para compilar y ejecutar los test en la raíz del proyecto ejecutar el siguiente comando -> mvn clean install
	3.2 Para ejecutar en la raiz del proyecto el comando -> mvn spring-boot:run
	
Para probar la aplicación se podra acceder al swagger desde la siguiente url: http://localhost:8080/swagger-ui.html#/
