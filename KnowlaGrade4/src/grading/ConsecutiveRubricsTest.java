package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ConsecutiveRubricsTest{
	
/*
Assumes consecutive-rubrics.txt as follows: 
 
1 2 7 You are correct that #1 and #2 are consecutive.
5 6 2 You are correct that #5 and #6 are consecutive.
3 4 8 You are correct that #3 and #4 are consecutive.
 */

  	@Test
	void applyToTest() {
		
		// Build new studentAnswer		
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));
		studentAnswer.add(new Integer(2));
		studentAnswer.add(new Integer(6));
		studentAnswer.add(new Integer(3));
		studentAnswer.add(new Integer(4));
		studentAnswer.add(new Integer(5));
		
		StudentPaper studentPaper = new StudentPaper("Sample Student");
		studentPaper.studentAnswer = studentAnswer;
		
		(new ConsecutiveRubrics()).applyTo(studentPaper);
		assertEquals(15, studentPaper.paperScore);
		assertEquals(17, ConsecutiveRubrics.totalConsecutiveScore);
		assertEquals(" You are correct that #1 and #2 are consecutive.", studentPaper.theFeedback.get(0));
		assertEquals(" You are correct that #3 and #4 are consecutive.", studentPaper.theFeedback.get(1));
	}
} 
