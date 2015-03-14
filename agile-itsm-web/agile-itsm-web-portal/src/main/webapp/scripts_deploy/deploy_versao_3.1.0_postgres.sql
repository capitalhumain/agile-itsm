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

-- inicio murilo pacheco

CREATE TABLE rh_conhecimento (
  idConhecimento Integer NOT NULL,
  descricao character varying(100) DEFAULT NULL,
  detalhe character varying(100) DEFAULT NULL,
  PRIMARY KEY (idConhecimento)
);

CREATE TABLE rh_experienciainformatica (
  idExperienciaInformatica Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idExperienciaInformatica)
);

CREATE TABLE rh_habilidade (
  idHabilidade Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idHabilidade)
);

CREATE TABLE rh_atitudeindividual (
  idAtitudeIndividual Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idAtitudeIndividual)
);

CREATE TABLE rh_atitudeorganizacional (
  idatitudeorganizacional Integer NOT NULL,
  descricao character varying(200) NOT NULL,
  detalhe text,
  PRIMARY KEY (idatitudeorganizacional)
);

CREATE TABLE rh_atitudecandidato (
  identrevista Integer NOT NULL,
  idatitudeorganizacional Integer NOT NULL,
  avaliacao char(1) DEFAULT NULL,
  PRIMARY KEY (identrevista,idatitudeorganizacional),
  CONSTRAINT fk_reference_atitudorg FOREIGN KEY (idatitudeorganizacional) REFERENCES rh_atitudeorganizacional (idatitudeorganizacional)
);

CREATE TABLE rh_certificacao (
  idCertificacao Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idCertificacao)
);

CREATE TABLE rh_certificacaorequisicaopessoal (
  idcertificacao Integer NOT NULL,
  versaocertificacao character varying(100) NOT NULL,
  validadecertificacao character varying(100) NOT NULL,
  descricaocertificacao character varying(100) NOT NULL,
  idcurriculo Integer NOT NULL,
  PRIMARY KEY (idcertificacao)
);

CREATE TABLE rh_curso (
  idCurso Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idCurso)
);

CREATE TABLE rh_jornadadetrabalho (
  idjornada Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  escala char(1) NOT NULL,
  considerarferiados char(1) NOT NULL,
  PRIMARY KEY (idjornada)
);

CREATE TABLE rh_idioma (
  idIdioma Integer NOT NULL,
  descricao character varying(100) NOT NULL,
  detalhe character varying(100) NOT NULL,
  PRIMARY KEY (idIdioma)
);

CREATE TABLE rh_requisicaoatitudeindividual (
  idatitudeindividual Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) NOT NULL,
  PRIMARY KEY (idatitudeindividual,idsolicitacaoservico)
);

CREATE TABLE rh_requisicaocertificacao (
  idcertificacao Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idcertificacao,idsolicitacaoservico)
);

CREATE TABLE rh_requisicaoconhecimento (
  idconhecimento Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idconhecimento,idsolicitacaoservico)
);

CREATE TABLE rh_requisicaocurso (
  idsolicitacaoservico Integer NOT NULL,
  idcurso Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idsolicitacaoservico,idcurso)
);

CREATE TABLE rh_requisicaoexperienciaanterior (
  idconhecimento Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idconhecimento,idsolicitacaoservico)
);


CREATE TABLE rh_requisicaoexperienciainformatica (
  idexperienciainformatica Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idexperienciainformatica,idsolicitacaoservico)
);

CREATE TABLE rh_requisicaoformacaoacademica (
  idformacaoacademica Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idformacaoacademica,idsolicitacaoservico)
);

CREATE TABLE rh_requisicaohabilidade (
  idhabilidade Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (idhabilidade,idsolicitacaoservico),
  CONSTRAINT fk_reference_738 FOREIGN KEY (idhabilidade) REFERENCES rh_habilidade (idHabilidade)
);

CREATE TABLE rh_requisicaoidioma (
  ididioma Integer NOT NULL,
  idsolicitacaoservico Integer NOT NULL,
  obrigatorio char(1) DEFAULT NULL,
  PRIMARY KEY (ididioma,idsolicitacaoservico),
  CONSTRAINT fk_reference_740 FOREIGN KEY (ididioma) REFERENCES rh_idioma (idIdioma)
);

