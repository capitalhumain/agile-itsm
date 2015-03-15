-- INICIO - RODRIGO PECCI ACORSE - 09/11/2013

ALTER TABLE atividadeperiodica ALTER COLUMN criadopor TYPE varchar(256);
ALTER TABLE atividadeperiodica ALTER COLUMN alteradopor TYPE varchar(256);

-- FIM - RODRIGO PECCI ACORSE - 09/11/2013

-- INICIO - CARLOS SANTOS - 14/04/2014

alter table bpm_tipofluxo add idprocessonegocio int;

drop sequence s_logdados;

drop sequence s_opiniao;

drop sequence s_servicos;

create sequence s_logdados;

create sequence s_opiniao;

create sequence s_servicos;

create table delegcentroresultadofluxo (
   iddelegacaocentroresultado int4                 not null,
   idinstanciafluxo     int8                 not null,
   constraint pk_delegcentroresultadofluxo primary key (iddelegacaocentroresultado, idinstanciafluxo)
);

create table delegcentroresultadoprocesso (
   iddelegacaocentroresultado int4                 not null,
   idprocessonegocio    int4                 not null,
   constraint pk_delegcentroresultadoprocess primary key (iddelegacaocentroresultado, idprocessonegocio)
);

create table delegacaocentroresultado (
   iddelegacaocentroresultado int4                 not null,
   idresponsavel        int4                 not null,
   idcentroresultado    int4                 not null,
   idempregado          int4                 not null,
   datainicio           date                 not null,
   datafim              date                 not null,
   abrangencia          char(1)              not null,
   revogada             char(1)              null,
   constraint pk_delegacaocentroresultado primary key (iddelegacaocentroresultado)
);

create table gruponivelautoridade (
   idgrupo              int4                 not null,
   idnivelautoridade    int4                 not null,
   constraint pk_gruponivelautoridade primary key (idgrupo, idnivelautoridade)
);

create table historicorespcentroresultado (
   idhistoricorespcentroresultado int4                 not null,
   idcentroresultado    int4                 not null,
   idresponsavel        int4                 not null,
   datainicio           date                 not null,
   datafim              date                 null,
   constraint pk_historicorespcentroresultad primary key (idhistoricorespcentroresultado)
);

create table limiteaprovacao (
   idlimiteaprovacao    int4                 not null,
   tipolimiteporvalor   char(1)              not null,
   abrangenciacentroresultado char(1)              not null,
   identificacao        varchar(70)          not null,
   constraint pk_limiteaprovacao primary key (idlimiteaprovacao)
);

create table limiteaprovacaoautoridade (
   idnivelautoridade    int4                 not null,
   idlimiteaprovacao    int4                 not null,
   constraint pk_limiteaprovacaoautoridade primary key (idnivelautoridade, idlimiteaprovacao)
);

create table limiteaprovacaoprocesso (
   idlimiteaprovacao    int4                 not null,
   idprocessonegocio    int4                 not null,
   constraint pk_limiteaprovacaoprocesso primary key (idlimiteaprovacao, idprocessonegocio)
);

create table nivelautoridade (
   idnivelautoridade    int4                 not null,
   nomenivelautoridade  varchar(100)         not null,
   hierarquia           int4                 not null,
   situacao             char(1)              not null,
   constraint pk_nivelautoridade primary key (idnivelautoridade)
);

create table processonegocio (
   idprocessonegocio    int4                 not null,
   idgrupoexecutor      int4                 null,
   idgrupoadministrador int4                 null,
   nomeprocessonegocio  varchar(100)         not null,
   permissaosolicitacao char(1)              not null,
   percdispensanovaaprovacao decimal(5,2)         not null,
   permiteaprovacaonivelinferior char(1)              not null,
   constraint pk_processonegocio primary key (idprocessonegocio)
);

comment on column processonegocio.permissaosolicitacao is
'T - Todos
A - Por nivel de autoridade';

create table processonivelautoridade (
   idprocessonegocio    int4                 not null,
   idnivelautoridade    int4                 not null,
   permiteaprovacaopropria char(1)              not null,
   permitesolicitacao   char(1)              not null,
   antecedenciaminimaaprovacao int4                 not null,
   constraint pk_processonivelautoridade primary key (idprocessonegocio, idnivelautoridade)
);

create table respcentroresultadoprocesso (
   idresponsavel        int4                 not null,
   idcentroresultado    int4                 not null,
   idprocessonegocio    int4                 not null,
   constraint pk_respcentroresultadoprocesso primary key (idresponsavel, idcentroresultado, idprocessonegocio)
);

create table responsavelcentroresultado (
   idresponsavel        int4                 not null,
   idcentroresultado    int4                 not null,
   constraint pk_responsavelcentroresultado primary key (idresponsavel, idcentroresultado)
);

