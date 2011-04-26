package net.sourceforge.fenixedu.domain.phd.candidacy;

import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.period.CandidacyPeriod;

import org.joda.time.DateTime;

import pt.ist.fenixWebFramework.services.Service;

public class EPFLPhdCandidacyPeriod extends EPFLPhdCandidacyPeriod_Base {

    protected EPFLPhdCandidacyPeriod() {
	super();
    }

    protected EPFLPhdCandidacyPeriod(final ExecutionYear executionYear, final DateTime start, final DateTime end,
	    PhdCandidacyPeriodType type) {
	this();

	init(executionYear, start, end, type);
    }

    @Override
    protected void init(final ExecutionYear executionYear, final DateTime start, final DateTime end, PhdCandidacyPeriodType type) {
	checkIfCanCreate(start, end);

	if (!PhdCandidacyPeriodType.EPFL.equals(type)) {
	    throw new DomainException("error.EPFLPhdCandidacyPeriod.type.must.be.epfl");
	}

	super.init(executionYear, start, end, type);
    }

    private void checkIfCanCreate(final DateTime start, final DateTime end) {
	for (final CandidacyPeriod period : RootDomainObject.getInstance().getCandidacyPeriods()) {
	    if (!period.equals(this) && period.isEpflCandidacyPeriod() && period.intercept(start, end)) {
		throw new DomainException(
			"error.EPFLInstitutionPhdCandidacyPeriod.already.contains.candidacyPeriod.in.given.dates");
	    }
	}
    }

    @Override
    public boolean isEpflCandidacyPeriod() {
	return true;
    }

    @Service
    public static EPFLPhdCandidacyPeriod create(final PhdCandidacyPeriodBean phdCandidacyPeriodBean) {
	final ExecutionYear executionYear = phdCandidacyPeriodBean.getExecutionYear();
	final DateTime start = phdCandidacyPeriodBean.getStart();
	final DateTime end = phdCandidacyPeriodBean.getEnd();
	final PhdCandidacyPeriodType type = phdCandidacyPeriodBean.getType();

	return new EPFLPhdCandidacyPeriod(executionYear, start, end, type);
    }

    public static boolean isAnyEPFLPhdCandidacyPeriodActive() {
	return isAnyEPFLPhdCandidacyPeriodActive(new DateTime());
    }

    public static boolean isAnyEPFLPhdCandidacyPeriodActive(final DateTime date) {
	return readEPFLPhdCandidacyPeriodForDateTime(date) != null;
    }

    public static EPFLPhdCandidacyPeriod readEPFLPhdCandidacyPeriodForDateTime(final DateTime date) {
	for (final CandidacyPeriod period : RootDomainObject.getInstance().getCandidacyPeriods()) {
	    if (period.isEpflCandidacyPeriod() && period.contains(date)) {
		return (EPFLPhdCandidacyPeriod) period;
	    }
	}

	return null;
    }

    static public EPFLPhdCandidacyPeriod getMostRecentCandidacyPeriod() {
	PhdCandidacyPeriod mostRecentCandidacyPeriod = null;

	for (CandidacyPeriod candidacyPeriod : RootDomainObject.getInstance().getCandidacyPeriods()) {
	    if (!candidacyPeriod.isEpflCandidacyPeriod()) {
		continue;
	    }

	    if (candidacyPeriod.getStart().isAfterNow()) {
		continue;
	    }

	    if (mostRecentCandidacyPeriod == null) {
		mostRecentCandidacyPeriod = (PhdCandidacyPeriod) candidacyPeriod;
		continue;
	    }

	    if (candidacyPeriod.getStart().isAfter(mostRecentCandidacyPeriod.getStart())) {
		mostRecentCandidacyPeriod = (PhdCandidacyPeriod) candidacyPeriod;
	    }
	}

	return (EPFLPhdCandidacyPeriod) mostRecentCandidacyPeriod;
    }

}