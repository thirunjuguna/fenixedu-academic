/*
 * CriarSalaServicosTest.java JUnit based test
 * 
 * Created on 24 de Outubro de 2002, 12:00
 */

package net.sourceforge.fenixedu.applicationTier.Servicos.MasterDegree.administrativeOffice.candidate;

/**
 * @author Nuno Nunes & Joana Mota
 */
import junit.framework.Test;
import junit.framework.TestSuite;
import net.sourceforge.fenixedu.dataTransferObject.InfoExecutionDegree;
import net.sourceforge.fenixedu.dataTransferObject.InfoMasterDegreeCandidate;
import net.sourceforge.fenixedu.dataTransferObject.InfoPerson;
import net.sourceforge.fenixedu.dataTransferObject.util.Cloner;
import net.sourceforge.fenixedu.domain.ICurso;
import net.sourceforge.fenixedu.domain.ICursoExecucao;
import net.sourceforge.fenixedu.domain.IDegreeCurricularPlan;
import net.sourceforge.fenixedu.domain.IExecutionYear;
import net.sourceforge.fenixedu.domain.IMasterDegreeCandidate;
import net.sourceforge.fenixedu.domain.IPessoa;
import net.sourceforge.fenixedu.applicationTier.Servicos.TestCaseReadServicesIntranet;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.ISuportePersistente;
import net.sourceforge.fenixedu.persistenceTier.OJB.SuportePersistenteOJB;

public class ReadCandidateListByPersonAndExecutionDegreeTest extends TestCaseReadServicesIntranet {

    public ReadCandidateListByPersonAndExecutionDegreeTest(java.lang.String testName) {
        super(testName);
    }

    public static void main(java.lang.String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ReadCandidateListByPersonAndExecutionDegreeTest.class);

        return suite;
    }

    protected void setUp() {
        super.setUp();
    }

    protected void tearDown() {
        super.tearDown();
    }

    protected String getNameOfServiceToBeTested() {
        return "ReadCandidateListByPersonAndExecutionDegree";
    }

    protected int getNumberOfItemsToRetrieve() {
        return 1;
    }

    protected Object getObjectToCompare() {
        ISuportePersistente sp = null;
        InfoMasterDegreeCandidate infoMasterDegreeCandidate = null;
        try {
            sp = SuportePersistenteOJB.getInstance();
            sp.iniciarTransaccao();

            IExecutionYear executionYear = sp.getIPersistentExecutionYear().readCurrentExecutionYear();
            assertNotNull(executionYear);

            ICurso degree = sp.getICursoPersistente().readBySigla("MEEC");
            assertNotNull(degree);

            IDegreeCurricularPlan degreeCurricularPlan = sp.getIPersistentDegreeCurricularPlan()
                    .readByNameAndDegree("plano2", degree);
            assertNotNull(degreeCurricularPlan);

            ICursoExecucao executionDegree = sp.getIPersistentExecutionDegree()
                    .readByDegreeCurricularPlanAndExecutionYear(degreeCurricularPlan, executionYear);
            assertNotNull(executionDegree);

            IPessoa person = sp.getIPessoaPersistente().lerPessoaPorUsername("nmsn");
            assertNotNull(person);

            IMasterDegreeCandidate masterDegreeCandidate = sp.getIPersistentMasterDegreeCandidate()
                    .readByExecutionDegreeAndPerson(executionDegree, person);
            infoMasterDegreeCandidate = Cloner
                    .copyIMasterDegreeCandidate2InfoMasterDegreCandidate(masterDegreeCandidate);

            sp.confirmarTransaccao();
        } catch (Exception e) {
            fail("Error !");
        }

        return infoMasterDegreeCandidate;
    }

    protected Object[] getArgumentsOfServiceToBeTestedSuccessfuly() {
        ISuportePersistente sp = null;
        InfoPerson infoPerson = null;
        InfoExecutionDegree infoExecutionDegree = null;

        try {
            sp = SuportePersistenteOJB.getInstance();
            sp.iniciarTransaccao();

            IExecutionYear executionYear = sp.getIPersistentExecutionYear().readCurrentExecutionYear();
            assertNotNull(executionYear);

            ICurso degree = sp.getICursoPersistente().readBySigla("MEEC");
            assertNotNull(degree);

            IDegreeCurricularPlan degreeCurricularPlan = sp.getIPersistentDegreeCurricularPlan()
                    .readByNameAndDegree("plano2", degree);
            assertNotNull(degreeCurricularPlan);

            ICursoExecucao executionDegree = sp.getIPersistentExecutionDegree()
                    .readByDegreeCurricularPlanAndExecutionYear(degreeCurricularPlan, executionYear);
            assertNotNull(executionDegree);

            IPessoa person = sp.getIPessoaPersistente().lerPessoaPorUsername("nmsn");
            assertNotNull(person);

            infoExecutionDegree = (InfoExecutionDegree) Cloner.get(executionDegree);
            infoPerson = Cloner.copyIPerson2InfoPerson(person);

            sp.confirmarTransaccao();

        } catch (Exception e) {
            fail("Error !");
        }

        Object[] args = { infoExecutionDegree, infoPerson };

        return args;
    }

    protected Object[] getArgumentsOfServiceToBeTestedUnsuccessfuly() {
        ISuportePersistente sp = null;
        try {
            sp = SuportePersistenteOJB.getInstance();
            sp.iniciarTransaccao();
            //TODO: delete all no longer exists
            //   sp.getIPersistentMasterDegreeCandidate().deleteAll();
            sp.confirmarTransaccao();
        } catch (ExcepcaoPersistencia excepcao) {
            fail("Exception when setUp");
        }
        return null;
    }
}