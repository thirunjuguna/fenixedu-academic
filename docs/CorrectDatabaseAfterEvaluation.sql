-- ALTERAR O NOME DA TABELA EVALUATION PARA EVALUATION_METHOD
ALTER TABLE EVALUATION RENAME EVALUATION_METHOD;

-- ALTERAR O NOME DA TABELA EXAM PARA EVALUATION
ALTER TABLE EXAM RENAME EVALUATION;

-- ADICIONAR O CAMPO CLASS-NAME NA TABELA EVALUATION
ALTER TABLE EVALUATION ADD CLASS_NAME varchar(250) NOT NULL AFTER ID_INTERNAL;

-- --------------------------------------------------------------
-- ALTERAR O NOME DO CAMPO CHAVE DE EXAM PARA CHAVE DE EVALUATION
-- --------------------------------------------------------------
ALTER TABLE EXAM_EXECUTION_COURSE CHANGE KEY_EXAM KEY_EVALUATION int(11) not null;
ALTER TABLE EXAM_ROOM CHANGE KEY_EXAM KEY_EVALUATION int(11) not null;
ALTER TABLE EXAM_STUDENT CHANGE KEY_EXAM KEY_EVALUATION int(11) not null;
ALTER TABLE EXAM_STUDENT_ROOM CHANGE KEY_EXAM KEY_EVALUATION int(11) not null;
ALTER TABLE MARK CHANGE KEY_EXAM KEY_EVALUATION int(11) not null;

-- --------------------------------------------------------------
-- ALTERAR OS RECORDS DA TABELA EVALUATION
-- --------------------------------------------------------------
UPDATE EVALUATION SET CLASS_NAME='Dominio.Exam';

-- --------------------------------------------------------------
-- ALTER TABLE CREDITS
-- --------------------------------------------------------------
ALTER TABLE CREDITS ADD ADDITIONAL_CREDITS float;
ALTER TABLE CREDITS ADD ADDITIONAL_CREDITS_JUSTIFICATION varchar (250);
UPDATE CREDITS set ADDITIONAL_CREDITS = NULL , ADDITIONAL_CREDITS_JUSTIFICATION = NULL;

-- ROLE CREDITS MANAGER
insert into ROLE values (13, 13, "/credits","index.do", "portal.credits");
insert into ROLE values (14, 14, "/credits","index.do", "portal.credits.department");

----------------------------
-- Table structure for CREDITS_MANAGER_DEPARTMENT
--  This table tells what departments that a person can manage the teacher credits.
----------------------------
drop table if exists CREDITS_MANAGER_DEPARTMENT;
create table CREDITS_MANAGER_DEPARTMENT (
   ID_INTERNAL int(11) not null auto_increment,
   KEY_PERSON int(11) not null,
   KEY_DEPARTMENT int(11) not null,
   primary key (ID_INTERNAL),
   unique U1 (KEY_PERSON, KEY_DEPARTMENT))
   type=InnoDB;

insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (1, 'DEP. ENG. ELECT. E COMPUTADORES', '21') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (2, 'DEPARTAMENTO DE ENGENHARIA QUIMICA', '23') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (3, 'DEPARTAMENTO DE ENGENHARIA CIVIL', '20') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (4, 'DEP. DE ENGENHARIA MECANICA', '22') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (5, 'DEPARTAMENTO DE FISICA', '24') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (6, 'DEPARTAMENTO DE ENGENHARIA MINAS', '26') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (7, 'DEPARTAMENTO DE MATEMATICA', '25') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (8, 'DEPART. DE ENGENHARIA DE MATERIAIS', '27') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (9, 'DEPARTAMENTO DE ENG. INFORMATICA', '28') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (10, 'SEC. AUTONOMA DE ECONOMIA E GESTAO', '3001') ;
insert  into DEPARTMENT ( ID_INTERNAL,NAME,CODE) values (11, 'SEC. AUTONOMA DE ENGENHARIA NAVAL', '3002') ;

   