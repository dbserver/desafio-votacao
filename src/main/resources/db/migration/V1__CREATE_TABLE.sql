create table associado (
    id bigint not null auto_increment,
    cpf varchar(255),
    nome varchar(255),
    primary key (id)
);

create table associado_sessoes (
    id bigint not null auto_increment,
    associados_id bigint,
    sessoes_id bigint,
primary key (id));

create table pauta (
    id bigint not null auto_increment,
assunto varchar(255), primary key (id));

create table sessao (
    id bigint not null auto_increment,
    duracao datetime(6),
    sessao_encerrada bit,
    total_votos_nao integer not null,
    total_votos_sim integer not null,
    voto smallint,
pauta_id bigint, primary key (id));

alter table associado add constraint cpf_uk unique (cpf);

alter table associado_sessoes add constraint sessao_id_fk
foreign key (sessoes_id) references sessao (id);

alter table associado_sessoes add constraint associado_id_fk
foreign key (associados_id) references associado (id);

alter table sessao add constraint pauta_id_fk
foreign key (pauta_id) references pauta (id);