CREATE TABLE rh_jornadaempregado (
  idjornada INTEGER NOT NULL,
  descricao char(100) NOT NULL,
  escala char(1) NOT NULL,
  considerarferiados char(1) NOT NULL,
  PRIMARY KEY (idjornada)
);

-- ####################### inicio fluxo ###################### --

INSERT INTO bpm_fluxo (idfluxo, versao, idtipofluxo, variaveis, conteudoxml, datainicio, datafim) VALUES($id_fluxo_pessoal_152, '21.0', $id_tipofluxo_pessoal, 'solicitacaoServico;solicitacaoServico.situacao;solicitacaoServico.grupoAtual;solicitacaoServico.grupoNivel1', '', '2013-10-03', NULL);

-- ####################### fim fluxo ###################### --

-- ############################## inicio elemento fluxo ################################ --

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1588, $id_fluxo_pessoal_152, 'Inicio', '', '', '', '', '', '', '', '', '', '', '', '', '', 19, 28, 32, 32, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1589, $id_fluxo_pessoal_152, 'Tarefa', '', 'Analisar requisiçã de pessoal', 'Analisar requisiçã de pessoal', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', '#{solicitacaoServico.grupoAtual}', '', '', '', '', '', '', 216, 11, 65, 140, '', 'AnaliseRequisicaoPessoal', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1590, $id_fluxo_pessoal_152, 'Tarefa', '', 'Alterar requisiçã de pessoal', 'Alterar requisiçã de pessoal', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '#{solicitacaoServico.grupoNivel1}', '', '', '', '', '', '', 337, 203, 65, 140, '', 'RequisicaoPessoal', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1591, $id_fluxo_pessoal_152, 'Tarefa', '', 'Triagem de curr�culos', 'Triagem de curr�culos', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', ' #{solicitacaoServico.grupoAtual}', '', '', '', '', '', '', 542, 11, 65, 140, '', 'TriagemRequisicaoPessoal', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1592, $id_fluxo_pessoal_152, 'Tarefa', '', 'Entrevista com RH', 'Entrevista com RH', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', ' #{solicitacaoServico.grupoAtual}', '', '#{execucaoFluxo}.associaItemTrabalhoEntrevistaRH(#{itemTrabalho}); ', '', '', '', '', 772, 111, 65, 140, '', 'EntrevistaRequisicaoPessoal', NULL, '', 'N', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1593, $id_fluxo_pessoal_152, 'Tarefa', '', 'Entrevista com Gestor', 'Entrevista com Gestor', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', NULL, 'script:#{execucaoFluxo}.recuperaLoginGestores();', '#{execucaoFluxo}.associaItemTrabalhoEntrevistaGestor(#{itemTrabalho}); ', '', '', '', '', 781, 253, 65, 140, '', 'EntrevistaRequisicaoPessoal', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1594, $id_fluxo_pessoal_152, 'Tarefa', '', 'Entrevista com RH', 'Entrevista com RH', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', '#{solicitacaoServico.grupoAtual}', '', '#{execucaoFluxo}.associaItemTrabalhoEntrevistaRH(#{itemTrabalho});', '', '', '', '', 774, 471, 65, 140, '', 'EntrevistaRequisicaoPessoal', NULL, '', 'N', '', 'S', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1595, $id_fluxo_pessoal_152, 'Tarefa', '', 'Entrevista com Gestor', 'Entrevista com Gestor', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', NULL, 'script:#{execucaoFluxo}.recuperaLoginGestores();', '#{execucaoFluxo}.associaItemTrabalhoEntrevistaGestor(#{itemTrabalho});', '', '', '', '', 948, 471, 65, 140, '', 'EntrevistaRequisicaoPessoal', NULL, '', 'N', '', 'S', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1596, $id_fluxo_pessoal_152, 'Tarefa', '', 'Classificacao', 'Classificacao', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', ' #{solicitacaoServico.grupoAtual}', '', '#{execucaoFluxo}.classificaCandidato();', '', '', '', '', 1165, 84, 65, 140, '', 'ClassificacaoRequisicaoPessoal', NULL, '', 'N', '', 'S', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1597, $id_fluxo_pessoal_152, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '#{solicitacaoServico.grupoAtual}', '', NULL, NULL, NULL, NULL, NULL, 86, 33, 22, 31, 'rhSolPesEncaminhada', NULL, NULL, NULL, NULL, '', NULL, NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1598, $id_fluxo_pessoal_152, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 149, 33, 22, 31, 'rhSolPesAbertura', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1599, $id_fluxo_pessoal_152, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 391, 125, 22, 31, 'rhSolPesRejeitada', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1600, $id_fluxo_pessoal_152, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 468, 33, 22, 31, '', NULL, NULL, NULL, NULL, '', NULL, NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1601, $id_fluxo_pessoal_152, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 1456, 226, 22, 31, 'rhSolPesPreenchida', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1602, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 385, 23, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1603, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 655, 192, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1604, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 997, 181, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1605, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 656, 369, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1606, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 1448, 97, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1607, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 587, 519, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1608, $id_fluxo_pessoal_152, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 998, 97, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo  (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao) VALUES($id_elementofluxo_pessoal_1609, $id_fluxo_pessoal_152, 'Finalizacao', '', '', '', '', '', '', '', '', '', '', '', '', '', 1456, 296, 32, 32, '', '', NULL, '', '', '', '', NULL);

