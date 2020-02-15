package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class GradingTest{
	
  	@Test
	void gradeTest() {

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
		
		StudentPaper currentStudent = new StudentPaper("Stanley Sample Jr.");
		currentStudent.studentAnswer = studentAnswer;
		
		gradingOrderRubrics.grade(currentStudent);
		assertEquals(12, currentStudent.paperScore); 
		assertEquals(20, OrderRubrics.totalOrderScore); 

	}
} 
