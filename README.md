# Book Summary (Trabalho A3)
![logo](img/logo.JPG)

# Sobre o Projeto

Este repositório contém o projeto desenvolvido como parte da disciplina "Estruturas de Dados e Análise de Algoritmos" da
faculdade UNA Betim, Trabalho A3. O projeto aborda o desenvolvimento de uma aplicação dedicada a facilitar o acesso a
resumos de livros de maneira fácil e rápida.

# Índice/Sumário

* [Sobre](#sobre-o-projeto)
* [Sumário](#índice/sumário)
* [Requisitos Funcionais](#requisitos-funcionais)
* [Tecnologias Usadas](#tecnologias-usadas)
* [Execução Local](#execução-local)
* [Documentação do Swagger](#documentação-do-swagger)
* [Histórico de Versões](#Histórico-de-Versões)
* [Licença](#Licença)
* [Autores](#autores)

# Requisitos Funcionais

- [ ] Cadastrar Usuário
- [ ] Fazer Login (autenticação)
- [ ] Cadastrar resumos de livros
- [ ] Buscar resumos de livros
- [ ] Compartilhar resumos de livro por e-mail
- [ ] Curtir e Comentar resumos de livro

# Tecnologias Usadas

- [Java](https://www.java.com/pt-BR/)
- [Spring](https://spring.io/)
- [MySQL](https://www.mysql.com/)

# Execução Local

Antes de começar, certifique-se de ter as seguintes ferramentas e dependências instaladas em sua máquina:

- [Java Development Kit (JDK17)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3.9.5](https://maven.apache.org/download.cgi)
- [MySQL Server](https://dev.mysql.com/downloads/installer/)
- Um ambiente de desenvolvimento integrado (IDE), como [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Para executar a aplicação localmente e testar suas funcionalidades, siga os passos abaixo:

1. Clone este repositório em seu ambiente de desenvolvimento.

2. Configure as informações do banco de dados MySQL no arquivo `application.properties`.
   ```markdown
    spring.mail.username=username #adicione aqui o username do banco de dados
    spring.mail.password=password #adicione aqui a senha do banco de dados
   ```

3. Configure as informações do e-mail no arquivo para comunicação com o usuário `application.properties`.
   Para este projeto, utilizamos os servidores do Gmail para o envio de e-mails. Portanto, será necessário gerar uma
   'senha de aplicativo' para que a API possa se comunicar com o GMail. Esse processo pode ser realizado seguindo
   [este tutorial](https://www.jetbrains.com/idea/)
   ```markdown
    spring.datasource.username=email #adicione aqui o email (Gmail)
    spring.datasource.password=senha-app #adicione aqui a senha de aplicativo (Gmail)
   ```

4. Abra um terminal na raiz do projeto e execute o seguinte comando para compilar o projeto:
   ```bash
   mvn clean install
   ```
5. Após a conclusão da compilação, inicie a aplicação com o seguinte comando:
   ```bash
   mvn java -jar target/booksummar-1.0.0.jar
   ```
6. A aplicação estará disponível em http://localhost:8080. Você pode acessar a documentação do Swagger em Swagger UI.

# Documentação do Swagger

A documentação da API está disponível no Swagger. O Swagger fornece uma interface interativa que permite explorar e
testar as rotas da API diretamente do navegador.

- **URL da Documentação do Swagger:** [Swagger UI](http://localhost:8080/swagger-ui.html)

## Passos para Utilizar o Swagger

Siga os passos abaixo para começar a utilizar o Swagger:

1. Certifique-se de que a API está em execução localmente. Se você ainda não iniciou a aplicação, siga as instruções
   em [Execução Local](#execução-local).

2. Abra seu navegador da web e acesse a URL [Swagger UI](http://localhost:8080/swagger-ui.html).

3. Você verá a documentação interativa da API, listando todas as rotas disponíveis e detalhando os endpoints, métodos e
   parâmetros.

4. Para testar uma rota, clique em uma rota listada na documentação e use o botão "Try it out" para enviar solicitações
   e ver as respostas diretamente na interface do Swagger.

5. Explore as diferentes funcionalidades e endpoints da API usando a documentação do Swagger.

Lembre-se de que a documentação do Swagger é uma ferramenta valiosa para entender e testar sua API, facilitando o
desenvolvimento e a depuração de solicitações.

Aproveite a documentação interativa do Swagger para explorar e testar a API de maneira eficaz.


