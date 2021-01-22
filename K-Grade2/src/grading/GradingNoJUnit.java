package grading;

import java.util.ArrayList;

public class GradingNoJUnit {
	
	static void doGradingTest() {
	// Postcondition 1: feedback.txt is empty
	// Post2: As per Grading.doGrading()
		
		Grading.eraseContentsOf("feedback.txt");
		
		try {
			Grading.doGrading();
		}
		catch(Exception e) {
			System.out.println(e + "Exception in Grading.doGrading()"); 
		    e.printStackTrace(System.out);
		}		
		System.out.println("Check feedback.txt");
	}
	
	static void eraseContentsOfTest() {
		Grading.eraseContentsOf(Grading.STANDARD_FEEDBACK_FILE); 
	}
	
	static void getStudentAswerTest() {
	// Post1: User expected to enter "4 6"
	// Post2: Grading.studentAswer has the above
		
		System.out.println("Option: respond with 4 6");
		ArrayList<Integer> answer = Grading.getStudentAswer(); 
		System.out.println("If 6 was second: 6<--->" + answer.get(1).intValue()); // verification
		Grading.studentAnswer = answer; 
	}
	
	public static void main(String[] args) {
	/* 
	 * For testing without JUnit
	 */		 
		getStudentAswerTest();
		doGradingTest();	 
	}
}
