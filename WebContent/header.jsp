<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="formcons" class="com.rhcloud.pugmg.cinetheatrosys.controle.uteis.FormConstants" scope="session"/>
<c:if test="${ajax==null || ajax == false}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
		<title>Cine Theatro</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<fmt:setLocale value="pt_br"/>	
		
		<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>"/>	
		
		<!-- CSS -->
		<link href="<c:url value="/lib/bootstrap-3.2.0-dist/css/bootstrap.min.css"/>" rel="stylesheet" />
		<link href="<c:url value="/lib/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.css"/>" rel="stylesheet" />
		<link href="<c:url value="/lib/bootstrap-3.2.0-dist/css/datepicker.css"/>" rel="stylesheet" />
		
		<link href="<c:url value="/css/jquery.autocomplete.css"/>" rel="stylesheet" /> 
		<link href="<c:url value="/css/style.css"/>" rel="stylesheet" /> 
		<link href="<c:url value="/css/jquery.fileupload-ui.css"/>" rel="stylesheet">
		<link href="<c:url value="/css/jasny-bootstrap.min.css"/>" rel="stylesheet">
		<script src="<c:url value="/javascripts/externos/jquery-2.1.0.min.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/jquery.validate.min.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/additional-methods.min.js"/>"></script>
	   
	   	<!-- Javascripts -->
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"/>"></script>	
	   	
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/moment.js"/>"></script>
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.js"/>"></script>	
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/locales/bootstrap-datetimepicker.pt-BR.js"/>" charset="UTF-8"></script>
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/bootstrap-datepicker.js"/>"></script>	
	   	<script src="<c:url value="/lib/bootstrap-3.2.0-dist/js/locales/bootstrap-datepicker.pt-BR.js"/>" charset="UTF-8"></script>
	   	
	   	<script src="<c:url value="/javascripts/externos/bootbox.min.js"/>"></script>
	   	<script src="<c:url value="/javascripts/externos/janela.js"/>"></script>    	
	   	<script src="<c:url value="/javascripts/externos/jquery.ui.widget.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/jquery.iframe-transport.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/jquery.fileupload.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/jquery-dateFormat.min.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/dateFormat.js"/>"></script>
		<script src="<c:url value="/javascripts/externos/jasny-bootstrap.min.js"/>"></script>
		
		<script src="<c:url value="/lib/ckeditorsimples/ckeditor.js"/>"></script>
		<script src="<c:url value="/lib/ckeditorsimples/adapters/jquery.js"/>"></script>
		
		<script src="<c:url value="/javascripts/externos/cpfcnpj.js"/>"></script>
		<script src="<c:url value="/javascripts/uteis.js"/>" charset="UTF-8"></script>
    </head>
    
<body <c:if test="${modo!=null && modo.equals(formcons.getModoJanela())}">onUnload="atualizaJanelaPrincipal()" </c:if>>

<c:if test="${modo==null}">    
<%@ include file="navbar.jsp" %>
</c:if>	


<!-- mensagens de erro -->
<c:if test="${!empty errors && !errors.get(0).message.equals('Invalid upload')}">

<div class="alert alert-danger">
	<c:forEach var="error" items="${errors}">
	
	<c:if test="${error.message.startsWith('{')}">
		<c:set var="msg" value="${error.message.replace('{','').replace('}','').trim()}"></c:set>
	  		<p style="color:red;"> <fmt:message key="${msg}"></fmt:message></p>
	 	</c:if>
	 	
	  <c:if test="${!error.message.startsWith('{')}">
	  	<p style="color:red;"> ${error.message}</p>
	  </c:if>
	</c:forEach>
</div> 

</c:if>	

<c:if test="${!empty msgsucesso}">
	<div class="alert alert-info">
		<fmt:message key="${msgsucesso}"/>
	</div>
</c:if>	

<div class="bb-alert alert alert-info" style="display:none;">
    <span id="spanconf"></span>
</div>
	
<div class="container">

</c:if>