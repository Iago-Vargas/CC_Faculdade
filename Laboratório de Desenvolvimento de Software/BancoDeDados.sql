
CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(30),
    placa VARCHAR(7),
    id_pessoa SMALLINT NOT NULL, -- Tipo de dado ajustado para SMALLINT
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);
