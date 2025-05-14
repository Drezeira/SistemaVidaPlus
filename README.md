# SistemaVidaPlus

Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS) desenvolvido para a instituição VidaPlus. Este projeto foca no back-end, fornecendo uma API RESTful para gerenciar pacientes, consultas, usuários e logs do sistema.

## Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.4.4
- **Build e Gerenciamento de Dependências:** Maven
- **Banco de Dados:** MySQL 8.0
- **Documentação da API:** Swagger UI (Springdoc OpenAPI)
- **Segurança:** Spring Security (Autenticação HTTP Basic)

## Pré-requisitos

Antes de iniciar, certifique-se de que você tem os seguintes softwares instalados em sua máquina:

- JDK 21 (Java Development Kit)
- Maven 3.6 ou superior
- MySQL Server 8.0
- Um cliente SQL de sua preferência para importar o dump do banco (ex: MySQL Workbench, DBeaver, ou via linha de comando)
- Git (para clonar o repositório)

## Configuração do Ambiente

Siga os passos abaixo para configurar o ambiente de desenvolvimento localmente.

### 1. Clonando o Repositório

Abra seu terminal ou Git Bash e clone o repositório do projeto:

```bash
git clone https://github.com/Drezeira/SistemaVidaPlus
```

Após clonar, navegue até o diretório correto do projeto Spring Boot:

```bash
cd SistemaVidaPlus/Sghss/SistemaVidaPlus
```

> **Nota:**  
> Se o repositório clonado já contém o arquivo `pom.xml`, o comando pode ser apenas:
>
> ```bash
> cd SistemaVidaPlus
> ```

### 2. Configuração do Banco de Dados MySQL

A aplicação requer um banco de dados MySQL chamado `sghss_db`.

#### a. Criação do Banco de Dados (se não existir)

A string de conexão no arquivo `application.properties` inclui o parâmetro `createDatabaseIfNotExist=true`. Assim, o banco `sghss_db` será criado automaticamente na primeira tentativa de conexão, caso ainda não exista.

#### b. Importando o Dump do Banco de Dados

O arquivo de dump `Dump20250507.sql` está localizado em:

```
Sghss/Arquivos Auxiliares/dumps/
```

##### Instruções para importação via MySQL Workbench:

1. Abra o MySQL Workbench.
2. Vá em `Server > Data Import`.
3. Em **Import Options**, selecione `Import from Self-Contained File`.
4. Clique em `...` e selecione `Dump20250507.sql`.
5. Em **Default Schema to be Imported To**, selecione `sghss_db` (ou deixe em branco para criar).
6. Marque a opção `Dump Structure and Data`.
7. Clique em **Start Import**.

#### c. Verifique as Credenciais no `application.properties`

Arquivo: `src/main/resources/application.properties`

```properties
spring.datasource.username=root # ('Troque sua usuário do Banco de Dados aqui')
spring.datasource.password=Root # ('Troque sua senha do Banco de Dados aqui')
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/sghss_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
```

> **Importante:**  
> Altere `spring.datasource.username` e `spring.datasource.password` conforme suas credenciais locais do MySQL.

### 3. Executando a Aplicação

Após configurar o banco de dados, navegue até o diretório raiz do projeto (onde está o `pom.xml`) e execute:

```bash
mvn spring-boot:run
```

A aplicação será iniciada e ficará acessível em:

```
http://localhost:8080
```

### 4. Acessando a API e Documentação (Swagger UI)

Com a aplicação em execução, acesse:

```
http://localhost:8080/swagger-ui.html
```

Lá você pode:

- Visualizar todos os endpoints
- Ver os parâmetros esperados
- Testar chamadas diretamente no navegador

Você também pode usar ferramentas como Postman para testar os endpoints.

### 5. Usuários de Teste

Para acessar endpoints protegidos, use as credenciais abaixo (disponíveis no dump):

Papel Admin

- **Login:** Admin
- **Senha:** admin
- **Role:** ADMIN

Papel Profissional de Saúde

- **Login:** Gertrudes
- **Senha:** Gertrudes123
- **Role:** PROFISSIONAL

> **Observação:**  
> A autenticação usada é HTTP Basic. Ao fazer requisições autenticadas (Swagger ou Postman), insira essas credenciais.
