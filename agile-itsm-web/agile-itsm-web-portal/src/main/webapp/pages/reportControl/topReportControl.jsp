<%@page import="br.com.centralit.citcorpore.util.CitCorporeConstantes"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="br.com.centralit.citcorpore.util.ParametroUtil"%>
<%@page import="br.com.citframework.util.Constantes"%>
<%@page import="br.com.centralit.citcorpore.util.WebUtil"%>
<%@page import="br.com.centralit.citcorpore.bean.UsuarioDTO"%>
<%@page import="br.com.citframework.dto.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/include/header.jsp"%> 
<script>
function voltar(){
	parent.window.location = '${ctx}/pages/index/index.load';
}
</script>
</head>
<body>
<button type='button' onclick='voltar()' class='light'><fmt:message key="questionario.voltar" /></button>
</body>
</html>
