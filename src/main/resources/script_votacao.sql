CREATE SCHEMA VOTACAO;

CREATE TABLE VOTACAO.STATUS_SESSAO( 
    ID int primary key not null,
    NOME_STATUS_SESSAO varchar(255) not null
);



CREATE TABLE VOTACAO.PAUTA
(
    ID int primary key auto_increment,
    NOME varchar(255) not null,
    ID_STATUS_SESSAO int not null,
    TEMPO_SESSAO datetime,
	FOREIGN KEY(ID_STATUS_SESSAO) REFERENCES STATUS_SESSAO(ID)
);

CREATE TABLE VOTACAO.VOTO
(
    ID bigint primary key auto_increment,
    ID_PAUTA int not null,
    ID_ASSOCIADO int not null,
	VOTO varchar(3) not null,
	FOREIGN KEY(ID_PAUTA) REFERENCES PAUTA(ID),
	CONSTRAINT UC_VOTO UNIQUE (ID_PAUTA,ID_ASSOCIADO),
    CONSTRAINT CK_VOTO CHECK (VOTO IN('Sim','Não'))
    
);



\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\CARGA DOMINIO STATUS DE SESSÃO\\\\\\\\\\\\\\\\\\\\
insert into  VOTACAO.STATUS_SESSAO values(1,'CRIADO');
insert into  VOTACAO.STATUS_SESSAO values(2,'ABERTO');
insert into  VOTACAO.STATUS_SESSAO values(3,'FINALIZADO');
