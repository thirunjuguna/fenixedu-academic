/*
 * Created on 19/Ago/2003
 *
 */
package Dominio;

import java.util.Calendar;

import Util.CorrectionAvailability;
import Util.TestType;

/**
 * @author Susana Fernandes
 */
public class DistributedTest extends DomainObject implements IDistributedTest {
	private String title;
	private String testInformation;
	private Calendar beginDate;
	private Calendar endDate;
	private Calendar beginHour;
	private Calendar endHour;
	private TestType testType;
	private CorrectionAvailability correctionAvailability;
	private Boolean studentFeedback;
	private Integer numberOfQuestions;
	private IDisciplinaExecucao executionCourse;
	private Integer keyExecutionCourse;

	public DistributedTest() {
	}

	public DistributedTest(Integer distributedTestId) {
		setIdInternal(distributedTestId);
	}

	public Calendar getBeginDate() {
		return beginDate;
	}

	public Calendar getBeginHour() {
		return beginHour;
	}

	public CorrectionAvailability getCorrectionAvailability() {
		return correctionAvailability;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public Calendar getEndHour() {
		return endHour;
	}

	public Boolean getStudentFeedback() {
		return studentFeedback;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setBeginDate(Calendar calendar) {
		beginDate = calendar;
	}

	public void setBeginHour(Calendar calendar) {
		beginHour = calendar;
	}

	public void setCorrectionAvailability(CorrectionAvailability availability) {
		correctionAvailability = availability;
	}

	public void setEndDate(Calendar calendar) {
		endDate = calendar;
	}

	public void setEndHour(Calendar calendar) {
		endHour = calendar;
	}

	public void setStudentFeedback(Boolean studentFeedback) {
		this.studentFeedback = studentFeedback;
	}

	public void setTestType(TestType type) {
		testType = type;
	}

	public String getTestInformation() {
		return testInformation;
	}

	public void setTestInformation(String string) {
		testInformation = string;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String string) {
		title = string;
	}

	public IDisciplinaExecucao getExecutionCourse() {
		return executionCourse;
	}

	public Integer getKeyExecutionCourse() {
		return keyExecutionCourse;
	}

	public void setExecutionCourse(IDisciplinaExecucao execucao) {
		executionCourse = execucao;
	}

	public void setKeyExecutionCourse(Integer integer) {
		keyExecutionCourse = integer;
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(Integer integer) {
		numberOfQuestions = integer;
	}

}
