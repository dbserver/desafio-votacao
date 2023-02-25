CREATE TABLE assembleia
(
    id     bigint    not null auto_increment,
    inicio timestamp NOT NULL,
    fim    timestamp,
    status text      NOT NULL,
    primary key (id)
);

CREATE TABLE pauta
(
    id            bigint       not null auto_increment,
    titulo        varchar(100) NOT NULL,
    status        text         NOT NULL,
    assembleia_id bigint       NOT NULL,
    primary key (id),
    FOREIGN KEY (assembleia_id) REFERENCES assembleia (id)
);

CREATE TABLE sessao_de_votacao
(
    id       bigint    not null auto_increment,
    inicio   timestamp NOT NULL,
    fim      timestamp NOT NULL,
    status   text      NOT NULL,
    pauta_id bigint    NOT NULL,
    primary key (id),
    FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);

CREATE TABLE voto
(
    id                   bigint not null auto_increment,
    status               text   NOT NULL,
    sessao_de_votacao_id bigint NOT NULL,
    FOREIGN KEY (sessao_de_votacao_id) REFERENCES sessao_de_votacao (id),
    PRIMARY KEY (id)
);

CREATE TABLE associado
(
    id      bigint       not null auto_increment,
    nome    varchar(100) NOT NULL,
    cpf     varchar(11)  NOT NULL,
    status  text         NOT NULL,
    primary key (id)
);

CREATE TABLE assembleia_pautas
(
    assembleia_id bigint NOT NULL,
    pauta_id      bigint NOT NULL,
    FOREIGN KEY (assembleia_id) REFERENCES assembleia (id),
    FOREIGN KEY (pauta_id) REFERENCES pauta (id)
);

CREATE TABLE pauta_sessao_de_votacao
(
    pauta_id             bigint NOT NULL,
    sessao_de_votacao_id bigint NOT NULL,
    FOREIGN KEY (pauta_id) REFERENCES pauta (id),
    FOREIGN KEY (sessao_de_votacao_id) REFERENCES sessao_de_votacao (id)
);

CREATE TABLE sessao_de_votacao_votos
(
    sessao_de_votacao_id bigint NOT NULL,
    voto_id              bigint NOT NULL,
    FOREIGN KEY (sessao_de_votacao_id) REFERENCES sessao_de_votacao (id),
    FOREIGN KEY (voto_id) REFERENCES voto (id)
);

CREATE TABLE voto_associado
(
    voto_id      bigint NOT NULL,
    associado_id bigint NOT NULL,
    FOREIGN KEY (voto_id) REFERENCES voto (id),
    FOREIGN KEY (associado_id) REFERENCES associado (id)
);

CREATE TABLE assembleia_associados
(
    assembleia_id bigint NOT NULL,
    associado_id  bigint NOT NULL,
    FOREIGN KEY (assembleia_id) REFERENCES assembleia (id),
    FOREIGN KEY (associado_id) REFERENCES associado (id)
);