<h1 align="center">
  Xmen-Challenge
</h1>

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [Swagger by SpringDoc](https://springdoc.org/)
- [H2](https://www.h2database.com/html/main.html)

## Como Executar

### Acessando pela AWS
- Acessar a URL na AWS:
```
git clone git@github.com:gabrielhedric/xmen-challenge.git
```
- Acessar aplicação em `url-aws`.

### Acessando localmente

- Clonar repositório git para execução local:
```
git clone git@github.com:gabrielhedric/xmen-challenge.git
```
- Executar a aplicação Spring Boot
- Acessar aplicação em `http://localhost:8080`.

## Arquitetura

![Desenho de Arquitetura](.github/Arquitetura%Xmen-Challenge.png)

## API

- http: 8080/mutant
```
HTTP/POST
curl --location 'http://localhost:8080/mutant' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Wolwerine",
    "seq": ["AGGTCA","CCGTGA","GTCCAGA","CGTTGA","TATAGGA","TTTTGC"]
}'
```

- http: 8080/mutant
```
curl --location 'http://localhost:8080/mutant'
```