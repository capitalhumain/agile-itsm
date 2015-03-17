<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="br.com.centralit.citcorpore.util.CitCorporeConstantes"%>
<%@page import="br.com.centralit.citcorpore.util.WebUtil"%>
<%@page import="br.com.centralit.citcorpore.bean.UsuarioDTO"%>
<%@page import="br.com.citframework.dto.Usuario"%>

<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor"%>

<!DOCTYPE html>
<compress:html
    enabled="true"
    jsCompressor="closure"
    compressCss="true"
    compressJavaScript="true"
    removeComments="true"
    removeMultiSpaces="true">
<html>
<head>
    <%@include file="/novoLayout/common/include/libCabecalho.jsp" %>
    <%@include file="/novoLayout/common/include/titulo.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/novoLayout/common/include/css/template.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/pages/index/css/index.css"/>
</head>
<cit:janelaAguarde id="JANELA_AGUARDE_MENU"  title="" style="display:none;top:325px;width:300px;left:500px;height:50px;position:absolute;"></cit:janelaAguarde>
<body>
    <div class="${not empty param.iframe ? 'container-fluid fixed' : ''}">
        <div class="navbar ${not empty param.iframe ? 'nomain' : 'main'} hidden-print">
            <c:if test="${empty param.iframe}">
                <%@include file="/novoLayout/common/include/cabecalho.jsp" %>
                <%@include file="/novoLayout/common/include/menuPadrao.jsp" %>
            </c:if>
        </div>

        <div id="wrapper" class="${not empty param.iframe ? 'nowrapper' : ''}">
            <!-- Inicio conteudo -->
            <div id="content">
                <mr:menuRapido />
            </div>
            <!--  Fim conteudo-->
            <%@include file="/novoLayout/common/include/rodape.jsp" %>
        </div>
    </div>
</body>
</html>
</compress:html>
