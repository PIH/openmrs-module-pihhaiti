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
	<c:forEach items="${apps}" var="app" varStatus="loopStatus">
		${loopStatus.index % 4 == 0 ? '<tr>' : ''}
			<td align="center">
				<a href="${pageContext.request.contextPath}/module/pihhaiti/chooseApp.form?appName=${app.name}">
					<input type="button" class="appButton" value="<spring:message code="pihhaiti.app.${app.name}" text="${app.name}"/>"/>
				</a>
			</td>					
		${loopStatus.index % 4 == 3 ? '</tr>' : ''}
		${loopStatus.index % 4 != 3 && loopStatus.last ? '</tr>' : ''}
	</c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %> 