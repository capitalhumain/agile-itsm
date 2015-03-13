<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="locale" value="${fn.toLowerCase(sessionScope.locale)}" scope="request" />

<c:if test="${empty locale}">
	<c:set var="locale" value="pt" scope="request" />
</c:if>

<script type="text/javascript" src="${ctx}/js/i18n/messages_${locale}.js"></script>

<script type="text/javascript">
	var locale = '${locale}';
	var locale_format = 'dd/MM/yyyy';

	if (locale === 'en') {
		locale_format = 'MM/dd/yyyy';
	}

	function i18n_message(lbl) {
		var l = bundle['key'][lbl];

		if (l === undefined) {
			l = lbl;
		}
		return l;
	}
</script>
