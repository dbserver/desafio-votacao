INSERT INTO assembleia(id, nome, inicio_assembleia, fim_assembleia, status) values
       ('d59c555e-1a85-451b-8009-e155ce570b38', 'Assembleia de Teste', '2023-01-30 14:00:10.123872', '2023-01-30 15:00:10.123872', 'FINALIZADO');

INSERT INTO pauta(id, hash, titulo, descricao ,autor_pauta, resultado, andamento) values
       ('5ca07fd1-4419-401a-8ceb-58b3f92c24de', '28-DBs3R', 'Padronizacao de tecnologia', 'Atraves da padronizacao todo o time utiliza as mesmas ferramentas para o desenvolvimento das aplicacoes','510292a5-d4fe-4482-a7cd-4eb7023a9b1f', 'APROVADO', 'CONCLUIDO' );

INSERT INTO votante(id, cod_associado, voto) values
        ('510292a5-d4fe-4482-a7cd-4eb7023a9b1f', '28chrltt' , 'AUTORIA'),
        ('3a38279d-f43d-485e-8160-4f7d0fd4cd1c', '32momoka','SIM'),
        ('cfc74cf3-c6a8-4aa8-b716-9b0086a8a4f6', '36yanagi','SIM');

INSERT INTO assembleia_pauta(pauta_id, assembleia_id) values
        ('5ca07fd1-4419-401a-8ceb-58b3f92c24de', 'd59c555e-1a85-451b-8009-e155ce570b38');
     
INSERT INTO pauta_votantes(pauta_id, votantes_id) values
        ('5ca07fd1-4419-401a-8ceb-58b3f92c24de', '510292a5-d4fe-4482-a7cd-4eb7023a9b1f'),
        ('5ca07fd1-4419-401a-8ceb-58b3f92c24de', '3a38279d-f43d-485e-8160-4f7d0fd4cd1c'),
        ('5ca07fd1-4419-401a-8ceb-58b3f92c24de','cfc74cf3-c6a8-4aa8-b716-9b0086a8a4f6');

       