CREATE TABLE public.associado (
	id int8 NOT NULL,
	nome varchar(60) NOT NULL,
	cpf varchar(11) NOT NULL,
	CONSTRAINT associado_pk PRIMARY KEY (id),
	CONSTRAINT associado_unique UNIQUE (cpf)
);

CREATE TABLE public.pauta (
	id int8 NOT NULL,
	idredator int8 NOT NULL,
	titulo varchar(50) NOT NULL,
	dtpauta date NOT NULL,
	CONSTRAINT pauta_pk PRIMARY KEY (id),
	CONSTRAINT pauta_associado_fk FOREIGN KEY (idredator) REFERENCES public.associado(id)
);

CREATE TABLE public.sessao (
	id int8 NOT NULL,
	duracao int4 NOT NULL,
	idpauta int8 NOT NULL,
	dtinicio timestamp NOT NULL,
	dtfim timestamp NOT NULL,
	CONSTRAINT sessao_pk PRIMARY KEY (id),
	CONSTRAINT sessao_pauta_fk FOREIGN KEY (idpauta) REFERENCES public.pauta(id)
);

CREATE TABLE public.voto (
	id int8 NOT NULL,
	idassociado int8 NOT NULL,
	idsessao int8 NOT NULL,
	voto char NOT NULL,
	dtvoto timestamp NOT NULL,
	CONSTRAINT voto_pk PRIMARY KEY (id, idassociado),
	CONSTRAINT voto_associado_fk FOREIGN KEY (idassociado) REFERENCES public.associado(id),
	CONSTRAINT voto_sessao_fk FOREIGN KEY (idsessao) REFERENCES public.sessao(id)
);