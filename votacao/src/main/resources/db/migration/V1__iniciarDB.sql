CREATE TABLE associado (
 id serial PRIMARY KEY,
 nome VARCHAR(150) NOT NULL,
 cpf VARCHAR(11) NOT NULL,
 status VARCHAR(40) NOT NULL,
 UNIQUE (cpf)
);

CREATE TABLE assembleia (
 id serial PRIMARY KEY,
 inicio timestamp with time zone NOT NULL,
 fim timestamp with time zone NOT NULL
);

CREATE TABLE pauta (
id serial PRIMARY KEY,
descricao text NOT NULL,
inicio timestamp with time zone NOT NULL,
fim timestamp with time zone NOT NULL,
status text NOT NULL
);

CREATE TABLE voto (
 id serial PRIMARY KEY,
 pauta_id integer NOT NULL,
 associado_id integer NOT NULL,
 valor VARCHAR(10) NOT NULL,
 FOREIGN KEY (pauta_id) REFERENCES pauta (id),
 FOREIGN KEY (associado_id) REFERENCES associado (id)
);

CREATE TABLE assembleia_pauta (
 assembleia_id bigint NOT NULL REFERENCES assembleia (id) ON DELETE CASCADE,
 pauta_id bigint NOT NULL REFERENCES pauta (id) ON DELETE CASCADE,
 PRIMARY KEY (assembleia_id, pauta_id)
);

CREATE TABLE pauta_votacao (
 pauta_id bigint NOT NULL REFERENCES pauta (id) ON DELETE CASCADE,
 votos_id bigint NOT NULL REFERENCES voto (id) ON DELETE CASCADE,
 PRIMARY KEY (pauta_id, votos_id)
);