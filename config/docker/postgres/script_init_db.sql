
CREATE TABLE IF NOT EXISTS sessao (
  id_sessao INT NOT NULL,
  inicio TIMESTAMP,
  duracao TIMESTAMP ,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS pauta (
  id_pauta INT NOT NULL,
  nome varchar(250) NOT NULL,
  descricao varchar(250) NOT NULL,
  id_sessao INT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_sessao) REFERENCES sessao (id_sessao)
);