-- ################################# fim elemento fluxo ######################## --

-- ################################# inicio sequencia fluxo ######################## --

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1588, $id_elementofluxo_pessoal_1597, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 68.5, 44, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1589, $id_elementofluxo_pessoal_1602, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 370.5, 43.75, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1590, $id_elementofluxo_pessoal_1589, $id_fluxo_pessoal_152, NULL, NULL, '', 3, 2, 284, 235, 'S', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1591, $id_elementofluxo_pessoal_1607, $id_fluxo_pessoal_152, NULL, NULL, '', 2, 0, 610, 319, 'S', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1592, $id_elementofluxo_pessoal_1604, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 954.5, 172.75, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1593, $id_elementofluxo_pessoal_1604, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 959, 243.75, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1594, $id_elementofluxo_pessoal_1595, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 931, 503.5, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1595, $id_elementofluxo_pessoal_1604, $id_fluxo_pessoal_152, NULL, NULL, '', 0, 2, 1018, 347, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1596, $id_elementofluxo_pessoal_1606, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 1376.5, 117.25, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1597, $id_elementofluxo_pessoal_1598, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 133, 44, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1598, $id_elementofluxo_pessoal_1589, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 198, 43.75, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1599, $id_elementofluxo_pessoal_1590, $id_fluxo_pessoal_152, NULL, NULL, '', 2, 0, 406.75, 175, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1600, $id_elementofluxo_pessoal_1591, $id_fluxo_pessoal_152, NULL, NULL, '', 1, 3, 520.5, 43.75, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1601, $id_elementofluxo_pessoal_1609, $id_fluxo_pessoal_152, NULL, NULL, '', 2, 0, 1471.75, 272, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1602, $id_elementofluxo_pessoal_1599, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.requisicaoRejeitada();', 2, 0, 406.25, 95, 'N', 'rejeitada');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1602, $id_elementofluxo_pessoal_1600, $id_fluxo_pessoal_152, NULL, NULL, '!#{execucaoFluxo}.requisicaoRejeitada();', 1, 3, 456, 44, 'S', 'não rejeitada');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1603, $id_elementofluxo_pessoal_1592, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.existeEntrevistaPendenteRH();', 1, 3, 707, 143, 'S', 'existe entrevista RH');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1603, $id_elementofluxo_pessoal_1593, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.existeEntrevistaPendenteGestor();', 1, 3, 711, 287, 'S', 'existe entrevista Gestor');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1604, $id_elementofluxo_pessoal_1608, $id_fluxo_pessoal_152, NULL, NULL, '', 0, 2, 1018.5, 160, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1605, $id_elementofluxo_pessoal_1594, $id_fluxo_pessoal_152, NULL, NULL, '!#{execucaoFluxo}.preRequisitoEntrevistaGestor();', 2, 3, 678, 502, 'S', 'entrevistas sequenciasis');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1605, $id_elementofluxo_pessoal_1603, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.preRequisitoEntrevistaGestor();', 0, 2, 676.5, 301.5, 'N', 'entrevistas simultaneas');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1606, $id_elementofluxo_pessoal_1591, $id_fluxo_pessoal_152, NULL, NULL, '!#{execucaoFluxo}.vagasPreenchidas();', 0, 1, 1468, 47, 'S', 'vagas não preenchidas');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1606, $id_elementofluxo_pessoal_1601, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.vagasPreenchidas();', 2, 0, 1470.25, 182.5, 'N', 'vagas preenchidas');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1607, $id_elementofluxo_pessoal_1605, $id_fluxo_pessoal_152, NULL, NULL, '!#{execucaoFluxo}.vagasPreenchidas();', 1, 3, 642.5, 465, 'N', 'vagas não preenchidas e solicitacao em andamento');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1607, $id_elementofluxo_pessoal_1609, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.vagasPreenchidas();', 2, 2, 1471, 574, 'S', 'vagas não preenchidas e solicitacao cancelada');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1608, $id_elementofluxo_pessoal_1591, $id_fluxo_pessoal_152, NULL, NULL, '!#{execucaoFluxo}.entrevistaAprovadaENaoClassificada();', 0, 1, 1016, 84, 'S', 'não existe entrevista');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_pessoal_1608, $id_elementofluxo_pessoal_1596, $id_fluxo_pessoal_152, NULL, NULL, '#{execucaoFluxo}.entrevistaAprovadaENaoClassificada();', 1, 3, 1054, 118, 'S', 'existe entrevista aprovada e não classificada');

