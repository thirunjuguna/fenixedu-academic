<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-report.tld" prefix="report"%>
<%@ page import="net.sourceforge.fenixedu.presentationTier.Action.sop.utils.SessionConstants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="<%= request.getContextPath() %>/CSS/dotist.css" rel="stylesheet" media="screen, print" type="text/css" />
<link href="<%= request.getContextPath() + "/CSS/report.css" %>" rel="stylesheet" type="text/css" />
</head>
<body style="background: #fff;">
<table id="bodycontent" width="100%">
	<tr>
		<td><logic:present name="infoReport">
			<logic:notEmpty name="infoReport" property="infoProject">
				<table>
					<tr>
						<td rowspan="7">
							<img height="110" alt="Logo <bean:message key="dot.title" bundle="GLOBAL_RESOURCES"/>" src="<bean:message key="university.logo" bundle="GLOBAL_RESOURCES" arg0="<%= request.getContextPath() %>"/>"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
						<h2><bean:message key="title.revenueReport" /></h2>
						</td>
					</tr>
					<bean:define id="infoProject" name="infoReport" property="infoProject" />

					<tr>
						<td><strong><bean:message key="label.acronym" />:</strong></td>
						<td><bean:write name="infoProject" property="title" /></td>
					</tr>
					<tr>
						<td><strong><bean:message key="label.projectNumber" />:</strong></td>
						<td><bean:write name="infoProject" property="projectIdentification" /></td>
					</tr>
					<tr>
						<td><strong><bean:message key="label.type" />:</strong></td>
						<bean:define id="type" name="infoProject" property="type" />
						<td><bean:write name="type" property="label" />&nbsp;-&nbsp;<bean:write name="type" property="value" /></td>
					</tr>
					<tr>
						<td><strong><bean:message key="label.coordinator" />:</strong></td>
						<td><bean:write name="infoProject" property="coordinatorName" /></td>
					</tr>
					<tr>
						<td><strong><bean:message key="label.date" />:</strong></td>
						<td><report:computeDate /></td>
					</tr>
				</table>
				<br />
				<br />
				<br />
				<logic:notEmpty name="infoReport" property="lines">
					<bean:define id="revenueLines" name="infoReport" property="lines" />

					<table class="report-table">
						<tr>
							<td class="report-hdr"><bean:message key="label.idMov" /></td>
							<td class="report-hdr"><bean:message key="label.financialEntity" /></td>
							<td class="report-hdr"><bean:message key="label.rubric" /></td>
							<td class="report-hdr"><bean:message key="label.date" /></td>
							<td class="report-hdr"><bean:message key="label.description" /></td>
							<td class="report-hdr"><bean:message key="label.value" /></td>
						</tr>
						<logic:iterate id="line" name="revenueLines" indexId="lineIndex">
							<tr>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="center"><bean:write name="line" property="movementId" /></td>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="center"><bean:write name="line" property="financialEntity" /></td>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="center"><bean:write name="line" property="rubric" /></td>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="center"><bean:write name="line" property="date" /></td>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="left"><bean:write name="line" property="description" /></td>
								<td class="<%= "report-td-" + (lineIndex.intValue() % 2) %>" align="right"><report:formatDoubleValue name="line" property="value" /></td>
							</tr>
						</logic:iterate>
						<tr>
							<td class="report-line-total-first" colspan="5"><bean:message key="label.total" /></td>
							<td class="report-line-total"><report:sumColumn id="revenueLines" column="5" /></td>
						</tr>
					</table>
					<br />
					<bean:message key="message.listReport" />
					<br />
					<br />
				</logic:notEmpty>
			</logic:notEmpty>
		</logic:present></td>
	</tr>
</table>
</body>
</html>
