-- Início Mário 05/09/2013

CREATE TABLE gruporequisicaomudanca (
  idgruporequisicaomudanca int NOT NULL,
  idgrupo int NOT NULL,
  idrequisicaomudanca int NOT NULL,
  PRIMARY KEY (idgruporequisicaomudanca),
  foreign key (idgrupo) references grupo (idgrupo),
  foreign key (idrequisicaomudanca) references requisicaomudanca (idrequisicaomudanca)
);

ALTER TABLE gruporequisicaomudanca ADD COLUMN nomegrupo VARCHAR(250);
ALTER TABLE gruporequisicaomudanca ADD COLUMN datafim date default null;

CREATE TABLE ligacao_mud_his_gru
(
  idligacao_mud_his_gru int4 NOT NULL,
  idgruporequisicaomudanca int4,
  idrequisicaomudanca int4,
  idhistoricomudanca int4,
  constraint pk_ligacao_mud_his_gru primary key (idligacao_mud_his_gru)
);

-- Fim Mário

-- Início Murilo Gabriel 07/09/2013

alter table historicoic add column idcontrato int null;
alter table historicoic add column idliberacao int null;
alter table historicoic add column idresponsavel int null;
alter table historicoic add column ativofixo varchar(255) null;

-- Fim Murilo Gabriel

-- Início Ronnie Mikihiro 10/09/2013

CREATE TABLE softwareslistanegra (
    idsoftwareslistanegra int NOT NULL,
    nomesoftwareslistanegra varchar(100) NOT NULL
);
ALTER TABLE softwareslistanegra ADD CONSTRAINT pk_softwareslistanegra PRIMARY KEY(idsoftwareslistanegra);
CREATE INDEX fk_idx_softwareslistanegra ON softwareslistanegra(idsoftwareslistanegra);


CREATE TABLE softwareslistanegraencontrados (
    idsoftwareslistanegraencontrad int NOT NULL,
    iditemconfiguracao int NOT NULL,
    idsoftwareslistanegra int NOT NULL,
    softwarelistanegraencontrado varchar(200) NOT NULL,
    data timestamp NOT NULL
);
ALTER TABLE softwareslistanegraencontrados ADD CONSTRAINT pk_softwareslistanegraencontrados PRIMARY KEY(idsoftwareslistanegraencontrad);
ALTER TABLE softwareslistanegraencontrados ADD CONSTRAINT fk_softwareslistanegraencontrados_itemconfiguracao FOREIGN KEY(iditemconfiguracao) REFERENCES itemconfiguracao(iditemconfiguracao);
ALTER TABLE softwareslistanegraencontrados ADD CONSTRAINT fk_softwareslistanegraencontrados_softwareslistanegra FOREIGN KEY (idsoftwareslistanegra) REFERENCES softwareslistanegra(idsoftwareslistanegra);
CREATE INDEX fk_idx_softwareslistanegraencontrados ON softwareslistanegraencontrados(idsoftwareslistanegraencontrad);

-- Fim Ronnie Mikihiro 10/09/2013

-- Início Bruno 10/09/2013

alter table solicitacaoservico add column vencendo varchar(1) NULL;
alter table solicitacaoservico add column criouproblemaautomatico varchar(1) NULL;

-- Fim Bruno

-- Incio Euler 10/09/2013

CREATE  TABLE regraescalonamento (
  idregraescalonamento integer NOT NULL ,
  idtipogerenciamento integer NOT NULL ,
  idservico integer NULL ,
  idcontrato integer NULL ,
  idsolicitante integer NULL ,
  idgrupo integer NULL ,
  idtipodemandaservico integer NULL ,
  urgencia VARCHAR(1) NULL ,
  impacto VARCHAR(1) NULL ,
  tempoexecucao integer NOT NULL ,
  intervalonotificacao integer NOT NULL ,
  enviaremail VARCHAR(1),
  criaproblema VARCHAR(1),
  prazocriarproblema integer NULL ,
  datainicio date null,
  datafim date null,
  PRIMARY KEY (idregraescalonamento) ,
  foreign key (idtipodemandaservico) references tipodemandaservico (idtipodemandaservico),
  foreign key (idservico) references servico (idservico),
  foreign key (idcontrato) references contratos (idcontrato),
  foreign key (idgrupo) references grupo (idgrupo)
);

