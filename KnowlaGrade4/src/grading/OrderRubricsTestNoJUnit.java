// Tests unsuitable for JUnit

package grading;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderRubricsTestNoJUnit {
	
	public static void main(String[] args) {
	/* 
	 * For testing without JUnit
	 */		
		getStudentAswerTest();
		applyToTest();
	}
	
	static void getStudentAswerTest() {
	// Post1: User expected to enter "4 6"
	// Post2: Grading.studentAswer has the above
		
		System.out.println("Respond with 4 6");
		Scanner reader = new Scanner(System.in);
		ArrayList<Integer> answer = (new StudentPaper()).getAndSetStudentAnswer(reader); 
		reader.close();
		System.out.println("6<--->" + answer.get(1).intValue()); // verification 
	}

	static void applyToTest() {
		
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
		System.out.println("Expect Sample Student 126345 with feedback");
		studentPaper.showStudentPaper();
	}
}
