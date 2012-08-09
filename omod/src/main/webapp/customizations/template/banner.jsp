<%@ taglib prefix="pihhaiti" uri="/WEB-INF/view/module/pihhaiti/resources/pihhaiti.tld" %>

<div id="banner">
	<a href="${pageContext.request.contextPath}">
	  <img style="padding:3px; vertical-align:middle;" src="${pageContext.request.contextPath}/moduleResources/pihhaiti/images/logos/PIH_ZL_plum-170x30.jpg" alt="PIH Medical Records System" border="0"/>
	</a>
	<div class="fullBar topBar">
		<a class="boldWhiteLink" href="${pageContext.request.contextPath}"><spring:message code="Navigation.home"/></a>
		<c:set var="app" value='<%= session.getAttribute("pihhaiti_selected_app") %>'/>
		<c:if test="${!empty app}">
			<a class="boldWhiteLink" href="${pageContext.request.contextPath}${app.indexUrl}">
				&nbsp;&nbsp;&gt;&nbsp;&nbsp;<spring:message code="pihhaiti.app.${app.name}"/>
			</a>
		</c:if>
	</div>
</div>
<script type="text/javascript">
	var userBar = document.getElementById("userBar")
	<c:if test="${pihhaiti:currentUserHasRole('System Administrator')}">
		var adminLink = document.createElement('a');
		adminLink.setAttribute('href', '${pageContext.request.contextPath}/admin/index.htm');
		adminLink.appendChild(document.createTextNode('<spring:message code="Navigation.administration"/>'));
		var adminSpan = document.createElement('span');
		adminSpan.appendChild(adminLink);
		userBar.appendChild(adminSpan);
	</c:if>
</script>

