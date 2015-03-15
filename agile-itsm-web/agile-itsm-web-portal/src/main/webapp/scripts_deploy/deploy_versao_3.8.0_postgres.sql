-- INICIO - CARLOS ALBERTO DOS SANTOS 23/07/2014

alter table rest_operation add generatelog char(1);
update rest_operation set generatelog = 'Y';
-- FIM

-- INICIO - EULER JOSE RAMOS 19/08/2014

CREATE TABLE grupoassinatura (
  idgrupoassinatura integer NOT NULL ,
  titulo VARCHAR(254) NOT NULL ,
  datainicio DATE NOT NULL ,
  datafim DATE NULL ,
  PRIMARY KEY (idgrupoassinatura));

CREATE TABLE assinatura (
  idassinatura integer NOT NULL ,
  idempregado integer NULL ,
  papel VARCHAR(254) NULL ,
  fase VARCHAR(254) NULL ,
  datainicio DATE NOT NULL ,
  datafim DATE NULL ,
  PRIMARY KEY (idassinatura));

CREATE  TABLE itemgrupoassinatura (
  iditemgrupoassinatura integer NOT NULL ,
  idgrupoassinatura integer NOT NULL ,
  idassinatura integer NOT NULL ,
  ordem integer NOT NULL ,
  datainicio DATE NOT NULL ,
  datafim DATE NULL ,
  PRIMARY KEY (iditemgrupoassinatura));
  
alter table os add idgrupoassinatura integer;

-- FIM
-- Inicio MARIO HAYASAKI JUNIOR 28/08/2014
CREATE TABLE imagemitemconfiguracaorelacao (
  idimagemitemconfiguracaorel integer NOT NULL,
  idimagemitemconfiguracao integer NOT NULL,
  idImagemItemConfiguracaoPai integer NOT NULL,
  PRIMARY KEY (idimagemitemconfiguracaorel));
 --FIM
