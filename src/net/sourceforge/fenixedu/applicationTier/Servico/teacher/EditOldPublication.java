/*
 * Created on 13/Nov/2003
 *  
 */
package net.sourceforge.fenixedu.applicationTier.Servico.teacher;

import net.sourceforge.fenixedu.dataTransferObject.InfoObject;
import net.sourceforge.fenixedu.dataTransferObject.teacher.InfoOldPublication;
import net.sourceforge.fenixedu.dataTransferObject.util.Cloner;
import net.sourceforge.fenixedu.domain.IDomainObject;
import net.sourceforge.fenixedu.domain.teacher.IOldPublication;
import net.sourceforge.fenixedu.applicationTier.Servico.framework.EditDomainObjectService;
import net.sourceforge.fenixedu.persistenceTier.IPersistentObject;
import net.sourceforge.fenixedu.persistenceTier.ISuportePersistente;
import net.sourceforge.fenixedu.persistenceTier.teacher.IPersistentOldPublication;

/**
 * @author Leonor Almeida
 * @author Sergio Montelobo
 *  
 */
public class EditOldPublication extends EditDomainObjectService {

    private static EditOldPublication service = new EditOldPublication();

    public static EditOldPublication getService() {
        return service;
    }

    /**
     *  
     */
    private EditOldPublication() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see ServidorAplicacao.IServico#getNome()
     */
    public String getNome() {
        return "EditOldPublication";
    }

    protected IPersistentObject getIPersistentObject(ISuportePersistente sp) {
        IPersistentOldPublication persistentOldPublication = sp.getIPersistentOldPublication();
        return persistentOldPublication;
    }

    protected IDomainObject clone2DomainObject(InfoObject infoObject) {
        IOldPublication oldPublication = Cloner
                .copyInfoOldPublication2IOldPublication((InfoOldPublication) infoObject);
        return oldPublication;
    }
}