// The set of ordering rubrics

package grading;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public final class ConsecutiveRubrics extends RubricSet {
	
	// Standard location of ordering rubrics
	public final static String STANDARD_CONSECUTIVE_RUBRICS = "consecutive-rubrics.txt";
	
	/* ASSUMPTIONS: 
	 * 1. There is a line of feedback text for every rubric.
	 * 2. Each line consists of <lower number> <higher number> <score> <feedback>, 
	 * interpreted as "<score> points if <lower number> and <higher number> are consecutive in the answer"
	 */

	@Override
	public double[] apply(ArrayList<Integer> aStudentAnswer) {
	// Postcondition: STANDARD_FEEDBACK_FILE contains feedback for every rubric in this set 
	// violated by aStudentAnswer
	// Returns: [score from all (consecutive) rubrics in STANDARD_CONSECUTIVE_RUBRICS applied to aStudentAnswer, 
	// total possible points from this rubric set]
		
		// Goal 1 (Total): totalScore = total score for all rubrics read from STANDARD_ORDER_RUBRICS 
		
		double totalScore = 0;
		
		// G2 (This Score): scoreForAStudentAnswer = score from applying to aStudentAnswer all
		// rubrics read from STANDARD_CONSECUTIVE_RUBRICS 
		
		double scoreForAStudentAnswer = 0;

		// G3 (Feedback): STANDARD_FEEDBACK_FILE contains feedback for every rubric read from
		// STANDARD_ORDER_RUBRICS and violated by aStudentAnswer
	
		// G4 (All read): All rubrics in STANDARD_CONSECUTIVE_RUBRICS have been read
		
		try {		
			Scanner consecutiveRubrics = new Scanner(new File(STANDARD_CONSECUTIVE_RUBRICS));
			int firstFragment = 0, firstFragmentIndex = 0, secondFragment = 0, 
					secondFragmentIndex = 0, consecutiveScore = 0;
			while (consecutiveRubrics.hasNext()) { // establishes G4		 
				// Read & parse next order rubric
				firstFragment = Integer.parseInt(consecutiveRubrics.next());
				firstFragmentIndex = // where first occurs in student's solution
							aStudentAnswer.indexOf(new Integer(firstFragment));
				secondFragment = Integer.parseInt(consecutiveRubrics.next());
				secondFragmentIndex = // where first occurs in student's solution 
							aStudentAnswer.indexOf(new Integer(secondFragment));
				consecutiveScore = Integer.parseInt(consecutiveRubrics.next());
				String feedback = consecutiveRubrics.nextLine(); 
				
				totalScore += consecutiveScore; // G1 restored	
				
				// Restore G2 and G3
				if(firstFragmentIndex >= 0 && 
						(secondFragmentIndex == firstFragmentIndex + 1)) { // both must be present
					scoreForAStudentAnswer += consecutiveScore; // C2
					appendToFeedback(feedback); // C3  
				}
			}
			consecutiveRubrics.close();
		}
		catch(Exception e) {
	    	 	e.printStackTrace(); 
		} 
		
		double[] returnScores = {scoreForAStudentAnswer, totalScore};
		return returnScores;
		}
	}
