package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class GradingTest{
	
  	@Test
	void gradeTest() {
		
		// Reset
		RubricSet.eraseContentsOf(RubricSet.STANDARD_FEEDBACK_FILE);

		// Build new studentAnswer		
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));
		studentAnswer.add(new Integer(2));
		studentAnswer.add(new Integer(6));
		studentAnswer.add(new Integer(4));
		studentAnswer.add(new Integer(3));
		studentAnswer.add(new Integer(5));
		
		Grading<OrderRubrics> gradingOrderRubrics = new Grading<OrderRubrics>();
		gradingOrderRubrics.setTheRubricSet(new OrderRubrics());
		
		Student currentStudent = new Student("Stanley Sample Jr.");
		currentStudent.studentAnswer = studentAnswer;
		
		double[] scores = gradingOrderRubrics.grade(currentStudent);
		assertEquals(12, scores[0]);
		assertEquals(20, scores[1]);
		 
		// Use getStudentAnswer()
		System.out.println("Enter 1 2  (Source: GradingTest.gradeTest())");
		currentStudent.getAndSetStudentAswer();
		double[] scores1 = gradingOrderRubrics.grade(currentStudent);
		assertEquals(7, scores1[0]);
		assertEquals(20, scores1[1]);
	}
} 