create table valorlimiteaprovacao (
   idvalorlimiteaprovacao int4                 not null,
   idlimiteaprovacao    int4                 not null,
   tipoutilizacao       char(1)              not null,
   tipolimite           char(1)              not null,
   valorlimite          numeric(10,2)        not null,
   intervalodias        int4                 null,
   constraint pk_valorlimiteaprovacao primary key (idvalorlimiteaprovacao)
);

comment on column valorlimiteaprovacao.tipoutilizacao is
'T - Todos
I - Uso interno
C - Atendimento ao cliente';

comment on column valorlimiteaprovacao.tipolimite is
'I - Individual
M - No mes
A - No ano
D - Intervalo dias';

alter table delegcentroresultadofluxo
   add constraint fk_delegcen_reference_delegaca foreign key (iddelegacaocentroresultado)
      references delegacaocentroresultado (iddelegacaocentroresultado)
      on delete restrict on update restrict;

alter table delegcentroresultadofluxo
   add constraint fk_delegcen_reference_bpm_inst foreign key (idinstanciafluxo)
      references bpm_instanciafluxo (idinstancia)
      on delete restrict on update restrict;

alter table delegcentroresultadoprocesso
   add constraint fk_delegcen_reference_delegaca foreign key (iddelegacaocentroresultado)
      references delegacaocentroresultado (iddelegacaocentroresultado)
      on delete restrict on update restrict;

alter table delegcentroresultadoprocesso
   add constraint fk_delegcen_reference_processo foreign key (idprocessonegocio)
      references processonegocio (idprocessonegocio)
      on delete restrict on update restrict;

alter table delegacaocentroresultado
   add constraint fk_delegaca_reference_responsa foreign key (idresponsavel, idcentroresultado)
      references responsavelcentroresultado (idresponsavel, idcentroresultado)
      on delete restrict on update restrict;

alter table delegacaocentroresultado
   add constraint fk_delegaca_reference_empregad foreign key (idempregado)
      references empregados (idempregado)
      on delete restrict on update restrict;

alter table gruponivelautoridade
   add constraint fk_gruponiv_reference_nivelaut foreign key (idnivelautoridade)
      references nivelautoridade (idnivelautoridade)
      on delete restrict on update restrict;

alter table gruponivelautoridade
   add constraint fk_gruponiv_reference_grupo foreign key (idgrupo)
      references grupo (idgrupo)
      on delete restrict on update restrict;

alter table historicorespcentroresultado
   add constraint fk_historic_reference_centrore foreign key (idcentroresultado)
      references centroresultado (idcentroresultado)
      on delete restrict on update restrict;

alter table historicorespcentroresultado
   add constraint fk_historic_reference_empregad foreign key (idresponsavel)
      references empregados (idempregado)
      on delete restrict on update restrict;

alter table limiteaprovacaoautoridade
   add constraint fk_limiteap_reference_nivelaut foreign key (idnivelautoridade)
      references nivelautoridade (idnivelautoridade)
      on delete restrict on update restrict;

alter table limiteaprovacaoautoridade
   add constraint fk_limiteap_reference_limiteap foreign key (idlimiteaprovacao)
      references limiteaprovacao (idlimiteaprovacao)
      on delete restrict on update restrict;

alter table limiteaprovacaoprocesso
   add constraint fk_limiteap_reference_limiteap foreign key (idlimiteaprovacao)
      references limiteaprovacao (idlimiteaprovacao)
      on delete restrict on update restrict;

alter table limiteaprovacaoprocesso
   add constraint fk_limiteap_reference_processo foreign key (idprocessonegocio)
      references processonegocio (idprocessonegocio)
      on delete restrict on update restrict;

alter table processonegocio
   add constraint fk_processo_reference_grupo foreign key (idgrupoexecutor)
      references grupo (idgrupo)
      on delete restrict on update restrict;

alter table processonegocio
   add constraint fk_processo_reference_grupo foreign key (idgrupoadministrador)
      references grupo (idgrupo)
      on delete restrict on update restrict;

alter table processonivelautoridade
   add constraint fk_processo_reference_processo foreign key (idprocessonegocio)
      references processonegocio (idprocessonegocio)
      on delete restrict on update restrict;

alter table processonivelautoridade
   add constraint fk_processo_reference_nivelaut foreign key (idnivelautoridade)
      references nivelautoridade (idnivelautoridade)
      on delete restrict on update restrict;

alter table respcentroresultadoprocesso
   add constraint fk_respcent_reference_responsa foreign key (idresponsavel, idcentroresultado)
      references responsavelcentroresultado (idresponsavel, idcentroresultado)
      on delete restrict on update restrict;

alter table respcentroresultadoprocesso
   add constraint fk_respcent_reference_processo foreign key (idprocessonegocio)
      references processonegocio (idprocessonegocio)
      on delete restrict on update restrict;

alter table responsavelcentroresultado
   add constraint fk_responsa_reference_empregad foreign key (idresponsavel)
      references empregados (idempregado)
      on delete restrict on update restrict;

