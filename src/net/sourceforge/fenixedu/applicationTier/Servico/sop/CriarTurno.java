/*
 * CriarTurno.java Created on 27 de Outubro de 2002, 18:48
 */

package net.sourceforge.fenixedu.applicationTier.Servico.sop;

/**
 * Servi�o CriarTurno
 * 
 * @author tfc130
 * @author Pedro Santos e Rita Carvalho
 */
import java.util.ArrayList;

import net.sourceforge.fenixedu.dataTransferObject.InfoShift;
import net.sourceforge.fenixedu.dataTransferObject.util.Cloner;
import net.sourceforge.fenixedu.domain.IExecutionCourse;
import net.sourceforge.fenixedu.domain.IExecutionPeriod;
import net.sourceforge.fenixedu.domain.IShift;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.ExistingServiceException;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.IPersistentExecutionCourse;
import net.sourceforge.fenixedu.persistenceTier.ISuportePersistente;
import net.sourceforge.fenixedu.persistenceTier.ITurnoPersistente;
import net.sourceforge.fenixedu.persistenceTier.OJB.SuportePersistenteOJB;
import pt.utl.ist.berserk.logic.serviceManager.IService;

public class CriarTurno implements IService {

    public InfoShift run(InfoShift infoTurno) throws FenixServiceException, ExcepcaoPersistencia {

        final ISuportePersistente sp = SuportePersistenteOJB.getInstance();

        final IShift newShift = Cloner.copyInfoShift2Shift(infoTurno);

        final ITurnoPersistente shiftDAO = sp.getITurnoPersistente();

        final IShift existingShift = shiftDAO.readByNameAndExecutionCourse(newShift.getNome(), newShift
                .getDisciplinaExecucao());

        if (existingShift != null) {
            throw new ExistingServiceException("Duplicate Entry: " + infoTurno.getNome());
        }

        IPersistentExecutionCourse executionCourseDAO = sp.getIPersistentExecutionCourse();

        IExecutionPeriod executionPeriod = Cloner.copyInfoExecutionPeriod2IExecutionPeriod(infoTurno
                .getInfoDisciplinaExecucao().getInfoExecutionPeriod());

        IExecutionCourse executionCourse = executionCourseDAO
                .readByExecutionCourseInitialsAndExecutionPeriod(infoTurno.getInfoDisciplinaExecucao()
                        .getSigla(), executionPeriod);

        sp.getITurnoPersistente().simpleLockWrite(newShift);
        Integer availabilityFinal = new Integer(new Double(Math.ceil(1.10 * newShift.getLotacao()
                .doubleValue())).intValue());
        newShift.setAvailabilityFinal(availabilityFinal);
        newShift.setAssociatedLessons(new ArrayList());
        newShift.setAssociatedClasses(new ArrayList());
        newShift.setAssociatedShiftProfessorship(new ArrayList());

        return Cloner.copyShift2InfoShift(newShift);

    }

}