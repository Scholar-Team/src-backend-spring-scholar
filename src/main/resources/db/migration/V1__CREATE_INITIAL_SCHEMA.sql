-- Host: 127.0.0.1
-- Tempo de geração: 01-Dez-2020 às 18:32
-- Versão do servidor: 10.4.13-MariaDB

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: scholar
--

-- --------------------------------------------------------

--
-- Estrutura da tabela activity
--

CREATE TABLE activity (
  id bigint(20) NOT NULL,
  description text DEFAULT NULL,
  final_date datetime(6) NOT NULL,
  initial_date datetime(6) NOT NULL,
  name varchar(255) NOT NULL,
  file_id bigint(20) DEFAULT NULL,
  classroom_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela activity_class
--

CREATE TABLE activity_class (
  activity_id bigint(20) NOT NULL,
  class_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela address
--

CREATE TABLE address (
  id bigint(20) NOT NULL,
  cep varchar(255) NOT NULL,
  city varchar(255) NOT NULL,
  complement varchar(255) DEFAULT NULL,
  number varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  street varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela administrator
--

CREATE TABLE administrator (
  person_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela answer
--

CREATE TABLE answer (
  id bigint(20) NOT NULL,
  date_time datetime(6) NOT NULL,
  description text DEFAULT NULL,
  activity_id bigint(20) NOT NULL,
  file_id bigint(20) DEFAULT NULL,
  student_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela class
--

CREATE TABLE class (
  id bigint(20) NOT NULL,
  date datetime(6) NOT NULL,
  description text NOT NULL,
  link varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  file_id bigint(20) DEFAULT NULL,
  discipline_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela classroom
--

CREATE TABLE classroom (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  file_id bigint(20) DEFAULT NULL,
  period_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela director
--

CREATE TABLE director (
  person_id bigint(20) NOT NULL,
  school_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela discipline
--

CREATE TABLE discipline (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  file_id bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela discipline_classroom
--

CREATE TABLE discipline_classroom (
  discipline_id bigint(20) NOT NULL,
  classroom_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela discipline_teacher
--

CREATE TABLE discipline_teacher (
  discipline_id bigint(20) NOT NULL,
  teacher_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela feedback
--

CREATE TABLE feedback (
  id bigint(20) NOT NULL,
  date_time datetime(6) NOT NULL,
  feedback text NOT NULL,
  grade varchar(255) NOT NULL,
  answer_id bigint(20) NOT NULL,
  file_id bigint(20) DEFAULT NULL,
  teacher_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela file
--

CREATE TABLE file (
  id bigint(20) NOT NULL,
  content_type varchar(255) DEFAULT NULL,
  full_name varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  size bigint(20) DEFAULT NULL,
  url varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela period
--

CREATE TABLE period (
  id bigint(20) NOT NULL,
  period varchar(255) NOT NULL,
  school_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela permission
--

CREATE TABLE permission (
  id bigint(20) NOT NULL,
  description varchar(255) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela permission_role
--

CREATE TABLE permission_role (
  permission_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela person
--

CREATE TABLE person (
  id bigint(20) NOT NULL,
  birth_date date NOT NULL,
  cpf varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  address_id bigint(20) DEFAULT NULL,
  file_id bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela person_role
--

CREATE TABLE person_role (
  person_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela role
--

CREATE TABLE role (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela school
--

CREATE TABLE school (
  id bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  site varchar(255) DEFAULT NULL,
  type varchar(255) NOT NULL,
  address_id bigint(20) DEFAULT NULL,
  file_id bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela student
--

CREATE TABLE student (
  person_id bigint(20) NOT NULL,
  classroom_id bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela teacher
--

CREATE TABLE teacher (
  person_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela telephone
--

CREATE TABLE telephone (
  id bigint(20) NOT NULL,
  number varchar(255) NOT NULL,
  type varchar(255) NOT NULL,
  person_id bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela activity
--
ALTER TABLE activity
  ADD PRIMARY KEY (id),
  ADD KEY FK6tyrd97nb8smrbsx3ncrkb96f (file_id),
  ADD KEY FK4c8hjgtep7baq11385e03dicj (classroom_id);

--
-- Índices para tabela activity_class
--
ALTER TABLE activity_class
  ADD PRIMARY KEY (activity_id,class_id),
  ADD KEY FK5t7oqmkibrv5mg1yrqqea9kks (class_id);

--
-- Índices para tabela address
--
ALTER TABLE address
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_7v30nb1hyt3kfh8dch430kgoh (cep);

--
-- Índices para tabela administrator
--
ALTER TABLE administrator
  ADD PRIMARY KEY (person_id);

--
-- Índices para tabela answer
--
ALTER TABLE answer
  ADD PRIMARY KEY (id),
  ADD KEY FKg1i73v3xm3oa8clfw37wlgebg (activity_id),
  ADD KEY FK255ucdmwq4wb1ns8n9mocwpum (file_id),
  ADD KEY FKfq3yte5284do0v7cw2bjsg95o (student_id);

--
-- Índices para tabela class
--
ALTER TABLE class
  ADD PRIMARY KEY (id),
  ADD KEY FK3f715h6nt14m3375mknkeold1 (file_id),
  ADD KEY FKf3mhq9e52k3arunidufkkjfyl (discipline_id);

--
-- Índices para tabela classroom
--
ALTER TABLE classroom
  ADD PRIMARY KEY (id),
  ADD KEY FKet23omw4xfaxxiobnnddhotho (file_id),
  ADD KEY FK5b4ttmr2n3kdlbyinpnfkf52q (period_id);

--
-- Índices para tabela director
--
ALTER TABLE director
  ADD PRIMARY KEY (person_id),
  ADD KEY FKcko9qf90ufewkthjhm1clad25 (school_id);

--
-- Índices para tabela discipline
--
ALTER TABLE discipline
  ADD PRIMARY KEY (id),
  ADD KEY FKfei0k9ta1pnh3aa40s6uk57bd (file_id);

--
-- Índices para tabela discipline_classroom
--
ALTER TABLE discipline_classroom
  ADD PRIMARY KEY (discipline_id,classroom_id),
  ADD KEY FK3r78xuy8tc56gfr20ilmkg81v (classroom_id);

--
-- Índices para tabela discipline_teacher
--
ALTER TABLE discipline_teacher
  ADD PRIMARY KEY (discipline_id,teacher_id),
  ADD KEY FKtrixjbwd92yr7dd5pdhl82bak (teacher_id);

--
-- Índices para tabela feedback
--
ALTER TABLE feedback
  ADD PRIMARY KEY (id),
  ADD KEY FK34t0rxbpyi0xkd4u200o0f5xs (answer_id),
  ADD KEY FK2o9sei0c0yfy19ouwk1xcpcpy (file_id),
  ADD KEY FK8x9xk5bvdd47pn4hb36j7qtsh (teacher_id);

--
-- Índices para tabela file
--
ALTER TABLE file
  ADD PRIMARY KEY (id);

--
-- Índices para tabela period
--
ALTER TABLE period
  ADD PRIMARY KEY (id),
  ADD KEY FK97qls3bc7g0tdkv0mbgm7a08x (school_id);

--
-- Índices para tabela permission
--
ALTER TABLE permission
  ADD PRIMARY KEY (id);

--
-- Índices para tabela permission_role
--
ALTER TABLE permission_role
  ADD PRIMARY KEY (permission_id,role_id),
  ADD KEY FK50sfdcvbvdaclpn7wp4uop4ml (role_id);

--
-- Índices para tabela person
--
ALTER TABLE person
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_ovihp2p82c97wbe60mv11txrj (cpf),
  ADD UNIQUE KEY UK_fwmwi44u55bo4rvwsv0cln012 (email),
  ADD KEY FKk7rgn6djxsv2j2bv1mvuxd4m9 (address_id),
  ADD KEY FKeid7tktqj3rv9e5wk2isfgdis (file_id);

--
-- Índices para tabela person_role
--
ALTER TABLE person_role
  ADD PRIMARY KEY (person_id,role_id),
  ADD KEY FKs7asxi8amiwjjq1sonlc4rihn (role_id);

--
-- Índices para tabela role
--
ALTER TABLE role
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_8sewwnpamngi6b1dwaa88askk (name);

--
-- Índices para tabela school
--
ALTER TABLE school
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_5rmrqdw1eyq8i02v9ifi1u6w4 (site),
  ADD KEY FKje7ysjh9qami0jc33ykm13g1d (address_id),
  ADD KEY FKt959k7c7akua6lstimea7fwxc (file_id);

--
-- Índices para tabela student
--
ALTER TABLE student
  ADD PRIMARY KEY (person_id),
  ADD KEY FK1rs4md9whkjqy20v181d18kfy (classroom_id);

--
-- Índices para tabela teacher
--
ALTER TABLE teacher
  ADD PRIMARY KEY (person_id);

--
-- Índices para tabela telephone
--
ALTER TABLE telephone
  ADD PRIMARY KEY (id),
  ADD KEY FKstw3jak6h05dfenl7jkki0dvb (person_id);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela activity
--
ALTER TABLE activity
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela address
--
ALTER TABLE address
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela answer
--
ALTER TABLE answer
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela class
--
ALTER TABLE class
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela classroom
--
ALTER TABLE classroom
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela discipline
--
ALTER TABLE discipline
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela feedback
--
ALTER TABLE feedback
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela file
--
ALTER TABLE file
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela period
--
ALTER TABLE period
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela permission
--
ALTER TABLE permission
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela person
--
ALTER TABLE person
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela role
--
ALTER TABLE role
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela school
--
ALTER TABLE school
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela telephone
--
ALTER TABLE telephone
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela activity
--
ALTER TABLE activity
  ADD CONSTRAINT FK4c8hjgtep7baq11385e03dicj FOREIGN KEY (classroom_id) REFERENCES classroom (id),
  ADD CONSTRAINT FK6tyrd97nb8smrbsx3ncrkb96f FOREIGN KEY (file_id) REFERENCES file (id);

--
-- Limitadores para a tabela activity_class
--
ALTER TABLE activity_class
  ADD CONSTRAINT FK5t7oqmkibrv5mg1yrqqea9kks FOREIGN KEY (class_id) REFERENCES class (id),
  ADD CONSTRAINT FKif8el9gbwuqvlygedmlpp3ne9 FOREIGN KEY (activity_id) REFERENCES activity (id);

--
-- Limitadores para a tabela administrator
--
ALTER TABLE administrator
  ADD CONSTRAINT FK1lapeuo6sq36uluybgd2c6rxn FOREIGN KEY (person_id) REFERENCES person (id);

--
-- Limitadores para a tabela answer
--
ALTER TABLE answer
  ADD CONSTRAINT FK255ucdmwq4wb1ns8n9mocwpum FOREIGN KEY (file_id) REFERENCES file (id),
  ADD CONSTRAINT FKfq3yte5284do0v7cw2bjsg95o FOREIGN KEY (student_id) REFERENCES student (person_id),
  ADD CONSTRAINT FKg1i73v3xm3oa8clfw37wlgebg FOREIGN KEY (activity_id) REFERENCES activity (id);

--
-- Limitadores para a tabela class
--
ALTER TABLE class
  ADD CONSTRAINT FK3f715h6nt14m3375mknkeold1 FOREIGN KEY (file_id) REFERENCES file (id),
  ADD CONSTRAINT FKf3mhq9e52k3arunidufkkjfyl FOREIGN KEY (discipline_id) REFERENCES discipline (id);

--
-- Limitadores para a tabela classroom
--
ALTER TABLE classroom
  ADD CONSTRAINT FK5b4ttmr2n3kdlbyinpnfkf52q FOREIGN KEY (period_id) REFERENCES period (id),
  ADD CONSTRAINT FKet23omw4xfaxxiobnnddhotho FOREIGN KEY (file_id) REFERENCES file (id);

--
-- Limitadores para a tabela director
--
ALTER TABLE director
  ADD CONSTRAINT FK8eksly1t3rr6qjv2iknk3k35m FOREIGN KEY (person_id) REFERENCES person (id),
  ADD CONSTRAINT FKcko9qf90ufewkthjhm1clad25 FOREIGN KEY (school_id) REFERENCES school (id);

--
-- Limitadores para a tabela discipline
--
ALTER TABLE discipline
  ADD CONSTRAINT FKfei0k9ta1pnh3aa40s6uk57bd FOREIGN KEY (file_id) REFERENCES file (id);

--
-- Limitadores para a tabela discipline_classroom
--
ALTER TABLE discipline_classroom
  ADD CONSTRAINT FK3r78xuy8tc56gfr20ilmkg81v FOREIGN KEY (classroom_id) REFERENCES classroom (id),
  ADD CONSTRAINT FKhe0mbltmrdtf6htmbq3aou5ie FOREIGN KEY (discipline_id) REFERENCES discipline (id);

--
-- Limitadores para a tabela discipline_teacher
--
ALTER TABLE discipline_teacher
  ADD CONSTRAINT FKfuvluf3v0p9mivj82m8pkvpuo FOREIGN KEY (discipline_id) REFERENCES discipline (id),
  ADD CONSTRAINT FKtrixjbwd92yr7dd5pdhl82bak FOREIGN KEY (teacher_id) REFERENCES teacher (person_id);

--
-- Limitadores para a tabela feedback
--
ALTER TABLE feedback
  ADD CONSTRAINT FK2o9sei0c0yfy19ouwk1xcpcpy FOREIGN KEY (file_id) REFERENCES file (id),
  ADD CONSTRAINT FK34t0rxbpyi0xkd4u200o0f5xs FOREIGN KEY (answer_id) REFERENCES answer (id),
  ADD CONSTRAINT FK8x9xk5bvdd47pn4hb36j7qtsh FOREIGN KEY (teacher_id) REFERENCES teacher (person_id);

--
-- Limitadores para a tabela period
--
ALTER TABLE period
  ADD CONSTRAINT FK97qls3bc7g0tdkv0mbgm7a08x FOREIGN KEY (school_id) REFERENCES school (id);

--
-- Limitadores para a tabela permission_role
--
ALTER TABLE permission_role
  ADD CONSTRAINT FK3tuvkbyi6wcytyg21hvpd6txw FOREIGN KEY (permission_id) REFERENCES permission (id),
  ADD CONSTRAINT FK50sfdcvbvdaclpn7wp4uop4ml FOREIGN KEY (role_id) REFERENCES role (id);

--
-- Limitadores para a tabela person
--
ALTER TABLE person
  ADD CONSTRAINT FKeid7tktqj3rv9e5wk2isfgdis FOREIGN KEY (file_id) REFERENCES file (id),
  ADD CONSTRAINT FKk7rgn6djxsv2j2bv1mvuxd4m9 FOREIGN KEY (address_id) REFERENCES address (id);

--
-- Limitadores para a tabela person_role
--
ALTER TABLE person_role
  ADD CONSTRAINT FKhyx1efsls0f00lxs6xd4w2b3j FOREIGN KEY (person_id) REFERENCES person (id),
  ADD CONSTRAINT FKs7asxi8amiwjjq1sonlc4rihn FOREIGN KEY (role_id) REFERENCES role (id);

--
-- Limitadores para a tabela school
--
ALTER TABLE school
  ADD CONSTRAINT FKje7ysjh9qami0jc33ykm13g1d FOREIGN KEY (address_id) REFERENCES address (id),
  ADD CONSTRAINT FKt959k7c7akua6lstimea7fwxc FOREIGN KEY (file_id) REFERENCES file (id);

--
-- Limitadores para a tabela student
--
ALTER TABLE student
  ADD CONSTRAINT FK1rs4md9whkjqy20v181d18kfy FOREIGN KEY (classroom_id) REFERENCES classroom (id),
  ADD CONSTRAINT FKnwsufvlvlnsxqv60ltj06bbfx FOREIGN KEY (person_id) REFERENCES person (id);

--
-- Limitadores para a tabela teacher
--
ALTER TABLE teacher
  ADD CONSTRAINT FK4pe9ttn7r2fxcvae0iibusuoq FOREIGN KEY (person_id) REFERENCES person (id);

--
-- Limitadores para a tabela telephone
--
ALTER TABLE telephone
  ADD CONSTRAINT FKstw3jak6h05dfenl7jkki0dvb FOREIGN KEY (person_id) REFERENCES person (id);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