CREATE  TABLE escalonamento (
  idescalonamento integer NOT NULL ,
  idregraescalonamento integer NOT NULL ,
  idgrupoexecutor integer NOT NULL ,
  prazoexecucao integer NOT NULL ,
  datainicio date null,
  datafim date null,
  PRIMARY KEY (idescalonamento) ,
  foreign key (idgrupoexecutor) references grupo (idgrupo),
  foreign key (idregraescalonamento) references regraescalonamento (idregraescalonamento)
);

alter table regraescalonamento add tipodataescalonamento integer;
update regraescalonamento set tipodataescalonamento = 1 where tipodataescalonamento is null;
alter table regraescalonamento alter tipodataescalonamento set not null;
alter table escalonamento add idprioridade integer null;

-- Fim Euler

-- Incio Emauri 11/09/2013

ALTER TABLE netmap ADD column hardware VARCHAR(255);
ALTER TABLE netmap ADD column sistemaoper VARCHAR(255);
ALTER TABLE netmap ADD column uptime VARCHAR(20);

-- Fim Emauri 11/09/2013

-- Inicio Cleison 12/09/2013

ALTER TABLE itemrequisicaoproduto ALTER COLUMN situacao TYPE VARCHAR(50);

-- Fim Cleison

-- Inicio Mário Haysaki J�nior 12/09/2013

ALTER TABLE projetos ADD COLUMN deleted varchar(1) DEFAULT NULL;

-- Fim Mário Haysaki J�nior

-- Inicio Bruno Franco 12/09/2013

