-- Inserindo nova associado
INSERT INTO associado (id, nome, documento, status)
VALUES (1, 'Usuario Teste', '90015955028', 'PODE_VOTAR');
-- Inserindo nova assembleia
INSERT INTO assembleia (id, inicio, fim)
VALUES (1, '2030-02-19T16:35:39.204Z', '2030-03-19T16:35:39.204Z');
-- Inserindo nova Pauta
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (1, 'Nova Pauta', '2030-02-19 14:00:00-03', '2030-02-19 17:00:00-03');
INSERT INTO pauta (id, descricao, inicio, fim)
VALUES (2, 'Nova Pauta', '2000-02-19 14:00:00-03', '2000-02-19 17:00:00-03');
-- Inserindo nova Pauta na Assembleia
INSERT INTO assembleia_pauta (assembleia_id, pauta_id)
VALUES (1, 2);
INSERT INTO assembleia_pauta (assembleia_id, pauta_id)
VALUES (1, 1);
-- Inserindo voto na Pauta
INSERT INTO voto (id, pauta_id, associado_id, valor)
VALUES (1, 1, 1, 'SIM');
