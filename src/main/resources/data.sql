insert into tb_user (name, password, email, description, created_at, updated_at, inactivated_at, is_active) values 
('Tiago', '123456','tiago@email.com', 'descricao do perfil', now(), now(), now(), true),
('Mariazinha', '1123456','mariazinha@email.com', 'descricao dela', now(), now(), now(), true),
('João', '321412','joao@email.com', 'joão do perfil', now(), now(), now(), true),
('Aline', '323232','line@email.com', 'line linda', now(), now(), now(), true)
;

insert into tb_knowledge(name) values
('empinar pipa'),
('cozinhar'),
('matemática'),
('japão'),
('nadar'),
('fotografia')
;

insert into tb_user_knowledge (user_id, knowledge_id) values
(1,3),
(1,4),
(1,5),
(2,2),
(2,3),
(2,4),
(3,1),
(3,4),
(3,6)
;

insert into tb_shared_session (created_at, updated_at, status) values
(now(), now(), 2),
(now(), now(), 3),
(now(), now(), 1)
;

insert into tb_user_session (user_id, session_id) values
(1,1),
(2,1),
(2,2),
(3,2),
(1,3),
(4,3)
;
