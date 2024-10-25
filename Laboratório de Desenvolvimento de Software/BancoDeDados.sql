-- Aula!
CREATE DATABASE bdaula01;
USE BDAula01;

CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(30),
    placa VARCHAR(7),
    id_pessoa SMALLINT NOT NULL, -- Tipo de dado ajustado para SMALLINT
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);
CREATE TABLE pessoa (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    idioma VARCHAR(10) NOT NULL
);
INSERT INTO pessoa (nome, sexo, idioma) VALUES 
("Igor", "M", "PORTUGUÊS"),
("Iago Vargas", "F", "Punjabi"),
("Maria Clara", "F", "Turco");



-- Casa!CREATE DATABASE BDAula01;
CREATE DATABASE escola;
USE escola;
CREATE TABLE disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professores(id)
);
CREATE TABLE matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_professor INT,
    data_matricula DATE,
    FOREIGN KEY (id_aluno) REFERENCES alunos(id),
    FOREIGN KEY (id_professor) REFERENCES professores(id)
);
CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    curso VARCHAR(50)
);
CREATE TABLE professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT,
    disciplina VARCHAR(50)
);

-- Trabalho! DATABASE mercado;
CREATE DATABASE mercado;
CREATE TABLE Categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);
CREATE TABLE Produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
);

