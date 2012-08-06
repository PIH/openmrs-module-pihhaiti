<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patients" otherwise="/login.htm" redirect="/module/pihhaiti/rehab/index.htm" />
<%@ include file="/WEB-INF/template/header.jsp" %>

<table class="rehabIndexTable">
	<tr>
		<td width=60% valign='top'>
			<openmrs:portlet id="findPatient" url="findPatient" parameters="size=full|postURL=../../../patientDashboard.form|showIncludeVoided=false|hideAddNewPatient=true|viewType=shortEdit" />
			<openmrs:hasPrivilege privilege="Add Patients">
				<br/> &nbsp; <spring:message code="general.or"/><br/><br/>
				<openmrs:portlet id="addPersonForm" url="addPersonForm" parameters="personType=patient|postURL=../../../admin/person/addPerson.htm|viewType=shortEdit" />
			</openmrs:hasPrivilege>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
		</td>
		<td valign='top' width="40%">
			<c:if test="${showAnalysis}">
				<b class="boxHeader"><spring:message code="pihhaiti.analysis" text="Analysis"/></b>
				<div class="box">
					<a href="${pageContext.request.contextPath}/cohortBuilder.list">
						<spring:message code="pihhaiti.cohortBuilder" text="Cohort Builder"/>
					</a>
				</div>
			</c:if>
			<br/><br/>
			<c:if test="${!empty reports}">
				<b class="boxHeader"><spring:message code="pihhaiti.reports" text="Reports"/></b>
				<div class="box">
					<c:forEach items="${reports}" var="report">
						<a href="${pageContext.request.contextPath}/module/reporting/run/runReport.form?reportId=${report.uuid}">
							${report.name}<br/>
						</a>
					</c:forEach>				
				</div>
			</c:if>
		</td>
	</tr>
</table>
<br>&nbsp;<br>

<%@ include file="/WEB-INF/template/footer.jsp" %>