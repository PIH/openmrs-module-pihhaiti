<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/headerMinimal.jsp" %>
<openmrs:htmlInclude file="/moduleResources/pihhaiti/pihhaiti.css"/>
<openmrs:authentication>
	<c:choose>
		<c:when test="${authenticatedUser == null}">
			<center style="padding-top:100px;">
				<img height="100px" src="${pageContext.request.contextPath}/moduleResources/pihhaiti/images/logos/PIH_ZL_plum.jpg">
				<br/>
				<br/>
				<h3 class="pihPlum">Electronic Medical Records System</h3>
				<openmrs:portlet url="login" />
				<script type="text/javascript">
					document.forms[0].elements["refererURL"].value = '';
				</script>
			</center>
			<br/>
		</c:when>
		<c:otherwise>
			<c:redirect url="/in.htm"/>
		</c:otherwise>
	</c:choose>
</openmrs:authentication>
<%@ include file="/WEB-INF/template/footer.jsp" %> 

