# 📚 Gerenciador de Biblioteca
 
Sistema de gerenciamento de empréstimos de livros desenvolvido com Spring Boot, com frontend em HTML + Bootstrap e deploy na Azure.
 
> Projeto desenvolvido durante o **Bootcamp Java - Deloitte 2026**
 
---
 
## 🚀 Deploy
 
A aplicação está disponível em:
 
🔗 **https://app-paulobezerra.azurewebsites.net/index.html**
 
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
│   ├── UsuarioRequestDTO.java
│   ├── UsuarioResponseDTO.java
│   ├── LivroRequestDTO.java
│   ├── LivroResponseDTO.java
│   └── EmprestimoResponseDTO.java
├── entity/
│   ├── Usuario.java
│   ├── Livro.java
│   └── Emprestimo.java
├── exception/
│   └── GlobalExceptionHandler.java  # Tratamento global de erros
├── repository/
│   ├── UsuarioRepository.java
│   ├── LivroRepository.java
│   └── EmprestimoRepository.java
├── service/
│   ├── UsuarioService.java
│   ├── LivroService.java
│   └── EmprestimoService.java
└── GerenciadorBibliotecaApplication.java
 
src/main/resources/
├── static/
│   └── index.html                 # Frontend SPA com Bootstrap
└── application.properties
```
 
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
 
3. Execute a aplicação pelo IntelliJ ou via terminal:
```bash
./mvnw spring-boot:run
```
 
4. Acesse no navegador:
```
http://localhost:8080/index.html
```
 
---
 
## 🔐 Autenticação
 
A API utiliza **Basic Auth**. Use as credenciais abaixo para autenticar:
 
| Campo | Valor |
|---|---|
| Usuário | `admin` |
| Senha | `admin123` |
 
> No frontend, a autenticação é feita automaticamente via JavaScript.
 
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
 
### 📚 Livros
 
| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/livros` | Cadastrar livro | ✅ |
| `GET` | `/livros` | Listar todos | ✅ |
| `GET` | `/livros/{id}` | Buscar por ID | ✅ |
| `PUT` | `/livros/{id}` | Atualizar | ✅ |
| `DELETE` | `/livros/{id}` | Deletar | ✅ |
 
### 🔄 Empréstimos
 
| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/emprestimos?usuarioId={id}&livroId={id}` | Registrar empréstimo | ✅ |
| `GET` | `/emprestimos` | Listar todos | ✅ |
| `PUT` | `/emprestimos/{id}/devolver` | Devolver livro | ✅ |
 
---
 
## 📖 Documentação Swagger
 
Com a aplicação rodando, acesse a documentação interativa em:
 
```
http://localhost:8080/swagger-ui.html
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
 
## 🧱 Arquitetura
 
O projeto segue a arquitetura em camadas:
 
```
Controller → Service → Repository → Banco de Dados
     ↑            ↑
   DTOs       Entidades
```
 
| Camada | Responsabilidade |
|---|---|
| **Controller** | Receber requisições HTTP e retornar respostas |
| **Service** | Regras de negócio |
| **Repository** | Comunicação com o banco de dados |
| **Entity** | Mapeamento das tabelas do banco |
| **DTO** | Transferência de dados entre camadas |
| **Exception** | Tratamento global de erros |
| **Config** | Configurações de segurança e documentação |
 
---
 
## ✅ Funcionalidades
 
- [x] CRUD completo de Usuários
- [x] CRUD completo de Livros
- [x] Registro de Empréstimos
- [x] Devolução de Livros
- [x] Controle de disponibilidade do livro
- [x] Validação de campos com mensagens de erro
- [x] Tratamento global de exceções
- [x] Autenticação com Spring Security (Basic Auth)
- [x] Documentação com Swagger UI
- [x] Frontend SPA com Bootstrap
- [x] Deploy na Azure
 
---
 
## 📄 Licença
 
Este projeto foi desenvolvido para fins educacionais durante o Bootcamp Java da Deloitte.
