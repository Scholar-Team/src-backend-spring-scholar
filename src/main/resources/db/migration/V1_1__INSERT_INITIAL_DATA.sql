--
-- Registrando endereços
--

INSERT INTO address (id, cep, city, complement, number, state, street) VALUES
	(1, '12345-677', 'São Paulo', NULL, '30B', 'São Paulo', 'Rua Lorem Ipsum'),
	(2, '12345-021', 'São Paulo', NULL, '30B', 'São Paulo', 'Rua Lorem Ipsum'),
	(3, '98745-430', 'São Paulo', NULL, '85A', 'São Paulo', 'Rua do Diretor'),
	(4, '78941-010', 'São Paulo', NULL, '120B', 'São Paulo', 'Rua Lorem 02'),
	(5, '78941-011', 'São Paulo', NULL, '120B', 'São Paulo', 'Rua Lorem 02'),
	(6, '78941-012', 'São Paulo', NULL, '120B', 'São Paulo', 'Rua Lorem 02'),
	(7, '78941-013', 'São Paulo', NULL, '120B', 'São Paulo', 'Rua Lorem 02');

--
-- Registrando escolas
--

INSERT INTO school (id, name, site, type, address_id) VALUES
	(1, 'ETEC de São Paulo', 'etecsaopaulo.com', 'ESTADUAL', 1),
	(2, 'ETEC de Guaianazes', 'etecguaianazes.com', 'ESTADUAL', 2);

--
-- Registrando disciplinas
--

INSERT INTO discipline (id, name) VALUES
	(1, 'Português 1A 1B'),
	(2, 'Português 1B'),
	(3, 'Português 1C');

--
-- Registrando períodos
--

INSERT INTO period (id, period, school_id) VALUES
	(1, 'MATUTINO', 1),
	(2, 'VESPERTINO', 1),
	(3, 'NOTURNO', 1);

--
-- Registrando turmas
--

INSERT INTO classroom (id, name, period_id) VALUES
	(1, '1A', 1),
	(2, '1B', 2),
	(3, '1C', 3);

--
-- Conectando turmas com disciplinas
--

INSERT INTO discipline_classroom (classroom_id, discipline_id) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(3, 3);

--
-- Registrando uma aula
--

INSERT INTO class (id, date, description, link, name, file_id, discipline_id) VALUES
	(1, '2020-11-21', 'Aula sobre português. 01', 'http://aula.com', 'Aula 01', NULL, 1),
	(2, '2020-11-21', 'Aula sobre português. 02', 'http://aula.com', 'Aula 02', NULL, 2),
	(3, '2020-11-21', 'Aula sobre português. 03', 'http://aula.com', 'Aula 03', NULL, 3);

--
-- Registrando atividades
--

INSERT INTO activity (id, description, final_date, initial_date, name, file_id, classroom_id) VALUES
	(1, 'Realizar frases', '2021-03-16 16:56:39.000000', '2020-11-21 04:20:03.000000', 'Atividade sobre português 01', NULL, 1),
	(2, 'Realizar frases', '2021-03-16 16:56:39.000000', '2020-11-21 04:20:13.000000', 'Atividade sobre português 02', NULL, 2),
	(3, 'Realizar frases', '2021-03-16 16:56:39.000000', '2020-11-21 04:20:21.000000', 'Atividade sobre português 03', NULL, 3);

--
-- Conectando aula com atividade
--

INSERT INTO activity_class (class_id, activity_id) VALUES
	(1, 1),
	(2, 2),
	(3, 3);

--
-- Registrando as permissões
--

INSERT INTO permission (id, description, name) VALUES
	(1, 'Visualizar dados das tabelas.', 'AD01'),
	(2, 'Editar dados nas tabelas. Exceto: credenciais dos usuários.', 'AD02'),
	(3, 'Criar dados nas tabelas.', 'AD03'),
	(4, 'Deletar dados nas tabelas', 'AD04'),
		
	(5, 'Visualizar seus dados.', 'US01'),
	(6, 'Editar seus dados. Exceto: CPF/CNPJ.', 'US02'),
	(7, 'Deletar seus dados.', 'US03'),
	
	(8, 'Entregar tarefas.', 'AL01'),
	(9, 'Visualizar atividades.', 'AL02'),
	
	(10, 'Criar atividades.', 'PR01'),
	(11, 'Visualizar atividades.', 'PR02'),
	(12, 'Corrigir atividades.', 'PR03'),
	(13, 'Criar aulas.', 'PR04'),
	
	(14, 'Realizar cadastros da escola. (Alunos, Professores, Períodos etc.)', 'DI01'),
	(15, 'Deletar dados da escola.', 'DI02'),
	(16, 'Editar dados da escola.', 'DI03'),
	(17, 'Visualizar dados da escola.', 'DI04');

--
-- Registrando os papeis
--

INSERT INTO role (id, name) VALUES
	(1, 'Administrador'),
	(2, 'Usuário'),
	(3, 'Aluno'),
	(4, 'Professor'),
	(5, 'Diretor');

--
-- Conectando os grupos as permissões
--

