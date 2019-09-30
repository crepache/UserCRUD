# Cliente CRUD
CRUD de Clientes usando Spring Boot e MongoDB

### Requerimentos
- Java 8
- Docker
- Kubernetes

### Start da aplicaç~ao
- Va para a pasta do docker e rodar o sh start_mongoDB.sh 
- On Intellij rode o main ClienteApplication 

### Checar se a aplicacao esta rodando e o seu estado

- Va para a url: http://localhost:9443/cliente/actuator/health e check se o status esta "up".

### TO DO
- Unit tests
- Swagger para documentar todas APIs
- Schema para mapear todas exceptions
- Configurar um certificado para subir com https