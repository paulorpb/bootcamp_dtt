# 📚 Gerenciador de Biblioteca
 
Sistema de gerenciamento de empréstimos de livros desenvolvido com Spring Boot, com frontend em HTML + Bootstrap e deploy na Azure.
 
> Projeto desenvolvido durante o **Bootcamp Java - Deloitte 2026**
 
---
 
## 🚀 Deploy
 
A aplicação está disponível em:
 
🔗 **https://app-paulobezerra.azurewebsites.net/index.html**
 
📖 **Swagger UI (Produção):** https://app-paulobezerra.azurewebsites.net/swagger-ui.html
 
---
 
## 🛠️ Tecnologias Utilizadas
 
| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.5.11 | Framework backend |
| Spring Data JPA | - | Persistência de dados |
| Spring Security | - | Autenticação Basic Auth |
| Spring Validation | - | Validação de campos |
| H2 Database | - | Banco de dados em memória |
| Lombok | - | Redução de boilerplate |
| SpringDoc OpenAPI | 2.8.6 | Documentação Swagger UI |
| Bootstrap | 5.3.0 | Framework CSS frontend |
| Bootstrap Icons | 1.11.0 | Ícones |
 
---
 
## 📁 Estrutura do Projeto
 
```
src/main/java/com/paulobezerra/gerenciador_biblioteca/
├── config/
│   ├── SecurityConfig.java        # Configuração do Spring Security
│   └── SwaggerConfig.java         # Configuração do Swagger UI
├── controller/
│   ├── UsuarioController.java     # Endpoints de usuários
│   ├── LivroController.java       # Endpoints de livros
│   └── EmprestimoController.java  # Endpoints de empréstimos
├── dto/
│   ├── UsuarioRequestDTO.java     # Dados de entrada para usuário
│   ├── UsuarioResponseDTO.java    # Dados de saída para usuário
│   ├── LivroRequestDTO.java       # Dados de entrada para livro
│   ├── LivroResponseDTO.java      # Dados de saída para livro
│   └── EmprestimoResponseDTO.java # Dados de saída para empréstimo
├── entity/
│   ├── Usuario.java               # Entidade mapeada para tabela usuarios
│   ├── Livro.java                 # Entidade mapeada para tabela livros
│   └── Emprestimo.java            # Entidade mapeada para tabela emprestimos
├── exception/
│   └── GlobalExceptionHandler.java  # Tratamento global de erros
├── repository/
│   ├── UsuarioRepository.java     # Comunicação com banco - usuários
│   ├── LivroRepository.java       # Comunicação com banco - livros
│   └── EmprestimoRepository.java  # Comunicação com banco - empréstimos
├── service/
│   ├── UsuarioService.java        # Regras de negócio - usuários
│   ├── LivroService.java          # Regras de negócio - livros
│   └── EmprestimoService.java     # Regras de negócio - empréstimos
└── GerenciadorBibliotecaApplication.java
 
src/main/resources/
├── static/
│   └── index.html                 # Frontend SPA com Bootstrap
└── application.properties
```
 
---
 
## 🧱 Arquitetura em Camadas
 
O projeto segue a arquitetura em camadas, onde cada camada tem uma responsabilidade bem definida:
 
```
Controller → Service → Repository → Banco de Dados
     ↑            ↑
   DTOs       Entidades
```
 
| Camada | Responsabilidade |
|---|---|
| **Controller** | Recebe as requisições HTTP, delega para o Service e retorna a resposta ao cliente |
| **Service** | Contém as regras de negócio da aplicação |
| **Repository** | Responsável pela comunicação com o banco de dados |
| **Entity** | Representa as tabelas do banco de dados como objetos Java |
| **DTO** | Controla o que entra e o que sai da API, protegendo as entidades |
| **Exception** | Centraliza o tratamento de erros, retornando respostas padronizadas |
| **Config** | Configurações de segurança, documentação e outros beans do Spring |
 
---
 
## 📦 Uso de DTOs (Data Transfer Objects)
 
Para garantir segurança e encapsulamento, a API utiliza DTOs para separar a camada de persistência da camada de exibição:
 
- **RequestDTO**: Controla e valida os dados de entrada. O cliente nunca envia o `id` — quem controla isso é o banco.
- **ResponseDTO**: Define exatamente o que o cliente verá. Isso impede a exposição de campos internos das entidades JPA, como listas de relacionamentos que causariam recursão infinita.
 
---
 
## 🏗️ Entidades e Anotações JPA
 
As entidades representam as tabelas do banco de dados. Graças ao JPA e Hibernate, o Java se comunica com o banco sem precisar escrever SQL manualmente.
 
- `@Entity` — Indica que a classe é uma tabela no banco de dados
- `@Table(name = "usuarios")` — Define explicitamente o nome da tabela
- `@Id` e `@GeneratedValue` — Define a chave primária com auto-incremento
- `@Column(nullable = false)` — Garante que o campo não pode ser nulo no banco
- `@OneToMany` / `@ManyToOne` — Mapeia os relacionamentos entre entidades
 
---
 
## 🔐 Segurança
 
A API utiliza **Spring Security com Basic Auth**. Use as credenciais abaixo para autenticar:
 
| Campo | Valor |
|---|---|
| Usuário | `admin` |
| Senha | `admin123` |
 
Regras de acesso:
 
| Rota | Acesso |
|---|---|
| `POST /usuarios` | 🔓 Público — qualquer um pode se cadastrar |
| `/swagger-ui/**` | 🔓 Público |
| `/h2-console/**` | 🔓 Público |
| Todos os demais | 🔒 Requer autenticação |
 