-- ################################# fim sequencia fluxo ######################## --

-- ####################### inicio fluxo Pessoal ###################### --

-- ####################### inicio fluxo cargo ###################### --

INSERT INTO bpm_fluxo (idfluxo, versao, idtipofluxo, variaveis, conteudoxml, datainicio, datafim)
VALUES($id_fluxo_cargo_153, '4.0', $id_tipofluxo_cargo, 'solicitacaoServico;solicitacaoServico.situacao;solicitacaoServico.grupoAtual;solicitacaoServico.grupoNivel1', '', '2013-10-07', NULL);

-- ####################### fim fluxo cargo ###################### --

-- ############################## inicio elemento fluxo cargo ################################ --

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1610, $id_fluxo_cargo_153, 'Inicio', '', '', '', '', '', '', '', '', '', '', '', '', '', 29, 60, 32, 32, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1611, $id_fluxo_cargo_153, 'Tarefa', '', 'Analisar solicitação de cargo', 'Analisar solicitação de cargo', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '', '#{solicitacaoServico.grupoAtual}', '', '', '', '', '', '', 292, 43, 65, 140, '', 'AnaliseSolicitacaoCargo', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1612, $id_fluxo_cargo_153, 'Tarefa', '', 'Alterar solicitação de cargo', 'Alterar solicitação de cargo', 'U', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '/pages/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load', '#{solicitacaoServico.grupoNivel1}', '', '', '', '', '', '', 594, 241, 65, 140, '', 'SolicitacaoCargo', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1613, $id_fluxo_cargo_153, 'Script', '', 'encerra', '', '', '', '', '', '', '', '', '#{execucaoFluxo}.encerra();', '', '', 855, 44, 65, 140, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1614, $id_fluxo_cargo_153, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 116, 65, 22, 31, 'rhSolCargoAbertura', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1615, $id_fluxo_cargo_153, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '#{solicitacaoServico.grupoAtual}', '', NULL, NULL, NULL, NULL, NULL, 207, 64, 22, 31, 'rhSolCarEncaminhada', NULL, NULL, NULL, NULL, '', NULL, NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1616, $id_fluxo_cargo_153, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 648, 163, 22, 31, 'rhSolCargogoRejeita', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1617, $id_fluxo_cargo_153, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 520, 66, 22, 31, 'rhSolCargoAndamento', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1618, $id_fluxo_cargo_153, 'Email', NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, 755, 67, 22, 31, 'rhSolCargoAprovada', NULL, NULL, NULL, NULL, '#{solicitacaoServico.emailcontato}', NULL, NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1619, $id_fluxo_cargo_153, 'Porta', '', '', '', '', '', '', '', '', '', '', '', '', '', 643, 57, 42, 42, '', '', NULL, '', '', '', '', NULL);

