package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MainKGrade4Test{
	
  	@Test
	void gradeWithAllRubricsTest() {

		// Build new studentAnswer		
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));
		studentAnswer.add(new Integer(2));
		studentAnswer.add(new Integer(6));
		studentAnswer.add(new Integer(4));
		studentAnswer.add(new Integer(3));
		studentAnswer.add(new Integer(5));
		
		StudentPaper currentStudent = new StudentPaper("Stanley Sample Jr.");
		currentStudent.studentAnswer = studentAnswer;
		
		MainKGrade4.gradeWithAllRubrics(currentStudent);
		/* 1 2 6 4 3 5
		 * 2 4 5 You are correct that #2 occurs before #4. Subtotal=5
		 * 1 2 7 You are correct that #1 occurs before #2. Subtotal=12
		 * 1 2 7 You are correct that #1 and #2 are consecutive. Subtotal=19 
		 */
		assertEquals(19, currentStudent.paperScore); 
	}
  	
  	@Test
	void getTotalPointsTest() {	
		/* 
		 * Based on ... 
		 * 4 6 8 You are correct that #4 occurs before #6. Subtotal=8
		 * 2 4 5 You are correct that #2 occurs before #4. Subtotal=13
		 * 1 2 7 You are correct that #1 occurs before #2. Subtotal=20
		 * 
		 * 1 2 7 You are correct that #1 and #2 are consecutive. Subtotal=27 
		 * 5 6 2 You are correct that #5 and #6 are consecutive. Subtotal=29 
		 * 3 4 8 You are correct that #3 and #4 are consecutive.  Subtotal=37 
		 */			
  		// Run at least one student to build totals
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));		
		StudentPaper currentStudent = new StudentPaper("Stanley Sample Jr.");
		currentStudent.studentAnswer = studentAnswer;		
		MainKGrade4.gradeWithAllRubrics(currentStudent);
		
		assertEquals(37, MainKGrade4.getTotalPoints());
  	}
		
} 
