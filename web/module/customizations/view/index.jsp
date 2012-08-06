<%@ include file="/WEB-INF/template/include.jsp" %>
<style>
	.appButton { height:150px; width:150px; color:white; font-weight:bold; background-color:#009384; }
</style>

<%@ include file="/WEB-INF/template/header.jsp" %>

<table border="0" align="center" cellspacing="20">
	<tr>
		<c:choose>
			<c:when test="${!empty apps}">
				<td align="center" colspan="${fn:length(apps)}">
					<spring:message code="pihhaiti.chooseYourApp"/>
				</td>
			</c:when>
			<c:otherwise>
				<td align="center">
					<spring:message code="pihhaiti.noAccessToAnyApps"/>
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<c:forEach items="${apps}" var="app">
			<td align="center">
				<a href="${pageContext.request.contextPath}/module/pihhaiti/chooseApp.form?appName=${app.name}">
					<input type="button" class="appButton" value="<spring:message code="pihhaiti.app.${app.name}" text="${app.name}"/>"/>
				</a>
			</td>					
		</c:forEach>
	</tr>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %> 