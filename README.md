# ⛽ Fuel Intelligence API

Este é um projeto pessoal desenvolvido com o objetivo de consolidar e aplicar meus conhecimentos 
A API foca no gerenciamento de postos de combustíveis e suas respectivas cotações de preços.

## 🎯 Propósito do Projeto
Este sistema foi construído especificamente para **testar meus conhecimentos** em arquitetura de software, explorando a integração entre o ecossistema Spring e a containerização de infraestrutura.
O foco foi validar o fluxo completo de uma aplicação, desde a requisição no cliente até a persistência segura no banco de dados.

---

## 🛠️ Stack Tecnológica e Aprendizados

* **Java 17 & Spring Boot 3**: Implementação de uma API RESTful robusta, explorando Injeção de Dependência e inversão de controle.
* **PostgreSQL**: Banco de dados relacional escolhido para aplicar conhecimentos de modelagem de dados e integridade referencial.
* **Docker & Docker Desktop**: Configuração de infraestrutura como código, garantindo que o banco e as ferramentas de gestão rodem em containers isolados.
* **pgAdmin4**: Ferramenta de administração de banco de dados integrada via container para monitoramento em tempo real.
* **Spring Data JPA / Hibernate**: Automatização do mapeamento objeto-relacional (ORM) e persistência de dados.

---

## 🏗️ Diferenciais Técnicos Aplicados

* **Custom ID Generation**: Desenvolvimento de uma lógica própria para geração de identificadores de 6 dígitos, facilitando a identificação visual dos registros.
* **Padrão DTO (Data Transfer Object)**: Separação rigorosa entre as entidades de banco e os dados trafegados, garantindo segurança e boas práticas de desenvolvimento.
* **Ambiente Dockerizado**: O projeto utiliza containers para que qualquer desenvolvedor consiga rodar o ambiente completo com apenas dois comandos, sem instalações manuais de banco de dados.

---

