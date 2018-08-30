// Initiating class (main())

package grading;

public class MainKGrade3 {

	public static void gradeAll() {		
	// Postcondition 1 (Answer): currentStudent.theStudentAnswer is the current student's answer  
	// Post2 (Order Score): Student's ordering score/total is on console as per Grading.grade()
	// Post3 (Consecutive Score): Student's consecutive score/total is on console as per Grading.grade()
	// Post4 (Total Score): Student's score/total is on console
		
		// Post1 (Answer)
		Student currentStudent = new Student("Stanley Sample Jr.");  // ??Not used
		currentStudent.getAndSetStudentAswer();
		
		// Post2 (Order Score)
		Grading<OrderRubrics> gradingOrderRubrics = new Grading<OrderRubrics>();
		gradingOrderRubrics.setTheRubricSet(new OrderRubrics());
		double[] orderGrade = gradingOrderRubrics.grade(currentStudent);
		System.out.println("Ordering grade is " + orderGrade[0] + " out of " + orderGrade[1]);
		
		// Post3 (Consecutive Score)
		Grading<ConsecutiveRubrics> gradingConsecutiveRubrics = new Grading<ConsecutiveRubrics>();
		gradingConsecutiveRubrics.setTheRubricSet(new ConsecutiveRubrics());
		double[] consecutiveGrade = gradingConsecutiveRubrics.grade(currentStudent);
		System.out.println
				("Consecutive grade is " + consecutiveGrade[0] + " out of " + consecutiveGrade[1]);
		
		// Post4 (Total Score)
		System.out.println("Overall grade is " + (orderGrade[0] + consecutiveGrade[0]) + 
				" out of " + (orderGrade[1] + consecutiveGrade[1]));
	}

	public static void main(String[] args) {
	// Intent: Initiating method for this application
	// Postcondition 1: RubricSet.STANDARD_FEEDBACK_FILE consists of the feedback from this session
	// Post2: as for gradeAll()
	// Post3: Prompt to check feedback is on console
		
		RubricSet.eraseContentsOf(RubricSet.STANDARD_FEEDBACK_FILE); // Post1
		gradeAll(); // Post2
		System.out.println("Check file " + RubricSet.STANDARD_FEEDBACK_FILE + " for your feedback.");
	}
}
