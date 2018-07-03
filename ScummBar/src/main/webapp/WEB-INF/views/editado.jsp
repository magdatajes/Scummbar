<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div class="alert alert-blockalertsuccess">
				<c:choose>
					<c:when test="${cancelado}">
						<spring:message code="editado.mensaje" />
					</c:when>
					<c:otherwise>
						<spring:message code="editado.otherwise" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>