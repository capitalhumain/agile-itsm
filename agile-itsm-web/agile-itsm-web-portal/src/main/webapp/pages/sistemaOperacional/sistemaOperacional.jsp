<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="br.com.centralit.citcorpore.util.WebUtil"%>
<%@page import="br.com.centralit.citcorpore.bean.UsuarioDTO"%>
<%@page import="br.com.citframework.dto.Usuario"%>

<!doctype html public "">
<html>
<head>
<%
	//identifica se a página foi aberta a partir de um iframe (popup de cadastro rápido)
	String iframe = "";
	iframe = request.getParameter("iframe");

%>
<%@include file="/include/header.jsp"%>
<%@include file="/novoLayout/common/include/titulo.jsp" %>

<%@include file="/include/javaScriptsComuns/javaScriptsComuns.jsp"%>
<script type="text/javascript" src="./js/sistemaOperacional.js"></script>

<%
//se for chamado por iframe deixa apenas a parte de cadastro da página
if(iframe != null){%>
	<link rel="stylesheet" type="text/css" href="./css/sistemaOperacional.css">
<%}%>

</head>

<body>
	<div id="wrapper">
	<%if(iframe == null){%>
		<%@include file="/include/menu_vertical.jsp"%>
	<%}%>
<!-- Conteudo -->
	<div id="main_container" class="main_container container_16 clearfix">
	<%if(iframe == null){%>
		<%@include file="/include/menu_horizontal.jsp"%>
	<%}%>

			<div class="flat_area grid_16">
				<h2><fmt:message key="comandoso.sistema"/></h2>
			</div>

			<div class="box grid_16 tabs">
				<ul class="tab_header clearfix">
					<li><a href="#tabs-1"><fmt:message key="sistema.cadastro"/></a></li>
					<li><a href="#tabs-2" class="round_top"><fmt:message key="sistema.pesquisa"/></a></li>
				</ul>
				<a href="#" class="toggle">&nbsp;</a>
				<div class="toggle_container">
					<div id="tabs-1" class="block">
						<div class="section">
							<form name='form'
								action='${ctx}/pages/sistemaOperacional/sistemaOperacional'>
								<div class="columns clearfix">
									<input type='hidden' name='id' />
									<div class="col_66">
										<fieldset>
											<label class="campoObrigatorio"><fmt:message key="sistema.nome"/></label>
											<div>
												<input type='text' name="nome" maxlength="80"
													class="Valid[Required] Description[sistema.nome]" />

											</div>
										</fieldset>
									</div>
								</div>

								<br>
								<br>

								<button type='button' name='btnGravar' class="light"
									onclick='document.form.save();'>
									<img
										src="${ctx}/template_new/images/icons/small/grey/pencil.png">
									<span><fmt:message key="citcorpore.comum.gravar"/></span>
								</button>
								<button type='button' name='btnLimpar' class="light"
									onclick='document.form.clear();'>
									<img
										src="${ctx}/template_new/images/icons/small/grey/clear.png">
									<span><fmt:message key="citcorpore.comum.limpar"/></span>
								</button>
							</form>
						</div>
					</div>
					<div id="tabs-2" class="block">
						<div class="section">
							<fmt:message key="citcorpore.comum.pesquisa"/>
							<form name='formPesquisa'>
								<cit:findField formName='formPesquisa'
									lockupName='LOOKUP_SISTEMAOPERACIONAL' id='LOOKUP_SISTEMAOPERACIONAL' top='0'
									left='0' len='550' heigth='400' javascriptCode='true'
									htmlCode='true' />
							</form>

						</div>
					</div>
					<!-- ## FIM - AREA DA APLICACAO ## -->


				</div>

			</div>

		</div>
		<!-- Fim da Pagina de Conteudo -->
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>