INSERT INTO bpm_elementofluxo (idelemento, idfluxo, tipoelemento, subtipo, nome, documentacao, tipointeracao, url, visao, grupos, usuarios, acaoentrada, acaosaida, script, textoemail, nomefluxoencadeado, posx, posy, altura, largura, modeloemail, template, intervalo, condicaodisparo, multiplasinstancias, destinatariosemail, contabilizasla, percexecucao)
VALUES($id_elementofluxo_cargo_1620, $id_fluxo_cargo_153, 'Finalizacao', '', '', '', '', '', '', '', '', '', '', '', '', '', 1081, 60, 32, 32, '', '', NULL, '', '', '', '', NULL);

-- ################################# fim elemento fluxo cargo ######################## --

-- ################################# inicio sequencia fluxo cargo ######################## --

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1610, $id_elementofluxo_cargo_1614, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 88.5, 76, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1611, $id_elementofluxo_cargo_1617, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 476, 76.25, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1612, $id_elementofluxo_cargo_1611, $id_fluxo_cargo_153, NULL, NULL, '', 3, 2, 361, 272, 'S', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1613, $id_elementofluxo_cargo_1620, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 1038, 76.25, 'N', '');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1614, $id_elementofluxo_cargo_1615, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 177, 75.5, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1615, $id_elementofluxo_cargo_1611, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 265, 75.25, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1616, $id_elementofluxo_cargo_1612, $id_fluxo_cargo_153, NULL, NULL, '', 2, 0, 663.75, 213, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1617, $id_elementofluxo_cargo_1619, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 597, 77.5, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1618, $id_elementofluxo_cargo_1613, $id_fluxo_cargo_153, NULL, NULL, '', 1, 3, 820.5, 77.25, 'N', NULL);

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1619, $id_elementofluxo_cargo_1616, $id_fluxo_cargo_153, NULL, NULL, '#{execucaoFluxo}.solicitacaoRejeitada();', 2, 0, 663.75, 131, 'N', 'rejeitada');

INSERT INTO bpm_sequenciafluxo (idelementoorigem, idelementodestino, idfluxo, nomeclasseorigem, nomeclassedestino, condicao, idconexaoorigem, idconexaodestino, bordax, borday, posicaoalterada, nome)
VALUES($id_elementofluxo_cargo_1619, $id_elementofluxo_cargo_1618, $id_fluxo_cargo_153, NULL, NULL, '', '1', '3', '720', '78', 'N', NULL);

-- ################################# fim sequencia fluxo ######################## --

-- ######################################## template solicitacao servico ###########################################

INSERT INTO templatesolicitacaoservico (idtemplate, identificacao, nometemplate, nomeclassedto, nomeclasseaction, nomeclasseservico, urlrecuperacao, scriptaposrecuperacao, habilitadirecionamento, habilitasituacao, habilitasolucao, alturadiv, habilitaurgenciaimpacto, habilitanotificacaoemail, habilitaproblema, habilitamudanca, habilitaitemconfiguracao, habilitasolicitacaorelacionada, habilitagravarecontinuar, idquestionario,aprovacao)
VALUES($id_template_sol_ser_pessoal, 'RequisicaoPessoal', 'Requisiçã Pessoal', 'br.com.centralit.citcorpore.rh.bean.RequisicaoPessoalDTO', 'br.com.centralit.citcorpore.ajaxForms.RequisicaoPessoal', 'br.com.centralit.citcorpore.rh.negocio.RequisicaoPessoalServiceEjb', '/pages/requisicaoPessoal/requisicaoPessoal.load', '', 'N', 'S', 'N', 1000, 'S', 'N', 'N', 'N', 'N', 'N', 'S', NULL, 'N');

