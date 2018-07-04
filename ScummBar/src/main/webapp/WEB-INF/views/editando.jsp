<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.7.3/themes/base/jquery-ui.css">

<script>
	$(function() {
		$('#dia').datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$('#dia').attr('readonly', true);});
</script>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<form:form action="editando" method="post">
				<div>
					<label for="personas"><spring:message code="reservar.personas"/></label>
<%-- 					<form:input path="personas" type="text"/> --%>
				</div>
<%-- 					<form:select path="personas" htmlEscape="true"> --%>
<%-- 						<c:forEach begin="1" end="10" var="count"> --%>
<%-- 							<form:option value="${count}" /> --%>
<%-- 						</c:forEach> --%>
<%-- 					</form:select> --%>
				</div>
				<div>
					<label for="dia"><spring:message code="cancelar.dia"/></label>
<%-- 					<form:input path="dia" type="text" cssClass="date-picker" /> --%>
				</div>
				<label for="turno"><spring:message code="cancelar.turno"/></label>
				<form:select path="turnoId" items="${command.turnos}"  itemValue="id" itemLabel="descripcion" htmlEscape="true" />
				</div>
				
				<div>
				<button type="submit" value="Editar">
						<spring:message code="editar.submit" />
					</button>
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>