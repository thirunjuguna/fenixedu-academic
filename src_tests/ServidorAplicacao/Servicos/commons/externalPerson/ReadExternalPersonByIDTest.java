package net.sourceforge.fenixedu.applicationTier.Servicos.commons.externalPerson;

import net.sourceforge.fenixedu.framework.factory.ServiceManagerServiceFactory;
import net.sourceforge.fenixedu.dataTransferObject.InfoExternalPerson;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.NonExistingServiceException;
import net.sourceforge.fenixedu.applicationTier.Servicos.ServiceTestCase;

/**
 * @author - Shezad Anavarali (sana@mega.ist.utl.pt) - Nadir Tarmahomed
 *         (naat@mega.ist.utl.pt)
 */
public class ReadExternalPersonByIDTest extends ServiceTestCase {

    private String dataSetFilePath;

    /**
     * @param testName
     */
    public ReadExternalPersonByIDTest(String testName) {
        super(testName);
        this.dataSetFilePath = "etc/datasets/servicos/commons/externalPerson/testReadExternalPersonByIDDataSet.xml";
    }

    protected void setUp() {
        super.setUp();
    }

    protected String getDataSetFilePath() {
        return this.dataSetFilePath;
    }

    protected String getNameOfServiceToBeTested() {
        return "ReadExternalPersonByID";
    }

    public void testReadExistingExternalPerson() {
        try {
            Integer externalPersonID = new Integer(1);
            Object[] argsReadExternalPerson = { externalPersonID };

            InfoExternalPerson infoExternalPerson = (InfoExternalPerson) ServiceManagerServiceFactory
                    .executeService(null, getNameOfServiceToBeTested(), argsReadExternalPerson);
            assertNotNull(infoExternalPerson);
            assertEquals(infoExternalPerson.getIdInternal(), externalPersonID);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("testReadExistingExternalPerson " + ex.getMessage());
        }

    }

    public void testReadNonExistingExternalPerson() {
        try {
            Integer externalPersonID = new Integer(145);
            Object[] argsReadExternalPerson = { externalPersonID };

            ServiceManagerServiceFactory.executeService(null, getNameOfServiceToBeTested(),
                    argsReadExternalPerson);

            fail("testReadNonExistingExternalPerson did not throw NonExistingServiceException");

        } catch (NonExistingServiceException ee) {
            //ok

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("testReadNonExistingExternalPerson " + ex.getMessage());
        }
    }

}