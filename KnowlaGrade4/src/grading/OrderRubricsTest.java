package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class OrderRubricsTest{

/*
Assumes that order-rubrics is as follows:
4 6 8 You are correct that #4 occurs before #6.
2 4 5 You are correct that #2 occurs before #4.
1 2 7 You are correct that #1 occurs before #2.
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
		
		(new OrderRubrics()).applyTo(studentPaper);
		assertEquals(12, studentPaper.paperScore);
		assertEquals(20, OrderRubrics.totalOrderScore);
		assertEquals(" You are correct that #2 occurs before #4.", studentPaper.theFeedback.get(0));
		assertEquals(" You are correct that #1 occurs before #2.", studentPaper.theFeedback.get(1));
	}
} 
