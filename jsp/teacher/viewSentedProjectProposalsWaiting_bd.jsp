<%@ page language="java" %>

<%@ page import="java.lang.String" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="net.sourceforge.fenixedu.dataTransferObject.InfoGroupProperties"%>
<%@ page import="net.sourceforge.fenixedu.dataTransferObject.InfoShift"%>


<logic:present name="siteView" property="component">
	<bean:define id="component" name="siteView" property="component" />


<logic:empty name="component" property="infoGroupPropertiesList">
	<br/>
	<br/>
	<h2><bean:message key="message.infoSentedProjectsProposalsWaitingList.not.available" /></h2>
	<br/>
	<br/>	
	<html:link page="<%="/viewExecutionCourseProjects.do?method=prepareViewExecutionCourseProjects&amp;objectCode=" + pageContext.findAttribute("objectCode")%>">
    	<bean:message key="link.backToProjectsAndLink"/></html:link><br/>
	<br/>
</logic:empty>

	
<logic:notEmpty name="component" property="infoGroupPropertiesList"> 
 
	<h2><bean:message key="title.SentedProjectProposalsWaiting"/></h2>
	
	<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="infoop">
			<bean:message key="label.teacher.SentedProjectProposalsWaiting.description" />
		</td>
	</tr>
	</table>
	<br/>

	<span class="error"><html:errors/></span> 	
	
<br/>
	<html:link page="<%="/viewExecutionCourseProjects.do?method=prepareViewExecutionCourseProjects&amp;objectCode=" + pageContext.findAttribute("objectCode")%>">
    	<bean:message key="link.backToProjectsAndLink"/></html:link>
<br/>
<br/>
<table width="100%" border="0" style="text-align: left;">
        <tbody>
    	 <tr>
			<td class="listClasses-header" width="30%" ><bean:message key="label.projectName" />
			</td>
			<td class="listClasses-header" width="40%" ><bean:message key="label.SentedProjectProposalsWaitingExecutionCourses" />
			</td>
			<td class="listClasses-header" width="30%" ><bean:message key="label.SentedProjectProposalsWaitingExecutionCoursesOption" />
			</td>
		</tr>
	<logic:iterate id="infoGroupProperties" name="component" property="infoGroupPropertiesList" >
		<bean:define id="groupPropertiesCode" name="infoGroupProperties" property="idInternal"/>
		<logic:iterate id="infoGroupPropertiesExecutionCourseElement" name="infoGroupProperties" property="infoGroupPropertiesExecutionCourse">              	             	                		
	       <bean:define id="infoExecutionCourse" name="infoGroupPropertiesExecutionCourseElement" property="infoExecutionCourse" />
	       <bean:define id="executionCourseCode" name="infoExecutionCourse" property="idInternal"/>
			<tr>
				
				<td class="listClasses" align="left">
					<b><bean:write name="infoGroupProperties" property="name"/></b>
                </td>
                    
				<td class="listClasses" align="left">
					<bean:define id="executionCourseCode" name="infoExecutionCourse" property="idInternal"/>
    			  	<bean:write name="infoExecutionCourse" property="nome"/>
    			</td>
		
				<td class="listClasses" align="left">
					<html:link page="<%="/deleteProjectProposal.do?method=deleteProjectProposal&amp;objectCode=" + pageContext.findAttribute("objectCode") + "&amp;groupPropertiesCode=" + groupPropertiesCode.toString() + "&amp;executionCourseCode=" + executionCourseCode.toString()%>">
					<bean:message key="link.deleteProjectProposal"/></html:link>
				</td>
			</tr>
			</logic:iterate>	
	        </logic:iterate>						
            </tbody>
</table>
</logic:notEmpty>     
</logic:present>

<logic:notPresent name="siteView" property="component">
<h2>
<bean:message key="message.infoGroupPropertiesList.not.available" />
</h2>
</logic:notPresent>