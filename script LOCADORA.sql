DROP TABLE IF EXISTS cliente;

DROP TABLE IF EXISTS carro;

CREATE TABLE cliente (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    cpf INTEGER NOT NULL UNIQUE,
    nome VARCHAR(80) NOT NULL,
    datanasc TEXT NOT NULL,
    logradouro VARCHAR(80) NOT NULL,
    numero INTEGER NOT NULL,
    complemento VARCHAR(20) NOT NULL,
    bairro VARCHAR(40) NOT NULL,
    cidade VARCHAR(40) NOT NULL,
    cep INTEGER NOT NULL,
    uf CHAR(2) NOT NULL,
    ddd INTEGER NOT NULL,
    telefone INTEGER NOT NULL
);

CREATE TABLE carro (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(50) NOT NULL,
    anofabr INTEGER NOT NULL,
    km INTEGER NOT NULL
);