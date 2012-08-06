<%@ taglib prefix="pihhaiti" uri="/WEB-INF/view/module/pihhaiti/resources/pihhaiti.tld" %>
<div class="fullBar topBar">
	<a class="boldWhiteLink" href="${pageContext.request.contextPath}"><spring:message code="Navigation.home"/></a>
	<c:set var="app" value='<%= session.getAttribute("pihhaiti_selected_app") %>'/>
	<c:if test="${!empty app}">
		<a class="boldWhiteLink" href="${pageContext.request.contextPath}${app.indexUrl}">
			&nbsp;&nbsp;&gt;&nbsp;&nbsp;<spring:message code="pihhaiti.app.${app.name}"/>
		</a>
	</c:if>
</div>

<script type="text/javascript">
	var helpElement = document.getElementById("userHelp")
	helpElement.innerHTML = '';
	<c:if test="${pihhaiti:currentUserHasRole('System Administrator')}">
		var adminLink = document.createElement('a');
		adminLink.setAttribute('href', '${pageContext.request.contextPath}/admin/index.htm');
		adminLink.appendChild(document.createTextNode('<spring:message code="Navigation.administration"/>'));
		helpElement.appendChild(adminLink);
	</c:if>
</script>