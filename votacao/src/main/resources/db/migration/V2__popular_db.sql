-- Inserindo Associados
INSERT INTO associado (nome, documento,status ) VALUES ('Antonio Castro dos Santos','93698128063','PODE_VOTAR');
INSERT INTO associado (nome, documento,status ) VALUES ('Gabriel Luis Fulx','63381808052','PODE_VOTAR');
INSERT INTO associado (nome, documento,status ) VALUES ('Diego Garcia da Silva','66337627040','PODE_VOTAR');
INSERT INTO associado (nome, documento,status ) VALUES ('Carlos Augusto de Souza','98986516012','PODE_VOTAR');
INSERT INTO associado (nome, documento,status ) VALUES ('Felipe da Silva Souza','34971750053','NAO_PODE_VOTAR');

-- Inserindo assembleias
INSERT INTO assembleia (id, inicio, fim)
VALUES (1, '2023-02-19T16:35:39.204Z', '2023-03-19T16:35:39.204Z' );
INSERT INTO assembleia (id, inicio, fim)
VALUES (2, '2025-02-19T16:35:39.204Z', '2025-03-19T16:35:39.204Z' );
INSERT INTO assembleia (id, inicio, fim)
VALUES (3, '2025-02-19T16:35:39.204Z', '2025-03-19T16:35:39.204Z' );

-- Inserindo nova Pauta
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (1, 'Pauta 1', '2024-02-19 14:00:00-03', '2024-02-19 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (2, 'Pauta 2', '2025-02-19 14:00:00-03', '2025-02-19 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (3, 'Pauta 3', '2024-02-19 14:00:00-03', '2024-02-19 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (4, 'Pauta 4', '2024-05-19 14:00:00-03', '2024-06-19 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (5, 'Pauta 5', '2024-02-20 14:00:00-03', '2024-02-21 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (6, 'Pauta 5', '2024-01-20 14:00:00-03', '2024-01-21 14:00:00-03');

-- Inserindo Pautas em Assembleias
INSERT INTO assembleia_pauta(assembleia_id, pauta_id) VALUES (2,2);
INSERT INTO assembleia_pauta(assembleia_id, pauta_id) VALUES (2,3);
INSERT INTO assembleia_pauta(assembleia_id, pauta_id) VALUES (3,4);
INSERT INTO assembleia_pauta(assembleia_id, pauta_id) VALUES (3,5);
INSERT INTO assembleia_pauta(assembleia_id, pauta_id) VALUES (3,6);

-- Inserindo Votos
INSERT INTO voto (id, pauta_id, associado_id, valor)
VALUES (1, 1, 1, 'SIM');
INSERT INTO voto (id, pauta_id, associado_id, valor)
VALUES (2, 1, 2, 'SIM');
INSERT INTO voto (id, pauta_id, associado_id, valor)
VALUES (3, 1, 3, 'NAO');

-- Inserindo Votos nas Pautas
INSERT INTO pauta_votacao (pauta_id, votos_id)
VALUES (1, 1), (1, 2), (1, 3);

-- Inserindo novas sequencias nas tabelas, as 10 primeiras posições serão reservadas
ALTER SEQUENCE assembleia_id_seq RESTART WITH 10;
ALTER SEQUENCE voto_id_seq RESTART WITH 10;
ALTER SEQUENCE associado_id_seq RESTART WITH 10;
ALTER SEQUENCE pauta_id_seq RESTART WITH 10;