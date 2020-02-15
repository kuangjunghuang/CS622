// The set of ordering rubrics

package grading;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public final class ConsecutiveRubrics extends RubricSet {
	
	// Standard location of ordering rubrics
	public final static String STANDARD_CONSECUTIVE_RUBRICS = "consecutive-rubrics.txt";
	public static double totalConsecutiveScore = 0;
	
	/* ASSUMPTIONS: 
	 * Each line in STANDARD_CONSECUTIVE_RUBRICS has form <lower number> <higher number> <score> <feedback>, 
	 * interpreted as "<score> points if <lower number> and <higher number> are consecutive in the answer"
	 */
	
	@Override
	public void applyTo(StudentPaper aStudentPaper) {
	// Postcondition 1: aStudentPaper includes paperScore and theFeedback 
	// consistent with studentAnswer and this rubric set
	// Post2: ConsecutiveRubric.totalScore is the highest possible consecutivity score
			
		// Goal 1 (Total): totalScore = total score for all rubrics read from STANDARD_CONSECUTIVE_RUBRICS 
			
		totalConsecutiveScore = 0;  // reset

		// G2 (Feedback): aStudentPaper.theFeedback contains feedback for every rubric read from
		// STANDARD_ORDER_RUBRICS and violated by aStudentPaper

		// AND
		
		// G3 (All read): All rubrics in STANDARD_CONSECUTIVE_RUBRICS have been read
			
		try {		
			
			ArrayList<Integer> studentAnswer = aStudentPaper.studentAnswer;  // shorthand
			Scanner consecutiveRubricsScanner = new Scanner(new File(STANDARD_CONSECUTIVE_RUBRICS));
			int firstFragment = 0, firstFragmentIndex = 0, secondFragment = 0, 
					secondFragmentIndex = 0, consecutiveScore = 0;
			String feedback = "";
			
			while (consecutiveRubricsScanner.hasNext()) { // establishes G3		 
				// Read & parse next order rubric
				firstFragment = Integer.parseInt(consecutiveRubricsScanner.next());
				firstFragmentIndex = // where first occurs in student's solution
						studentAnswer.indexOf(new Integer(firstFragment));
				secondFragment = Integer.parseInt(consecutiveRubricsScanner.next());
				secondFragmentIndex = // where first occurs in student's solution 
						studentAnswer.indexOf(new Integer(secondFragment));
				consecutiveScore = Integer.parseInt(consecutiveRubricsScanner.next());
				feedback = consecutiveRubricsScanner.nextLine(); 
				
				totalConsecutiveScore +=consecutiveScore; // G1 restored	
					
				// Restore G2
				if(firstFragmentIndex >= 0 && 
						(secondFragmentIndex == firstFragmentIndex + 1)) { // both must be present
					aStudentPaper.paperScore += consecutiveScore;  
					aStudentPaper.theFeedback.add(feedback); 
				}
			}
			consecutiveRubricsScanner.close();
		}
		catch(Exception e) {
		    	 e.printStackTrace(); 
		} 
	}
}
