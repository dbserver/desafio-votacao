-- Inserindo nova assembleia
INSERT INTO assembleia (id, inicio, fim)
VALUES (1, '2023-02-19T16:35:39.204Z', '2023-03-19T16:35:39.204Z' );

-- Inserindo nova Pauta
INSERT INTO pauta (id,descricao, inicio, fim) VALUES (1, 'Nova Pauta', '2023-02-19 14:00:00-03', '2023-02-19 17:00:00-03');

-- Inserindo nova Pauta na Assembleia
INSERT INTO assembleia_pauta (assembleia_id, pauta_id) VALUES (1, 1);