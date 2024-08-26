# insurance-customer-challenge

A execução do projeto depende da existencia de um banco de dados postgres. O banco pode ser inicializado com os comandos a seguir.
```bash
docker build -t postgres_16 .
```
```bash
docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres_16
```

Para executar qualquer um dos dois microsserviços basta executar o comando a seguir na pasta raiz de cada microsserviço
```bash
mvn spring-boot:run
```

As duas aplicações possuem um swagger, que podem ser acessados pelas seguintes urls:
- customer-service: http://localhost:8080/swagger-ui/index.html#/
- insurance-service: http://localhost:8081/swagger-ui/index.html#/
