# User-Service

User-service es un microservicio forma parte de los microservicios de negocio que forman parte de la arquitectura que se ha propuesto para este trabajo. User-service permite realizar operaciones CRUD sobre una base de datos de usuarios finales (adultos mayores) y es accesible a través de una API REST.

### Objetivo
El principal objetivo de este microservicio es que la informción de los usuarios finales contenida en la base de datos de este microservicio se encuentre en un repositorio centralizado para el acceso y manipulación de los datos de los usuarios por todos los otros microservicios.

### Detalles de base de datos
La información que se presenta en la base de datos de Usuarios son:
  * id: identificador de registro de usuario (clave primaria).
  * numberID: Número de identidad del usuario (cédula).
  * firstName: Primer nombre del usuario.
  * lastName: Primer apellido del usuario.
  * sex: Sexo del usuario en inglés (Male o Female).
  * age: Edad en años del usuario.

### Métodos HTTP para el acceso a la API REST
  * allUsers (GET): /user/
  * userById (GET): /user/{id}
  * createUser (POST): /user/
  * updateUser (PUT): /user/{id}
  * deleteUser (DELETE): /user/{id}
