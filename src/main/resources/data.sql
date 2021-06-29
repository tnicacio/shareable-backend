insert into tb_user (name, password, email, image, description, created_at, updated_at, inactivated_at, is_active) values 
('Tiago', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG','tiago@email.com', 'image-url', 'descricao do perfil', now(), now(), null, true),
('Mariazinha', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG','mariazinha@email.com', 'image-url', 'descricao dela', now(), now(), null, true),
('João', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG','joao@email.com', 'image-url', 'joão do perfil', now(), now(), now(), false),
('Aline', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG','line@email.com', 'image-url', 'line linda', now(), now(), null, true)
;

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);

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

insert into tb_session (created_at, updated_at, status) values
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
