
/**
 *
 * Autores :
 *   - Nuno Nunes (nmsn@rnl.ist.utl.pt)
 *   - Joana Mota (jccm@rnl.ist.utl.pt)
 *
 */

package ServidorAplicacao.Servico.commons.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataBeans.util.Cloner;
import Dominio.IStudentCurricularPlan;
import ServidorAplicacao.IServico;
import ServidorAplicacao.IUserView;
import ServidorAplicacao.Servico.ExcepcaoInexistente;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorAplicacao.Servico.exceptions.NonExistingServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;
import Util.TipoCurso;

public class ReadStudentCurricularPlans implements IServico {
    
    private static ReadStudentCurricularPlans servico = new ReadStudentCurricularPlans();
    
    /**
     * The singleton access method of this class.
     **/
    public static ReadStudentCurricularPlans getService() {
        return servico;
    }
    
    /**
     * The actor of this class.
     **/
    private ReadStudentCurricularPlans() { 
    }
    
    /**
     * Returns The Service Name */
    
    public final String getNome() {
        return "ReadStudentCurricularPlans";
    }
    
    
    public List run(IUserView userView) throws ExcepcaoInexistente, FenixServiceException {
        ISuportePersistente sp = null;
        
		List studentCurricularPlans = null;
         
        try {
            sp = SuportePersistenteOJB.getInstance();
            
            studentCurricularPlans = (List) sp.getIStudentCurricularPlanPersistente().readByUsername(userView.getUtilizador());
          
        } catch (ExcepcaoPersistencia ex) {
            FenixServiceException newEx = new FenixServiceException("Persistence layer error");
            newEx.fillInStackTrace();
            throw newEx;
        } 

		if ((studentCurricularPlans == null) || (studentCurricularPlans.size() == 0)){
			throw new NonExistingServiceException();
		}
		
		Iterator iterator = studentCurricularPlans.iterator();
		List result = new ArrayList();
		
		// FIXME: There's a problem with data of the Graduation Students
		//        For now only Master Degree Students can view their Curriculum 
		
		while(iterator.hasNext()){
			IStudentCurricularPlan studentCurricularPlan = (IStudentCurricularPlan) iterator.next();
			
			if (studentCurricularPlan.getDegreeCurricularPlan().getDegree().getTipoCurso().equals(TipoCurso.MESTRADO_OBJ)){
				result.add(Cloner.copyIStudentCurricularPlan2InfoStudentCurricularPlan(studentCurricularPlan));	
			}
		}

		if ((result.size() == 0)){
			throw new NonExistingServiceException();
		}

		return result;
    }
}