alter table responsavelcentroresultado
   add constraint fk_responsa_reference_centrore foreign key (idcentroresultado)
      references centroresultado (idcentroresultado)
      on delete restrict on update restrict;

alter table valorlimiteaprovacao
   add constraint fk_valorlim_reference_limiteap foreign key (idlimiteaprovacao)
      references limiteaprovacao (idlimiteaprovacao)
      on delete restrict on update restrict;

-- FIM - CARLOS SANTOS - 14/04/2014

-- INICIO - renato.jesus - 25/04/2014

CREATE TABLE formulaos (
    idformulaos INT,
    descricao VARCHAR (254),
    formula VARCHAR (254),
    situacao CHAR(1) DEFAULT 'A',
    PRIMARY KEY (idformulaos)
);

ALTER TABLE atividadesservicocontrato ADD estruturaformulaos VARCHAR(254);
ALTER TABLE atividadesservicocontrato ADD formulacalculofinal VARCHAR (254);

CREATE TABLE contratoformulaos (
    idcontratoformulaos INT,
    idcontrato INT,
    idformulaos INT,
    deleted char(1) DEFAULT 'N',
    PRIMARY KEY (idcontratoformulaos)
);

-- FIM - renato.jesus - 25/04/2014

-- INICIO - Bruno.aquino - 02/05/2014

INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (1,'Horas * Complexidade * Dias Úteis','vValor{horas}*vComplexidade*vDiasUteis','A');
INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (2,'Horas * Complexidade * Dias Corridos','vValor{horas}*vComplexidade*{Periodo}vDiasCorridos','A');
INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (3,'Horas * Complexidade * Quantidade Mensal','vValor{horas}*vComplexidade*{Quantidade}vValor{Periodo}{Mensal}','A');
INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (4,'Horas * Complexidade * Quantidade Semanal','vValor{horas}*vComplexidade*{Quantidade}vValor{Periodo}{Semanal}','A');
INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (5,'Horas * Complexidade * Quantidade Diário','vValor{horas}*vComplexidade*{Quantidade}vValor{Periodo}{Diário}','A');
INSERT INTO formulaos (idformulaos,descricao,formula,situacao) VALUES (6,'(minutos/minutos horas X Complexidade) X NU','(vValor/vValor{Horas}*vComplexidade)*vValor{NU}','A');

INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (1,1,1,'N');
INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (2,1,2,'N');
INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (3,1,3,'N');
INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (4,1,4,'N');
INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (5,1,5,'N');
INSERT INTO contratoformulaos (idcontratoformulaos,idcontrato,idformulaos,deleted) VALUES (6,1,6,'N');

-- Fim - Bruno.aquino - 25/04/2014

-- inicio - flavio.santana 15/05/2014

create table bi_dashboard (
   iddashboard          int8                 not null,
   tipo                 char(1)              not null,
   idusuario            int8                 null,
   nomedashboard        varchar(150)         null,
   identificacao        varchar(70)          null,
   situacao             char(1)              null,
   parametros           text                 null,
   naoatualizbase       char(1)              null,
   temporefresh         int8                 null,
   constraint pk_bi_dashboard primary key (iddashboard)
);

create  index ix_ident_dash on bi_dashboard (
    identificacao
);

create table bi_itemdashboard (
   iditemdashboard      int8                 not null,
   titulo               varchar(150)         not null,
   iddashboard          int8                 not null,
   idconsulta           int8                 null,
   posicao              int2                 null,
   itemtop              int4                 null,
   itemleft             int4                 null,
   itemwidth            int4                 null,
   itemheight           int4                 null,
   parmssubst           text                 null,
   constraint pk_bi_itemdashboard primary key (iditemdashboard)
);

create  index ix_id_dash on bi_itemdashboard (
    iddashboard
);

alter table bi_itemdashboard
   add constraint fk_bi_itemd_reference_bi_dashb foreign key (iddashboard)
      references bi_dashboard (iddashboard)
      on delete restrict on update restrict;

alter table bi_itemdashboard
   add constraint fk_bi_itemd_reference_bi_consu foreign key (idconsulta)
      references bi_consulta (idconsulta)
      on delete restrict on update restrict;

create table bi_dashboardsegur (
   iddashboard          int8                 not null,
   idgrupo              int4                 not null,
   constraint pk_bi_dashboardsegur primary key (iddashboard, idgrupo)
);

alter table bi_dashboardsegur
   add constraint fk_bi_dashb_reference_bi_dashb foreign key (iddashboard)
      references bi_dashboard (iddashboard)
      on delete restrict on update restrict;

alter table bi_dashboardsegur
   add constraint fk_bi_dashb_reference_grupo foreign key (idgrupo)
      references grupo (idgrupo)
      on delete restrict on update restrict;

-- fim - flavio.santana