CREATE TABLE relEscalonamentoSolServico
(
   idsolicitacaoservico bigint,
   idescalonamento integer,
   CONSTRAINT pk_escalonamento_solicitacao PRIMARY KEY (idsolicitacaoservico, idescalonamento),
   CONSTRAINT fk_solicitacaoservico FOREIGN KEY (idsolicitacaoservico) REFERENCES solicitacaoservico (idsolicitacaoservico) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT fk_escalonamento FOREIGN KEY (idescalonamento) REFERENCES escalonamento (idescalonamento) ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Fim Bruno Franco

-- Inicio Maycon Fernandes 12/09/2013

CREATE TABLE auditoriaitemconfig
(
  idauditoriaitemConfig int NOT NULL,
  iditemconfiguracao int,
  iditemconfiguracaopai int,
  idhistoricoic int,
  idhistoricovalor int,
  idvalor int,
  idusuario int,
  datahoraalteracao timestamp,
  tipoalteracao VARCHAR(20),
  primary key (idauditoriaitemconfig)
);

alter table itemconfiguracao ADD COLUMN datahoradesinstalacao TIMESTAMP;

-- Fim Maycon

-- Inicio - Bruno Franco 13/09/2013

alter table requisicaomudanca add column vencendo varchar(1) NULL;

insert into modelosemails (idmodeloemail, titulo, texto, situacao, identificador) values ($id_modeloemail_escalonamento,'Prazo para resolução terminando - ${IDSOLICITACAOSERVICO}','Senhor(a) ${NOMECONTATO},<br /><br />Informamos que o prazo para resolu&ccedil;&atilde;o da solicita&ccedil;&atilde;o&nbsp;${IDSOLICITACAOSERVICO} &nbsp;est&aacute; terminando.<br /><br /><strong>N&uacute;mero:</strong> ${IDSOLICITACAOSERVICO}<br /><strong>Tipo:</strong> ${DEMANDA}<br /><strong>Servi&ccedil;o:</strong> ${SERVICO}<br /><br /><strong>Descri&ccedil;&atilde;o:</strong> <br />${DESCRICAO}<br /><br />Atenciosamente,<br /><br />Central IT&nbsp;Tecnologia da Informa&ccedil;&atilde;o Ltda<br />','A','PrazoSolicitacao');

-- Fim - Bruno Franco

-- Inicio - Ronnie Mikihiro Sato Lopes 13/09/2013

INSERT INTO modelosemails
(idmodeloemail,
titulo,
texto,
situacao,
identificador)
VALUES
($id_listanegraemail,
'Softwares Irregulares Encontrados Perante Lista Negra',
'Senhor(a) ${NOMECONTATO},<br /><br />Informamos que foram encontrados softwares irregulares instalados perante a Lista Negra de Softwares, como segue a tabela abaixo:<strong><br /><br /></strong><div style="text-align: left;"><strong>TABELA DE SOFTWARES IRREGULARES PERANTE LISTA NEGRA<br /><br /></strong>${TABELALISTANEGRA}</div><div style="text-align: center;">&nbsp;</div><br /><br />Atenciosamente,<br /><font face="Calibri"><font size="1"><b><span style="COLOR: #31849b; FONT-SIZE: 14pt">Central</span></b><b><span style="FONT-SIZE: 14pt">IT</span></b></font></font>',
'A',
'softwaresListaNegra');

-- Fim - Ronnie Mikihiro Sato Lopes

-- Incio Maycon 13/09/2013

CREATE TABLE auditoriaitemconfig
(
  idauditoriaitemConfig int NOT NULL,
  iditemconfiguracao int,
  iditemconfiguracaopai int,
  idhistoricoic int,
  idhistoricovalor int,
  idvalor int,
  idusuario int,
  datahoraalteracao timestamp,
  tipoalteracao VARCHAR(20),
  primary key (idauditoriaitemconfig)
);

alter table itemconfiguracao ADD COLUMN datahoradesinstalacao TIMESTAMP;

-- Fim Maycon

-- Inicio - Bruno Franco 13/09/2013

CREATE TABLE relEscalonamentomudanca
(
   idrequisicaomudanca bigint,
   idescalonamento integer,
   CONSTRAINT pk_escalonamento_mudanca PRIMARY KEY (idrequisicaomudanca, idescalonamento),
   CONSTRAINT fk_requisicaomudanca FOREIGN KEY (idrequisicaomudanca) REFERENCES requisicaomudanca(idrequisicaomudanca) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT fk_escalonamento FOREIGN KEY (idescalonamento) REFERENCES escalonamento (idescalonamento) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE relEscalonamentoproblema
(
   idproblema bigint,
   idescalonamento integer,
   CONSTRAINT pk_escalonamento_problema PRIMARY KEY (idproblema, idescalonamento),
   CONSTRAINT fk_problema FOREIGN KEY (idproblema) REFERENCES problema (idproblema) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT fk_escalonamento FOREIGN KEY (idescalonamento) REFERENCES escalonamento (idescalonamento) ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Fim - Bruno Franco

-- Inicio - Bruno Franco 16/09/2013

alter table problema add column vencendo varchar(1) NULL;

-- Fim - Bruno Franco

-- Inicio - Bruno Franco 18/09/2013

ALTER TABLE regraescalonamento ALTER COLUMN tempoexecucao DROP NOT NULL;

-- Fim - Bruno Franco

-- Inicio - Uelen Paulo - 23/09/2013

create index idx_datahorafinalizacao on bpm_itemtrabalhofluxo(datahorafinalizacao);

-- Fim - Uelen Paulo

-- Inicio - Bruno Franco 24/09/2013

ALTER TABLE problema ALTER COLUMN descricao TYPE text;

-- Fim - Bruno Franco

-- Inicio Maycon 25/09/2013

ALTER TABLE solicitacaoservico ADD COLUMN idusuarioresponsavelatual INT;

-- Fim Maycon

-- Inicio - Bruno Franco 04/10/2013

ALTER TABLE baseconhecimento ADD COLUMN  idsolicitacaoservico int null;

-- Fim Bruno Franco

-- Inicio - Maycon 09/10/2013

ALTER TABLE parametrocorpore add column tipodado character varying(50);

-- Fim Maycon

-- Inicio - Rodrigo Pecci Acorse 17/10/2013

CREATE INDEX empregados_idempregado_idx ON empregados USING btree (idempregado);
CREATE INDEX gruposempregados_idgrupo_idx ON gruposempregados USING btree (idgrupo);
CREATE INDEX contratosgrupos_idgrupo_idx ON contratosgrupos USING btree (idgrupo);
CREATE INDEX contratosgrupos_idcontrato_idx ON contratosgrupos USING btree (idcontrato);

-- Fim - Rodrigo Pecci Acorse

-- Inicio - Euler 23/10/2013

delete from menu where nome = '$menu.esconder';
delete from parametrocorpore where idparametrocorpore in (20,21);

-- Fim - Euler

-- Inicio - Rodrigo Pecci Acorse 28/10/2013

CREATE INDEX idx_idatribuicao ON bpm_atribuicaofluxo USING btree (idatribuicao);
CREATE INDEX idx_idelemento ON bpm_elementofluxo USING btree (idelemento);
CREATE INDEX idx_idfluxo ON bpm_fluxo USING btree (idfluxo);
CREATE INDEX idx_idhistoricoitemtrabalho ON bpm_historicoitemtrabalho USING btree (idhistoricoitemtrabalho);
CREATE INDEX idx_idinstancia ON bpm_instanciafluxo USING btree (idinstancia);
CREATE INDEX idx_iditemtrabalho ON bpm_itemtrabalhofluxo USING btree (iditemtrabalho);
CREATE INDEX idx_idobjetoinstancia ON bpm_objetoinstanciafluxo USING btree (idobjetoinstancia);
CREATE INDEX idx_idelementoorigem ON bpm_sequenciafluxo USING btree (idelementoorigem);
CREATE INDEX idx_idtipofluxo ON bpm_tipofluxo USING btree (idtipofluxo);

-- Fim - Rodrigo Pecci Acorse

-- Inicio - Murilo Gabriel Rodrigues 31/10/2013

UPDATE bpm_tipofluxo SET descricao = 'Solicitação de Serviço' WHERE idtipofluxo = 1;
INSERT INTO lingua (idlingua, nome, sigla, datainicio, datafim) VALUES ($id_ligua_espanhol, 'Español', 'ES', '2013-10-31', NULL);

-- Fim - Murilo Gabriel Rodrigues

-- Inicio - Bruno Carvalho de Aquino 01/11/2013

ALTER TABLE itemrequisicaoproduto ADD PRIMARY KEY (iditemrequisicaoproduto);

CREATE TABLE historicoitemrequisicao (
     idhistorico      INT NOT NULL,
     iditemrequisicao INT NOT NULL,
     idresponsavel    INT NOT NULL,
     datahora         TIMESTAMP NULL DEFAULT NULL,
     acao             VARCHAR(100),
     situacao         VARCHAR(30) NOT NULL,
     complemento      TEXT,
     PRIMARY KEY (idhistorico, iditemrequisicao, idresponsavel),
     FOREIGN KEY (iditemrequisicao) REFERENCES itemrequisicaoproduto (
     iditemrequisicaoproduto),
     FOREIGN KEY (idresponsavel) REFERENCES empregados (idempregado)
);

-- Fim - Bruno Carvalho de Aquino 01/11/2013

-- INICIO Thiago Fernandes Oliveira 01/11/2013

alter table motivosuspensaoativid add column datafim date;

-- FIM Thiago Fernandes Oliveira

-- INICIO Emauri 05/11/2013

CREATE TABLE bi_categorias
  (
     idcategoria    INT8 NOT NULL,
     idcategoriapai INT8 NULL,
     nomecategoria  VARCHAR(80) NOT NULL,
     identificacao  VARCHAR(70) NOT NULL,
     situacao       CHAR(1) NOT NULL,
     CONSTRAINT pk_bi_categorias PRIMARY KEY (idcategoria)
  );

CREATE UNIQUE INDEX ix_bi_identcatg ON bi_categorias (identificacao);

ALTER TABLE bi_categorias
  ADD CONSTRAINT fk_bi_categ_reference_bi_categ FOREIGN KEY (idcategoriapai)
  REFERENCES bi_categorias (idcategoria) ON DELETE restrict ON UPDATE restrict;

CREATE TABLE bi_consulta
  (
     idconsulta     INT8 NOT NULL,
     idcategoria    INT8 NOT NULL,
     identificacao  VARCHAR(70) NOT NULL,
     nomeconsulta   VARCHAR(255) NOT NULL,
     tipoconsulta   CHAR(1) NOT NULL,
     textosql       TEXT NULL,
     acaocruzado    CHAR(1) NULL,
     situacao       CHAR(1) NULL,
     template       TEXT NULL,
     scriptexec     TEXT NULL,
     parametros     TEXT NULL,
     naoatualizbase CHAR(1) NULL,
     CONSTRAINT pk_bi_consulta PRIMARY KEY (idconsulta)
  );

CREATE UNIQUE INDEX ix_bi_ident ON bi_consulta (identificacao);

CREATE INDEX ix_bi_categ ON bi_consulta (idcategoria);

ALTER TABLE bi_consulta
  ADD CONSTRAINT fk_bi_consu_reference_bi_categ FOREIGN KEY (idcategoria)
  REFERENCES bi_categorias (idcategoria) ON DELETE restrict ON UPDATE restrict;

CREATE TABLE bi_consultacolunas
  (
     idconsultacoluna INT8 NOT NULL,
     idconsulta       INT8 NOT NULL,
     nomecoluna       VARCHAR(90) NOT NULL,
     tipofiltro       CHAR(1) NULL,
     ordem            INT4 NULL,
     CONSTRAINT pk_bi_consultacolunas PRIMARY KEY (idconsultacoluna)
  );

CREATE INDEX ix_bi_idconscols ON bi_consultacolunas (idconsulta);

ALTER TABLE bi_consultacolunas
  ADD CONSTRAINT fk_bi_consu_reference_bi_consu FOREIGN KEY (idconsulta)
  REFERENCES bi_consulta (idconsulta) ON DELETE restrict ON UPDATE restrict;

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (1,NULL,'Incidentes/Requisições','INCREQ001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (2,NULL,'Ativos e Configuração','ATVCFG001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (3,NULL,'Base de Conhecimento','BASECG001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (4,NULL,'N�veis de Serviço','NIVELS001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (5,NULL,'Problemas','PROBL001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (6,NULL,'Mudanças','MUDAN001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (7,NULL,'Liberações','LIBER001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (8,NULL,'Catalogo de Serviços','CATAL001','A');

INSERT INTO bi_categorias
            (idcategoria,idcategoriapai,nomecategoria,identificacao,situacao)
VALUES      (9,NULL,'Projetos','PROJET001','A');

-- FIM Emauri

-- INICIO Murilo Gabriel Rodrigues 01/11/2013

delete from menu where link like '/inventario/inventario.load';

-- FIM Murilo Gabriel Rodrigues

-- INICIO - Fl�vio 14/11/2013

CREATE TABLE instalacao (
   idinstalacao integer NOT NULL,
   sucesso character(1),
   passo character varying
);

ALTER TABLE ONLY instalacao ADD CONSTRAINT instalacao_pkey PRIMARY KEY (idinstalacao);

-- FIM - Fl�vio 14/11/2013

-- INICIO - MURILO GABRIEL RODRIGUES - 18/11/2013

delete from menu where link like '/monitoramentoServicos/monitoramentoServicos.load';

-- FIM - MURILO GABRIEL RODRIGUES - 18/11/2013
