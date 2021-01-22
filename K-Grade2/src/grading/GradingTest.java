package grading;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class GradingTest{
	
  	@Test
	public void applyConsecutiveRubricsTest() {
		
		// Reset
		Grading.eraseContentsOf(Grading.STANDARD_FEEDBACK_FILE);
		Grading.theScore = 0; // reset
		Grading.theMaxScore = 0;

		// Build new studentAnswer		
		Grading.studentAnswer = new ArrayList<Integer>();
		Grading.studentAnswer.add(new Integer(1));
		Grading.studentAnswer.add(new Integer(2));
		Grading.studentAnswer.add(new Integer(4));
		Grading.studentAnswer.add(new Integer(3));
		Grading.studentAnswer.add(new Integer(5));
		Grading.studentAnswer.add(new Integer(6));
		
		try { 
			Grading.applyOrderRubrics("consecutive-rubrics.txt"); 
		}
		catch(IOException anException) {
			System.out.println(anException);
		}
		catch(MalformedRecordException m) { 
			m.messageToConsole();
		}
		assertEquals(9, Grading.theScore);
		assertEquals(17, Grading.theMaxScore);
	}
	
  	@Test
  	public void applyOrderRubricsTest() {
		
		// Reset
		Grading.eraseContentsOf(Grading.STANDARD_FEEDBACK_FILE);
		Grading.theScore = 0; // reset
		Grading.theMaxScore = 0;

		// Build new studentAnswer		
		Grading.studentAnswer = new ArrayList<Integer>();
		Grading.studentAnswer.add(new Integer(1));
		Grading.studentAnswer.add(new Integer(2));
		Grading.studentAnswer.add(new Integer(4));
		Grading.studentAnswer.add(new Integer(3));
		Grading.studentAnswer.add(new Integer(5));
		Grading.studentAnswer.add(new Integer(6));
		
		try { 
			Grading.applyOrderRubrics("order-rubrics-test-1.txt"); 
		}
		catch(IOException anException) {
			System.out.println(anException);
		}
		catch(MalformedRecordException m) {
			m.messageToConsole();
		}
		assertEquals(14, Grading.theScore);
		assertEquals(19, Grading.theMaxScore);
	}
 
	@Test
	public void doGradingTest() {

		// Reset
		Grading.eraseContentsOf(Grading.STANDARD_FEEDBACK_FILE);
		Grading.theScore = 0; // reset
		Grading.theMaxScore = 0;
		
		// Build new studentAnswer
		Grading.studentAnswer = new ArrayList<Integer>();
		Grading.studentAnswer.add(new Integer(1));
		Grading.studentAnswer.add(new Integer(2));
		Grading.studentAnswer.add(new Integer(4));
		Grading.studentAnswer.add(new Integer(6));
		try {
			Grading.doGrading();
		}
		catch(IOException anException) {
			System.out.println(anException);
		}
		catch(MalformedRecordException malException) {
			malException.messageToConsole();
		}
		assertEquals(27, Grading.theScore);
		assertEquals(37, Grading.theMaxScore);
	}
} 
