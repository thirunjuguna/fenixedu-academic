<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>
<%@ page import="net.sourceforge.fenixedu.domain.student.RegistrationDataByExecutionYear.EnrolmentModelFactoryEditor" %>
<html:xhtml />

<em><bean:message key="label.academicAdminOffice" bundle="ACADEMIC_OFFICE_RESOURCES"/></em>
<h2><bean:message key="link.student.manageEnrolmentModel" bundle="ACADEMIC_OFFICE_RESOURCES" /></h2>


<p>
	<html:link page="/student.do?method=visualizeRegistration"
		paramId="registrationID" paramName="enrolmentModelBean"
		paramProperty="registration.idInternal">
		<bean:message key="link.student.back"
			bundle="ACADEMIC_OFFICE_RESOURCES" />
	</html:link>
</p>

<bean:define id="registrationId" name="enrolmentModelBean" property="registration.idInternal" />
<fr:form action="<%= "/manageEnrolmentModel.do?method=setEnrolmentModel" %>">
	<fr:edit id="enrolmentModelBean" name="enrolmentModelBean" schema="student.manageEnrolmentModel">
		<fr:layout name="tabular">
			<fr:property name="classes" value="tstyle5 thlight thright" />
			<fr:property name="columnClasses" value=",,tdclear tderror1" />
		</fr:layout>
		
		<fr:destination name="cancel" path="<%= "/student.do?method=visualizeRegistration&registrationID=" + registrationId %>"/>
		<fr:destination name="postback" path="<%= "/manageEnrolmentModel.do?method=postback" %>"/>
	</fr:edit>
	
	<html:submit><bean:message bundle="APPLICATION_RESOURCES" key="button.submit" /></html:submit>
	<html:cancel><bean:message bundle="APPLICATION_RESOURCES" key="button.cancel" /></html:cancel>
</fr:form>
