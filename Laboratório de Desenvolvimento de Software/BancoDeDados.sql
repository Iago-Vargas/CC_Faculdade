-- Aula!
CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(30),
    placa VARCHAR(7),
    id_pessoa SMALLINT NOT NULL, -- Tipo de dado ajustado para SMALLINT
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);
-- Casa!
CREATE TABLE Disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professores(id)
);

