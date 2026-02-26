# 📌 CRM API - Backend

Projeto pessoal de um CRM desenvolvido com Java e Quarkus, com foco em boas práticas de arquitetura backend, segurança e organização de código.

## 🚀 Tecnologias utilizadas

- Java 17+
- Quarkus
- Hibernate ORM / JPA
- PostgreSQL
- JWT (autenticação)
- Bean Validation

## 🏗 Arquitetura

A aplicação segue o padrão em camadas:

Controller → Service → Repository

- Controllers: recebem requisições e retornam respostas
- Services: concentram regras de negócio
- Repositories: acesso a dados
- DTOs: utilizados para entrada e saída de dados
- Tratamento global de exceções

## 📦 Funcionalidades

- CRUD de Usuários
- CRUD de Departamentos
- CRUD de Clientes
- CRUD de Eventos

## 📑 Regras de negócio implementadas

- Validação de CPF
- Validação de e-mail único
- Evento deve estar associado a um cliente
- Controle de acesso por perfil (Admin / Gestor / Usuário)
- Usuários visualizam apenas seus próprios eventos

## 🔐 Segurança

- Senhas armazenadas com hash
- Autenticação baseada em JWT
- Controle de autorização por perfil

## ▶️ Como executar o projeto

1. Clone o repositório
2. Configure o banco de dados no application.properties
3. Execute:

```bash
./mvnw quarkus:dev