> No frontend, a autenticação é feita automaticamente via JavaScript no header de cada requisição.
 
---
 
## ✅ Validações
 
Os campos são validados com **Jakarta Bean Validation** antes de chegarem ao banco de dados:
 
```java
@NotBlank(message = "Nome é obrigatório")
@Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
private String nome;
 
@Email(message = "Email inválido")
private String email;
```
 
Erros de validação retornam uma resposta padronizada com status `400` indicando exatamente qual campo está inválido e por quê.
 
---
 
## ⚠️ Tratamento de Erros
 
O `GlobalExceptionHandler` centraliza o tratamento de todas as exceções da aplicação, retornando respostas HTTP padronizadas:
 
| Tipo de erro | Status retornado |
|---|---|
| Campos inválidos (`@Valid`) | 400 Bad Request |
| Regra de negócio (ex: livro indisponível) | 400 Bad Request |
| Erro inesperado | 500 Internal Server Error |
 
---
 
## 🗄️ Camada de Persistência (Repository)
 
Os repositories estendem `JpaRepository`, o que faz o Spring Data JPA gerar automaticamente todas as operações SQL em tempo de execução:
 
- `save()` — Cria ou atualiza um registro
- `findAll()` — Retorna todos os registros
- `findById()` — Busca por chave primária
- `deleteById()` — Remove um registro
 
Isso significa que não precisamos escrever nenhuma linha de SQL para as operações básicas do CRUD.
 
---
 
## 📋 Endpoints da API
 
### 👤 Usuários
 
| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/usuarios` | Cadastrar usuário | ❌ Público |
| `GET` | `/usuarios` | Listar todos | ✅ |
| `GET` | `/usuarios/{id}` | Buscar por ID | ✅ |
| `PUT` | `/usuarios/{id}` | Atualizar | ✅ |
| `DELETE` | `/usuarios/{id}` | Deletar | ✅ |
 
**Body para cadastro/atualização:**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "81999999999"
}
```
 
### 📚 Livros
 
| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/livros` | Cadastrar livro | ✅ |
| `GET` | `/livros` | Listar todos | ✅ |
| `GET` | `/livros/{id}` | Buscar por ID | ✅ |
| `PUT` | `/livros/{id}` | Atualizar | ✅ |
| `DELETE` | `/livros/{id}` | Deletar | ✅ |
 
**Body para cadastro/atualização:**
```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884"
}
```
 
### 🔄 Empréstimos
 
| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/emprestimos?usuarioId={id}&livroId={id}` | Registrar empréstimo | ✅ |
| `GET` | `/emprestimos` | Listar todos | ✅ |
| `PUT` | `/emprestimos/{id}/devolver` | Devolver livro | ✅ |
 
---
 
## 📖 Documentação Swagger
 
### Localmente
 
Com a aplicação rodando, acesse:
```
http://localhost:8080/swagger-ui.html
```
 
### Em Produção (Azure)
 
```
https://app-paulobezerra.azurewebsites.net/swagger-ui.html
```
 
**Como testar pelo Swagger:**
 
1. Acesse o link acima
2. Clique em um endpoint (ex: `POST /usuarios`)
3. Clique em **"Try it out"**
4. Preencha o JSON no corpo da requisição
5. Clique em **"Execute"**
6. Confira o **Server Response**
 
---
 
## ⚙️ Como Executar Localmente
 
### Pré-requisitos
 
- Java 21+
- Maven 3.8+
- IntelliJ IDEA (recomendado)
 
### Passos
 
1. Clone o repositório:
```bash
git clone https://github.com/paulorpb/bootcamp_dtt.git
```
 
2. Acesse a pasta do projeto:
```bash
cd bootcamp_dtt/Main
```
 
3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```
 
4. Acesse no navegador:
```
http://localhost:8080/index.html
```
 
---
 
## 🗄️ Console H2
 
Para visualizar o banco de dados em memória, acesse:
 
```
http://localhost:8080/h2-console
```
 
| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:biblioteca` |
| Usuário | `sa` |
| Senha | *(vazio)* |
 
---
 
## ✅ Funcionalidades
 
- [x] CRUD completo de Usuários
- [x] CRUD completo de Livros
- [x] Registro de Empréstimos
- [x] Devolução de Livros
- [x] Controle automático de disponibilidade do livro
- [x] Validação de campos com mensagens de erro detalhadas
- [x] Tratamento global de exceções com respostas padronizadas
- [x] Autenticação com Spring Security (Basic Auth)
- [x] DTOs para separação entre entidades e API
- [x] Documentação interativa com Swagger UI
- [x] Frontend SPA com Bootstrap integrado ao backend
- [x] Deploy na Azure App Service
 
---
 
## 📚 Objetivo Educacional
 
Este projeto foi desenvolvido como exercício prático do **Bootcamp Java – Deloitte 2026**, com foco em:
 
- Construção de APIs REST com Spring Boot
- Arquitetura em camadas (Controller, Service, Repository)
- Persistência de dados com JPA e Hibernate
- Uso de DTOs para separação de responsabilidades
- Validação de dados com Bean Validation
- Segurança com Spring Security
- Documentação de APIs com Swagger/OpenAPI
- Desenvolvimento de frontend integrado ao backend
- Deploy em nuvem com Microsoft Azure
 
---
 
## 📄 Licença
 
Este projeto foi desenvolvido para fins educacionais durante o Bootcamp Java da Deloitte.
