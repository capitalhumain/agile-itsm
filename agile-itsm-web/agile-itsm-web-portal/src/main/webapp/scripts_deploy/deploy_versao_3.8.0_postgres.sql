
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

-- INICIO - DAVID RODRIGUES DA SILVA 26/08/2014
-- Correção de script da versão 3.4.0 - Erro de syntax e referência a chave estrangeira

insert into importardados (idimportardados,idexternalconnection,importarpor,tipo,nome,script,agendarrotina,executarpor,horaexecucao,periodohora,datafim,tabelaorigem,tabeladestino,jsonmatriz)
values ('1', '1', 'S', 'J', 'Popular tabela de rh_funcionario', 'var importNames = JavaImporter();\n \nimportNames.importPackage(java.sql);\nimportNames.importPackage(java.util);\nimportNames.importPackage(Packages.br.com.citframework.integracao);\nimportNames.importPackage(Packages.br.com.centralit.citcorpore.bean);\n \njava.lang.Class.forName(driver);\n \nvar conn = java.sql.DriverManager.getConnection(url, user, password);\n  \nvar stmt = conn.createStatement();\nvar stmt2 = conn.createStatement();\n \nvar sql = \"select idfuncionario from rh_funcionario where idfuncionario >= ?\";\nvar sql_consulta_empregado;\n \nvar objs =  new Array();\nobjs[0]= 1;\n \nvar objs2;\n \nvar funcs = jdbcEngine.execSQL(sql, objs, 0);\nvar emps;\n \nvar rs;\nvar rs2;\nvar meta;\nvar aux;\nvar idFuncionario = 1;\nvar idEmpregado;\n\nvar str = \"\"; \nvar res = \"\";\n\nvar auxEmp; \n\n//Valida se a tabela esta vazia\nif(funcs == null || funcs.isEmpty()){\n \n	//Tabela vazia, popula a tabela com todos os dados\n \n 	sql = \"select distinct (CPF) CPF, NOMEFUNC FROM \" +  schema + \".[TFPFUN] where CODEMP in (1,5,6) and CPF is not null and DTDEM is null order by NOMEFUNC\";\n \n 	rs = stmt.executeQuery(sql);\n 	meta = rs.getMetaData();\n \n 	while(rs.next()) {\n \n 		objs = new Array();\n \n 		objs[0] = idFuncionario;\n 		\n 		for(var i = 1; i <= meta.getColumnCount(); i++) {\n 			objs[i] = rs.getObject(i);\n 		}\n \n 		objs.push(dataAtual);\n\n		str = objs[2]; \n		\n		//Remove os espaços em branco\n		res = str.trim();\n		res = res.replace(\" \", \"\");\n		\n		res = res.toLowerCase();\n	\n		sql_consulta_empregado = \" select usr.idempregado from usuario usr join empregados emp on emp.idempregado = usr.idempregado \"\n		sql_consulta_empregado += \" where lcase(replace(trim(emp.nome), '' '', '''')) like ''\" + res + \"'' order by usr.idempregado limit 1;\"\n		\n		emps = jdbcEngine.execSQL(sql_consulta_empregado, null, 0);\n	 \n		if(emps != null && !emps.isEmpty()){\n		\n			var auxEmp = emps.get(0);\n			\n			idEmpregado = Number(auxEmp[0]);\n			\n			objs[4] = idEmpregado;\n\n			sql = \"insert into rh_funcionario (idfuncionario, cpf, nome, datainicio, datafim, idempregado) values (?,?,?,?, null,?) \";\n	 \n			//Inserir registro\n			jdbcEngine.execUpdate(sql, objs);\n			\n		}\n\n 		idFuncionario += 1;\n \n 	}\n \n } else {\n \n	//Tabela ja contem dados, realiza update\n	\n 	sql = \"select NOMEFUNC, DTDEM, CPF FROM \" +  schema + \".[TFPFUN] where CODEMP in (1,5,6) and CPF is not null and DTALTER >= ''\";\n 	sql = sql + dataAtualFormatada + \"'' order by DTALTER\";\n \n 	rs = stmt.executeQuery(sql);\n 	objs = new Array();\n 	meta = rs.getMetaData();\n \n 	while(rs.next()) {\n \n 		sql = \"update rh_funcionario set nome = ?, datafim = ? where cpf = ?\";\n 		objs = new Array();\n \n 		for(var i = 1; i <= meta.getColumnCount(); i++) {\n \n 			if(i == 2){\n \n 				if(rs.getObject(i) == null || rs.getObject(i).equals(\"\"))\n 					objs[i-1] = null;\n 				else \n 					objs[i-1] = rs.getObject(i).toString().substring(0, 10);\n \n 			}else\n 				objs[i-1] = rs.getObject(i);\n \n 		}\n		\n		//Executa update(Retorna 1: atualizou o registro ou 0 caso não encontrou o registro)\n		// Se não eencontrou o registro, realiza insert\n 		if(jdbcEngine.execUpdate(sql, objs) == 0){\n \n			str = objs[0]; \n			\n			//Remove os espaços em branco\n			res = str.trim();\n			res = res.replace(\" \", \"\");\n			\n			res = res.toLowerCase();\n		\n			sql_consulta_empregado = \" select usr.idempregado from usuario usr join empregados emp on emp.idempregado = usr.idempregado \"\n			sql_consulta_empregado += \" where lcase(replace(trim(emp.nome), '' '', '''')) like ''\" + res + \"'' order by usr.idempregado limit 1; \"\n			\n			emps = jdbcEngine.execSQL(sql_consulta_empregado, null, 0);\n	 \n			if(emps != null && !emps.isEmpty()){\n		\n				var auxEmp = emps.get(0);\n				\n				idEmpregado = Number(auxEmp[0]);\n				\n				objs2 =  new Array();\n	 \n				sql = \"select idfuncionario from rh_funcionario order by idfuncionario desc limit 1\";\n	 \n				funcs = jdbcEngine.execSQL(sql, objs2, 0);\n	 \n				var aux = funcs.get(0);\n				\n				idFuncionario = Number(aux[0]);\n				\n				objs2.push(idFuncionario + 1);\n				objs2.push(objs[0]);\n				objs2.push(objs[2]);\n				objs2.push(dataAtual);\n				objs2.push(objs[1]);\n				objs2.push(idEmpregado);\n\n				sql = \"insert into rh_funcionario (idfuncionario, nome, cpf, datainicio, datafim, idempregado) values (?,?,?,?,?,?)\";\n				\n				jdbcEngine.execUpdate(sql, objs2);\n			\n			}\n 			\n 		}\n 	}\n \n }\n \n //Fecha conexão\n rs.close();\n stmt.close();\n conn.close();', 'S', 'P', '00:00', '4', NULL, NULL, NULL, '');

--FIM
