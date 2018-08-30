// Tests unsuitable for JUnit

package grading;

import java.util.ArrayList;

public class OrderRubricsTestNoJUnit {
	
	public static void main(String[] args) {
	/* 
	 * For testing without JUnit
	 */		
		//?? appendToFeedbackTest(); 
		doGradingTest();
		getStudentAswerTest();
	}
	
	static void doGradingTest() {
		
		RubricSet.resetFeedback();
		
		ArrayList<Integer> answer = new ArrayList<Integer>();
		answer.add(new Integer(1));
		answer.add(new Integer(2));
		answer.add(new Integer(6));
		answer.add(new Integer(4));
		
		try {
			System.out.println("Expect ... You are correct that #2/1 occurs before #4/2 ...");
			(new OrderRubrics()).apply(answer);
			System.out.println("OrderRubrics.apply() executed in OrderRubricsNoJUnit.doGradingTest()");
			System.out.println("with 1, 2, 6, 4");
		}
		catch(Exception e) {
			System.out.println(e + "Exception in Grading.doGrading()"); 
		    e.printStackTrace(System.out);
		}		
		System.out.println("Check feedback.txt");
	}
	
	static void getStudentAswerTest() {
	// Post1: User expected to enter "4 6"
	// Post2: Grading.studentAswer has the above
		
		System.out.println("Respond with 4 6");
		ArrayList<Integer> answer = (new Student()).getAndSetStudentAswer(); 
		System.out.println("6<--->" + answer.get(1).intValue()); // verification 
	}
/*	
	static void appendToFeedbackTest() {
		
		Grading.appendToFeedback("aaaaaaaaa");
		Grading.appendToFeedback("bbbbbbbbbbbbb");
		System.out.println("Last 2 lines of feedback.txt should be: aaaaaaaaa and bbbbbbbbbbbbb resp");
	}
*/
}