INSERT INTO templatesolicitacaoservico (idtemplate, identificacao, nometemplate, nomeclassedto, nomeclasseaction, nomeclasseservico, urlrecuperacao, scriptaposrecuperacao, habilitadirecionamento, habilitasituacao, habilitasolucao, alturadiv, habilitaurgenciaimpacto, habilitanotificacaoemail, habilitaproblema, habilitamudanca, habilitaitemconfiguracao, habilitasolicitacaorelacionada, habilitagravarecontinuar, idquestionario,aprovacao)
VALUES($id_template_sol_ser_analise_pessoal, 'AnaliseRequisicaoPessoal', 'An�lise Requisiçã Pessoal', 'br.com.centralit.citcorpore.rh.bean.RequisicaoPessoalDTO', 'br.com.centralit.citcorpore.ajaxForms.AnaliseRequisicaoPessoal', 'br.com.centralit.citcorpore.rh.negocio.RequisicaoPessoalServiceEjb', '/pages/analiseRequisicaoPessoal/analiseRequisicaoPessoal.load', '', 'N', 'S', 'N', 1000, 'S', 'N', 'N', 'N', 'N', 'N', 'S', NULL, 'N');

INSERT INTO templatesolicitacaoservico (idtemplate, identificacao, nometemplate, nomeclassedto, nomeclasseaction, nomeclasseservico, urlrecuperacao, scriptaposrecuperacao, habilitadirecionamento, habilitasituacao, habilitasolucao, alturadiv, habilitaurgenciaimpacto, habilitanotificacaoemail, habilitaproblema, habilitamudanca, habilitaitemconfiguracao, habilitasolicitacaorelacionada, habilitagravarecontinuar, idquestionario,aprovacao)
VALUES($id_template_sol_ser_triagem, 'TriagemRequisicaoPessoal', 'Triagem Requisiçã Pessoal', 'br.com.centralit.citcorpore.rh.bean.RequisicaoPessoalDTO', 'br.com.centralit.citcorpore.ajaxForms.TriagemRequisicaoPessoal', 'br.com.centralit.citcorpore.rh.negocio.RequisicaoPessoalServiceEjb', '/pages/triagemRequisicaoPessoal/triagemRequisicaoPessoal.load', '', 'N', 'N', 'N', 800, 'S', 'N', 'N', 'N', 'N', 'N', 'S', NULL, 'N');

INSERT INTO templatesolicitacaoservico (idtemplate, identificacao, nometemplate, nomeclassedto, nomeclasseaction, nomeclasseservico, urlrecuperacao, scriptaposrecuperacao, habilitadirecionamento, habilitasituacao, habilitasolucao, alturadiv, habilitaurgenciaimpacto, habilitanotificacaoemail, habilitaproblema, habilitamudanca, habilitaitemconfiguracao, habilitasolicitacaorelacionada, habilitagravarecontinuar, idquestionario,aprovacao)
VALUES($id_template_sol_ser_entrevista, 'EntrevistaRequisicaoPessoal', 'Entrevista Requisicao Pessoal', 'br.com.centralit.citcorpore.rh.bean.RequisicaoPessoalDTO', 'br.com.centralit.citcorpore.ajaxForms.EntrevistaRequisicaoPessoal', 'br.com.centralit.citcorpore.rh.negocio.RequisicaoPessoalServiceEjb', '/pages/entrevistaRequisicaoPessoal/entrevistaRequisicaoPessoal.load', '', 'N', 'N', 'N', 800, 'S', 'N', 'N', 'N', 'N', 'N', 'S', NULL, 'N');

-- ###################################### servicos ####################################

