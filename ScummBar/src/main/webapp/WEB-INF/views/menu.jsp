<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<div class="menu">
	<spring:message code="menu.titulo" />
	<ul>
		<li><spring:url value="/restaurantes" var="homeUrl"
				htmlEscape="true" /> <a href="${homeUrl}"><spring:message
					code="menu.restaurantes" /></a></li>
		<li><spring:url value="/reservar" var="bookingUrl"
				htmlEscape="true" /> <a href="${bookingUrl}"><spring:message
					code="menu.reservar" /> </a></li>
		<li><spring:url value="/cancelar" var="cancelUrl"
				htmlEscape="true" /> <a href="${cancelUrl}"><spring:message
					code="menu.cancelar" /> </a></li>
		<li><spring:url value="/mostrar" var="mostrarUrl"
				htmlEscape="true" /> <a href="${mostrarUrl }"><spring:message
					code="menu.mostrar" /> </a></li>
		<li><spring:url value="/editar" var="editarUrl" htmlEscape="true" />
			<a href="${editarUrl}"><spring:message code="menu.editar" /> </a></li>
	</ul>
</div>