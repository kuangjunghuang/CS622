package grading;
 
import java.util.ArrayList; 

public class ConsecutiveRubricsTestNoJUnit{
	
/*
Assumes consecutive-rubrics.txt as follows: 
 
1 2 7 You are correct that #1 and #2 are consecutive.
5 6 2 You are correct that #5 and #6 are consecutive.
3 4 8 You are correct that #3 and #4 are consecutive.
 */
	
	public static void main(String[] args) {
		System.out.println("ConsecutiveRubricsTestNoJUnit");
		applyToTest();
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
		
		(new ConsecutiveRubrics()).applyTo(studentPaper);
		System.out.println("Expect Sample Student 126345 with feedback");
		studentPaper.showStudentPaper();
	}
} 
