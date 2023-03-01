#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE TABLE IF NOT EXISTS sessao (
  id_sessao INT NOT NULL,
  inicio TIMESTAMP,
  duracao INT ,
  PRIMARY KEY (id_sessao)
);

CREATE TABLE IF NOT EXISTS pauta (
  id_pauta INT NOT NULL,
  nome varchar(250) NOT NULL,
  descricao varchar(250) NOT NULL,
  id_sessao INT,
  PRIMARY KEY (id_pauta),
  FOREIGN KEY (id_sessao) REFERENCES sessao (id_sessao)
);

CREATE TABLE IF NOT EXISTS voto (
  id_voto INT NOT NULL,
  cpf_associado varchar(14) NOT NULL,
  voto varchar(5) NOT NULL,
  id_pauta INT,
  PRIMARY KEY (id_voto),
  FOREIGN KEY (id_pauta) REFERENCES pauta (id_pauta)
);

CREATE SEQUENCE sessao_id_seq
INCREMENT 1
START 1;

CREATE SEQUENCE pauta_id_seq
INCREMENT 1
START 1;

CREATE SEQUENCE voto_id_seq
INCREMENT 1
START 1;
EOSQL