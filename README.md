# Payment Manager

O **Payment Manager** é uma aplicação backend desenvolvida em **Spring Boot** que gerencia transações de pagamento para usuários. A aplicação integra com o Mercado Pago para processar pagamentos e armazena o histórico de transações em um banco de dados PostgreSQL.

## Funcionalidades

- **Criação de transações**: Permite que os usuários realizem transações de pagamento.
- **Histórico de transações**: Armazena e gerencia o histórico de transações realizadas, com status como `pending`, `approved`, `failed`.
- **Integração com Mercado Pago**: Processa pagamentos usando o Mercado Pago, com suporte a métodos como PIX.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para o desenvolvimento do backend.
- **Spring Data JPA**: Para acesso ao banco de dados PostgreSQL.
- **Mercado Pago API**: Para integração com o sistema de pagamentos.
- **PostgreSQL**: Banco de dados utilizado para armazenar informações dos usuários e transações.
- **Maven**: Gerenciador de dependências.
- **Docker**: Para containerização do banco de dados PostgreSQL.

## Estrutura do Projeto

```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── Nordertino
│   │   │           └── payment_manager
│   │   │               ├── controller
│   │   │               ├── domain
│   │   │               ├── repositories
│   │   │               ├── request
│   │   │               ├── service
│   │   │               └── PaymentManagerApplication.java
│   │   └── resources
│   │       ├── application.proper

