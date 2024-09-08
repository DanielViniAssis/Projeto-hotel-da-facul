# Projeto de Gerenciamento de Reservas (Java MVC + PostgreSQL)

## Descrição do Projeto
Este é um projeto de gerenciamento de reservas desenvolvido em Java, seguindo a arquitetura MVC (Model-View-Controller) e utilizando o banco de dados PostgreSQL. O sistema permite que o funcionario crie novas reservas, inserindo os dados do cliente, atualizando todas as informações do cliente e da reserva, deletando as informações tanto da reserva como do cliente e realizar o gerenciamento dos quartos como verificar e atualizar a disponibilidade.

## Funcionalidades Principais
- Cadastro de funcionário: Permite adicionar, editar e remover o funcionário.
- Visualização das reservas: Exibe uma lista completa das reservas cadastradas.

## Arquitetura
### Este projeto segue o padrão MVC (Model-View-Controller):

- Model: Responsável pela lógica de negócios e pela comunicação com o banco de dados.
- View: Interface do usuário, desenvolvida com SpringCLI para apenas rodar no terminal do VsCode.
- Controller: Responsável por receber as requisições do usuário, interagir com o model e atualizar a view.
- Tecnologias Utilizadas
- Linguagem: Java 19
- Framework: SpringCLI.
- Banco de Dados: PostgreSQL
- JDBC: Para integração com o banco de dados
- IDE: Visual Studio Code
- Ferramenta de Build: Maven
