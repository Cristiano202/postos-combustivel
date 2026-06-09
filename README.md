# ⛽ Fuel Intelligence API

Este é um projeto backend desenvolvido com o objetivo de consolidar e aplicar conhecimentos avançados em arquitetura de software, automação de infraestrutura e práticas modernas de DevOps. A API foca no gerenciamento estratégico de postos de combustíveis e suas respectivas cotações de preços.

## 🎯 Propósito do Projeto
Este sistema foi construído especificamente para explorar a integração entre o ecossistema Spring e a containerização de infraestrutura. O foco foi validar o fluxo completo de uma aplicação de nível corporativo: desde a validação da requisição no cliente, passando por uma esteira automatizada de testes, até a persistência segura e deploy contínuo na nuvem.

---

## 🛠️ Stack Tecnológica

* **Java 21 & Spring Boot 3**: Uso dos recursos mais recentes da linguagem para implementação de uma API RESTful robusta, explorando Injeção de Dependência e inversão de controle.
* **PostgreSQL**: Banco de dados relacional escolhido para aplicar conhecimentos de modelagem de dados, integridade referencial e indexação.
* **Docker & Docker Desktop**: Configuração de infraestrutura como código (IaC), garantindo ambientes isolados e idênticos para desenvolvimento e testes.
* **GitHub Actions**: Automatização completa do pipeline de CI/CD (Integração e Entrega Contínuas).
* **Spring Data JPA / Hibernate**: Automatização do mapeamento objeto-relacional (ORM) e persistência eficiente de dados.

---

## 🏗️ Diferenciais Técnicos Aplicados

* **Pipeline de CI/CD Profissional**: Integração com GitHub Actions que, a cada *Pull Request* ou *Merge* na branch `main`, provisiona dinamicamente um container PostgreSQL via Docker, roda os testes unitários/integrados com Maven e, se tudo estiver verde, dispara o deploy automaticamente para o ambiente de produção.
* **Custom ID Generation**: Desenvolvimento de uma lógica própria para geração de identificadores de 6 dígitos, facilitando a identificação visual dos registros e otimizando buscas.
* **Padrão DTO (Data Transfer Object)**: Separação rigorosa entre as entidades de banco de dados e os dados trafegados na rede, garantindo segurança, encapsulamento e validação de dados com Bean Validation.
* **Ambiente Isomórfico (Dockerizado)**: O projeto utiliza containers para que o ambiente completo (banco e aplicação) possa ser replicado localmente com agilidade, eliminando o problema do *"na minha máquina funciona"*.

---

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos
* Java 21 instalado
* Docker e Docker Compose rodando

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SeuUsuario/postos-combustivel.git](https://github.com/SeuUsuario/postos-combustivel.git)
   cd postos-combustivel

   Suba o banco de dados via Docker:

2.Bash
docker-compose up -d
Execute a aplicação:

3.Bash
./mvnw spring-boot:run
A API estará disponível em http://localhost:8080.
