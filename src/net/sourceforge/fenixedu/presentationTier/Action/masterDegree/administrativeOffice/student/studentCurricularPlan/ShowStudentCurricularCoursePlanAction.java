package net.sourceforge.fenixedu.presentationTier.Action.masterDegree.administrativeOffice.student.studentCurricularPlan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import net.sourceforge.fenixedu.dataTransferObject.InfoStudentCurricularPlan;
import net.sourceforge.fenixedu.applicationTier.Servico.UserView;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.presentationTier.Action.exceptions.FenixActionException;
import net.sourceforge.fenixedu.presentationTier.Action.sop.utils.SessionConstants;
import net.sourceforge.fenixedu.framework.factory.ServiceManagerServiceFactory;

/**
 * @author T�nia Pous�o Created on 6/Out/2003
 */
public class ShowStudentCurricularCoursePlanAction extends DispatchAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        Integer studentCurricularPlanId = null;
        String studentCurricularPlanIdString = request.getParameter("studentCurricularPlanId");
        if (studentCurricularPlanIdString == null) {
            studentCurricularPlanIdString = (String) request.getAttribute("studentCurricularPlanId");
        }
        studentCurricularPlanId = new Integer(studentCurricularPlanIdString);

        UserView userView = (UserView) session.getAttribute(SessionConstants.U_VIEW);

        Object args[] = { studentCurricularPlanId };

        InfoStudentCurricularPlan infoStudentCurricularPlan = null;
        try {
            infoStudentCurricularPlan = (InfoStudentCurricularPlan) ServiceManagerServiceFactory
                    .executeService(userView, "ReadPosGradStudentCurricularPlanById", args);
        } catch (FenixServiceException e) {
            throw new FenixActionException(e);
        }

        request.setAttribute("student", infoStudentCurricularPlan.getInfoStudent());
        request.setAttribute("studentCurricularPlan", infoStudentCurricularPlan);

        return mapping.findForward("ShowStudentCurricularCoursePlan");
    }
}