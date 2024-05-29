# Desafio Desenvolvedor Backend Spring Boot

Este é um repositório para testar minhas habilidades como desenvolvedor backend, foi pedido um CRUD de usuários com banco de dados MySQL.

## Autor

Vinicius Pires Barreto

## Pré-requisitos

Antes de iniciar com o processo de montar o ambiente e rodar a aplicação, verifique se tem o Java JDK 21 instalado e configurado, também verifique se tem o Docker Desktop (Windows) ou o Docker no linux instalado e rodando, pois será necessário para montar o ambiente a primeira vez.

## Ambiente

Para montar o ambiente, siga estes passos:

1. Monte um container com MySQL:

   ```bash
   docker run --name mysqlcontainer -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=backend-challenge -e MYSQL_USER=user -e MYSQL_PASSWORD=root -p 3306:3306 -d mysql:latest
   ```

1. Clone o repositório:
   ```bash
   git clone https://github.com/ViniciusPiresB/1sti-challenge-springboot.git
   ```
1. Navegue até a pasta do repositório:
   ```bash
   cd 1sti-challenge-springboot
   ```
1. Compile o projeto com Maven:
   ```bash
   ./mvnw clean package
   ```

Obs: Certifique-se de utilizar uma versão do Java compatível. Se possível, utilize as mesmas versões indicadas na seção "Versão do Java".

## Testes

Para rodar os testes, execute o seguinte comando:

```bash
./mvnw test
```

## Aplicação

## Rodando com Docker

Para rodar a aplicação com Docker, siga os passos abaixo:

1. Pare o container do MySQL caso tenha iniciado.
2. No terminal, navegue até a pasta do projeto.
3. Construa e inicie os containers com Docker Compose:
   ```bash
   docker-compose up --build
   ```
4. Acesse a aplicação em `http://localhost:3000`

## Rodando sem Docker

Para rodar a aplicação sem Docker, siga os passos abaixo:

1. Inicie novamente o container isolado do MySQL.
2. Configure as variáveis de ambiente no arquivo `application.properties` ou `application.yml` localizado em `src/main/resources` baseado nos exemplos fornecidos:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/backend-challenge?createDatabaseIfNotExist=true
   spring.datasource.username=user
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse a aplicação em `http://localhost:3000`

## Primeiro acesso

Assim que a aplicação estiver em execução, acesse o endpoint http://localhost:3000/user/first-user/get para obter o primeiro usuário do sistema com privilégios máximos.
A partir deste usuário, será possivel gerar tokens na rota de login para realizar todas as operações presentes na aplicação.

## Documentação Swagger

Para visualizar a documentação Swagger, acesse `http://localhost:3000/swagger-ui.html`

## Versão do Java

Este projeto foi desenvolvido e testado com o Java versão 21.