INSERT INTO permission_role (role_id, permission_id) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	
	(2, 5),
	(2, 6),
	(2, 7),
	
	(3, 8),
	(3, 9),
	
	(4, 10),
	(4, 11),
	(4, 12),
	(4, 13),
	
	(5, 14),
	(5, 15),
	(5, 16),
	(5, 17);
	
--
-- Registrando pessoas
--

INSERT INTO person (id, birth_date, cpf, email, name, password, address_id) VALUES
	(1, '2002-10-19', '74664611072', 'director01@director.com', 'Director 01', '$2a$10$4rrDtoL3ZSFVMsksw57iNO0o7UHtVln7a8Zq9l2u8iFBm.VExBt.q', 3),
	(2, '2002-10-19', '86052268034', 'teacher01@teacher.com', 'Teacher 1', '$2a$10$rkoF5oqgZQ/xzoYJS0vQHeqPtAb99eYY/Po.koRl15isCENJcNKYW', 4),
	(3, '2002-10-19', '82395306053', 'teacher02@teacher.com', 'Teacher 2', '$2a$10$UQXIJpMBHE7zMWz78hIii.Bmlokohjd3DCx3RJJgg564sXQL5Z0aa', 5),
	(4, '2002-10-19', '34573383026', 'teacher03@teacher.com', 'Teacher 3', '$2a$10$VOmNopgyThxcLkRqf2qjNeYkcEcDQAxIyWq1KDb/ioOGn6Mu9zS5O', 6),
	(5, '2002-10-19', '22454199017', 'teacher04@teacher.com', 'Teacher 4', '$2a$10$Sxo3/H8MVMwCyVXyqJdhXO6hoIgy0lzRjant6fPkAUs2iLFwgJ0OO', 7),
	(6, '2002-10-19', '99335413070', 'student01@student.com', 'Estudante 01', '$2a$10$ABpkBdSLlKxZnENXOO9J7OWx67dg98gwDN66vpT.a4Wf3O0v4aK9e', NULL),
	(7, '2002-10-19', '45968994010', 'student02@student.com', 'Estudante 02', '$2a$10$ljRP2yP1HYT8V6mbS08c1.bYWOglilaHvJu6VXj6RsPyu8rnrlgz.', NULL),
	(8, '2002-10-19', '48926000087', 'student03@student.com', 'Estudante 03', '$2a$10$3TiBym8POe2XA/AND731j.gVy7jN1.92Dn2jr0uojb.CflfTD4eZG', NULL),
	(9, '2002-10-19', '47667961018', 'admin01@admin.com', 'Administrador 01', '$2a$10$3TiBym8POe2XA/AND731j.gVy7jN1.92Dn2jr0uojb.CflfTD4eZG', NULL);

--
-- Conectando pessoa com papel
--

INSERT INTO person_role (person_id, role_id) VALUES
	(1, 2),
	(1, 5),
	(2, 2),
	(2, 4),
	(3, 2),
	(3, 4),
	(4, 2),
	(4, 4),
	(5, 2),
	(5, 4),
	(6, 2),
	(6, 3),
	(7, 2),
	(7, 3),
	(8, 2),
	(8, 3),
	(9, 1),
	(9, 2);

--
-- Registrando telefones
--

INSERT INTO telephone (id, number, type, person_id) VALUES
	(1, '11956691230', 'FIXO', 1),
	(2, '11123456781', 'FIXO', 2),
	(3, '11123456782', 'FIXO', 3),
	(4, '11123456783', 'FIXO', 4),
	(5, '11123456784', 'FIXO', 5);
	
--
-- Registrando estudantes
--

INSERT INTO student (person_id, classroom_id) VALUES
	(6, 1),
	(7, 2),
	(8, 3);

--
-- Registrando professores
--

INSERT INTO teacher (person_id) VALUES
	(2),
	(3),
	(4),
	(5);

--
-- Conectando disciplina com professor
--

INSERT INTO discipline_teacher (discipline_id, teacher_id) VALUES
	(1, 2),
	(2, 2),
	(2, 3),
	(3, 4),
	(3, 5);

--
-- Registrando diretor
--

INSERT INTO director (person_id, school_id) VALUES
	(1, 1);

--
-- Registrando Administrador
--

INSERT INTO administrator (person_id) VALUES
	(9);

--
-- Registrando respostas
--

INSERT INTO answer (id, date_time, description, activity_id, file_id, student_id) VALUES
	(1, '2020-11-21 04:20:54.000000', 'Resposta da atividade 01', 1, NULL, 6),
	(2, '2020-11-21 04:21:09.000000', 'Resposta da atividade 02', 2, NULL, 7),
	(3, '2020-11-21 04:21:16.000000', 'Resposta da atividade 03', 3, NULL, 8);

--
-- Registrando feedback
--

INSERT INTO feedback (id, date_time, feedback, grade, answer_id, file_id, teacher_id) VALUES
(1, '2020-11-21 04:21:43.000000', 'Foi bem mais ou menos', 'R', 1, NULL, 2),
(2, '2020-11-21 04:22:09.000000', 'Foi bem mais ou menos', 'R', 2, NULL, 3),
(3, '2020-11-21 04:22:18.000000', 'Foi bem mais ou menos', 'R', 3, NULL, 5);
