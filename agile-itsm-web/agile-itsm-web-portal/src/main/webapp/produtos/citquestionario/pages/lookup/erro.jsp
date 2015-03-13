<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%
String mensagem = (String)request.getAttribute("mensagem");
if (mensagem==null){
	mensagem = "Erro ao executar o localizar....";
}
%>
<table>
	<tr>
		<td>
			<B><font color='RED'><%=mensagem%></font></B>
		</td>
	</tr>
</table>