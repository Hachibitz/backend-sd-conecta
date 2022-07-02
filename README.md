# backend-sd-conecta

Aplicação API de um sistema que possui usuários que são médicos. O integra-se ao sistema SD Conecta para que os seus usuários médicos sejam enviados e cadastrados também no SD Conecta (caso autorizados através da conexão e validação com a API externa da SD CONECTA).

A aplicação foi desenvolvida, principalmente, com as tecnologias Java + Spring Boot + Spring Data JPA + Hibernate;

O banco de dados utilizado na implementação de persistência foi o H2.
O sistema conta com Swagger UI, pode ser acessada através do endereço "http://localhost:8082/swagger-ui.html" para facilitar o uso.

- A integração do sistema com o SD conecta foi realizada através do uso do FeignClient e de RestTemplate (conforme item 1).
- Estão disponíveis endpoints CRUD da aplicação (conforme item 4) para:  

Cadastrar,  

Alterar,  

Listar todos:  

API pode receber, opcionalmente, o nome para incluir como filtro;  

API pode receber, opcionalmente, a especialidade do CRM (specialty) para incluir como filtro;  

Deletar,  

Consultar usuário por ID;  

- Está disponível o endpoint para login com validação no sistema local e no sistema do SD conecta, persistindo na entidade o último status de "authorization_status" e retornando o as respostas conforme item 5->a->i/ii;  
- A criação do banco de dados foi realizada usando migrations com o uso da dependência FlyWay conforme critério de desempate 3;  
- Estão presentes testes unitários conforme critério de desempate 1.

**Dependências adicionais utilizadas na aplicação:**  

* OpenAPI Core/UI (Swagger) - (org.springdoc)
* Lombok - (org.projectlombok)
* h2 - (com.h2database)
* flyway-core  - (org.flywaydb)
* spring-cloud-starter-openfeign - (org.springframework.cloud)
* mapstruct - (org.mapstruct)

# Orientações:

**Recomendado: Rodar a aplicação na interface de desenvolvimento Spring Tool Suíte 4 e JavaSE-17;**  

- 1 - Importar o projeto como projeto Maven;
- 2 - Instalar o Lombok na IDE utilizada:  

Passo 1 --> Acessar o repositório local do maven depois de importado o projeto (No windows "C:\Users\{seuUsuario}\.m2\repository"),  

Passo 2 --> Abrir um terminal no diretório .\org\projectlombok\lombok\1.18.24,  

Passo 3 --> Executar o comando "java -jar lombok-1.18.24.jar",  

Passo 4 --> Selecionar sua IDE utilizada ou procurar o diretório da IDE e selecionar a opção "Instalar".  

*Observação importante: O Lombok substitui todos os constructors, getters and setters, hashcode & equals. Sem o Lombok instalado corretamente, a aplicação não funcionará.*
