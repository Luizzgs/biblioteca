<div align="center">

# Sistema de Biblioteca 📖
Aplicativo em ***java*** para um sistema de biblioteca na disciplina de *Engenharia de Software II*.

[![PDF](https://img.shields.io/badge/DOCUMENTAÇÃO-PDF-blue.svg?logo=Google-Docs&logoColor=f5f5f5&style=for-the-badge)](PDF.pdf)

</div>

---

## Requisitos ⚙️

- `PostgreSQL JDBC Driver` para conexão com o banco de dados utilizando *PostgreSQL*;
- tabelas `.csv` com dados em `tables/`;
- configuração da conexão com banco de dados no arquivo `bd/ConexaoBD.java`.

<br>

## Uso 🛠️

1. Utilize o arquivo `app/Menu.java` para abertura do menu do aplicativo;
2. Botão `Cadastrar Livro` para cadastrar livro no banco de dados, inserindo *Título* e *ID*;
3. Botão `Cadastrar Aluno` para cadastrar aluno no banco de dados, inserindo *Nome* e *RA*;
4. Botão `Emprestar Livro` para realizar empréstimo de livro no banco de dados, inserindo *RA do aluno*, *Código do livro* e *Data prevista de devolução*;
5. Botão `Devolver Livro` para realizar devolução de livro no banco de dados, inserindo *RA do aluno*, *Código do livro* e atualizando o *status* do livro para **disponível**. Caso haja devolução atrasada por parte do aluno, cadastra um **débito** para o mesmo;
6. Botão `Buscar` onde pode-se realizar uma busca por **alunos** ou **livros** cadastrados no banco de dados, mostrando seus dados e empréstimos.

> Descrição das funcionalidades completas no **documento**.

#