INSERT INTO servico (idservico, idcategoriaservico, idsituacaoservico, idtiposervico, idimportancianegocio, idempresa, idtipoeventoservico, idtipodemandaservico, idlocalexecucaoservico, nomeservico, detalheservico, objetivo, passosservico, datainicio, linkprocesso, descricaoprocesso, tipodescprocess, dispportal, quadroorientportal, deleted, detalhesServico, siglaAbrev, idbaseconhecimento, idtemplatesolicitacao, idtemplateacompanhamento)
VALUES($id_sol_serv_requisicao_pessoal, 2131, 1, 1, NULL, 1, 1, 1, NULL, 'REQUISI��O DE PESSOAL', 'REQUISI��O DE PESSOAL', NULL, NULL, '2013-01-01', NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, $id_template_sol_ser_pessoal, $id_template_sol_ser_pessoal);

INSERT INTO servico (idservico, idcategoriaservico, idsituacaoservico, idtiposervico, idimportancianegocio, idempresa, idtipoeventoservico, idtipodemandaservico, idlocalexecucaoservico, nomeservico, detalheservico, objetivo, passosservico, datainicio, linkprocesso, descricaoprocesso, tipodescprocess, dispportal, quadroorientportal, deleted, detalhesServico, siglaAbrev, idbaseconhecimento, idtemplatesolicitacao, idtemplateacompanhamento)
VALUES($id_sol_serv_requisicao_cargo, 2131, 1, 1, NULL, 1, 1, 1, NULL, 'SOLICITA��O DE CARGO', 'SOLICITA��O DE CARGO', NULL, NULL, '2013-01-01', NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, $id_template_sol_ser_cargo, $id_template_sol_ser_cargo);

INSERT INTO servicocontrato (idservicocontrato, idservico, idcontrato, idcondicaooperacao, datainicio, datafim, observacao, custo, restricoespressup, objetivo, permiteslanocadinc, linkprocesso, descricaoprocesso, tipodescprocess, deleted, arearequisitante, idgruponivel1, idModeloEmailCriacao, idModeloEmailFinalizacao, idModeloEmailAcoes, idgrupoexecutor, idcalendario, permSLATempoACombinar, permMudancaSLA, permMudancaCalendario, idgrupoaprovador)
VALUES($id_servicocontrato_sol_cargo, $id_sol_serv_requisicao_cargo, 1, 10, '2012-08-28', NULL, '', NULL, '', '', NULL, '', '', NULL, NULL, '', NULL, 1, 2, -999, -999, 1, NULL, NULL, NULL, NULL);

INSERT INTO servicocontrato (idservicocontrato, idservico, idcontrato, idcondicaooperacao, datainicio, datafim, observacao, custo, restricoespressup, objetivo, permiteslanocadinc, linkprocesso, descricaoprocesso, tipodescprocess, deleted, arearequisitante, idgruponivel1, idModeloEmailCriacao, idModeloEmailFinalizacao, idModeloEmailAcoes, idgrupoexecutor, idcalendario, permSLATempoACombinar, permMudancaSLA, permMudancaCalendario, idgrupoaprovador)
VALUES($id_servicocontrato_sol_pessoal, $id_sol_serv_requisicao_pessoal, 1, 10, '2012-08-28', NULL, '', NULL, '', '', NULL, '', '', NULL, NULL, '', NULL, 1, 2, -999, -999, 1, NULL, NULL, NULL, NULL);

INSERT INTO fluxoservico (idfluxoservico, idservicocontrato, idtipofluxo, idfase, principal, deleted)
VALUES($id_fluxoservico_sol_pessoal, $id_servicocontrato_sol_pessoal, $id_tipofluxo_pessoal, 2, 'S', NULL);

INSERT INTO fluxoservico (idfluxoservico, idservicocontrato, idtipofluxo, idfase, principal, deleted)
VALUES($id_fluxoservico_sol_cargo, $id_servicocontrato_sol_cargo, $id_tipofluxo_cargo, 2, 'S', NULL);

-- #####################################

-- fim murilo pacheco modulo RH

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
