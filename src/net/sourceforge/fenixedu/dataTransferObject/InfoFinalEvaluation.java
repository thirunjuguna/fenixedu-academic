package net.sourceforge.fenixedu.dataTransferObject;

import net.sourceforge.fenixedu.domain.IFinalEvaluation;
import net.sourceforge.fenixedu.util.EvaluationType;

/**
 * @author T�nia Pous�o
 *  
 */
public class InfoFinalEvaluation extends InfoEvaluation implements ISiteComponent {

    public void copyFromDomain(IFinalEvaluation finalEvaluation) {
        super.copyFromDomain(finalEvaluation);
        if (finalEvaluation != null) {
            setEvaluationType(EvaluationType.FINAL_TYPE);
        }
    }

    /**
     * @param finalEvaluation
     * @return
     */
    public static InfoFinalEvaluation newInfoFromDomain(IFinalEvaluation finalEvaluation) {
        InfoFinalEvaluation infoFinalEvaluation = null;
        if (finalEvaluation != null) {
            infoFinalEvaluation = new InfoFinalEvaluation();
            infoFinalEvaluation.copyFromDomain(finalEvaluation);
        }
        return infoFinalEvaluation;
    }

}