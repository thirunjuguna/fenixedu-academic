/*
 * Created on 18/Jun/2004
 *
 */
package net.sourceforge.fenixedu.dataTransferObject;

import java.util.List;

import net.sourceforge.fenixedu.domain.ILesson;
import net.sourceforge.fenixedu.domain.IShift;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

/**
 * @author T�nia Pous�o 18/Jun/2004
 */
public class InfoShiftWithInfoLessons extends InfoShift {
    public void copyFromDomain(IShift shift) {
        super.copyFromDomain(shift);
        if (shift != null) {
            setInfoLessons(copyILessons2InfoLessons(shift.getAssociatedLessons()));
        }
    }

    public static InfoShift newInfoFromDomain(IShift shift) {
        InfoShiftWithInfoLessons infoShift = null;
        if (shift != null) {
            infoShift = new InfoShiftWithInfoLessons();
            infoShift.copyFromDomain(shift);
        }
        return infoShift;
    }

    private static List copyILessons2InfoLessons(List list) {
        List infoLessons = null;
        if (list != null) {
            infoLessons = (List) CollectionUtils.collect(list, new Transformer() {

                public Object transform(Object arg0) {

                    return InfoLessonWithInfoRoom.newInfoFromDomain((ILesson) arg0);
                }

            });
        }
        return infoLessons;
    }
}