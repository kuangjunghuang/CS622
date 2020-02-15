// The set of ordering rubrics

package grading;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public final class OrderRubrics extends RubricSet {
	
	// Standard location of ordering rubrics
	public final static String STANDARD_ORDER_RUBRICS = "order-rubrics.txt";
	public static double totalOrderScore = 0;
	
	/* ASSUMPTIONS: 
	 * Each line in STANDARD_ORDER_RUBRICS has form <lower number> <higher number> <score> <feedback>, 
	 * interpreted as "<score> points if <lower number> precedes <higher number> in the answer"
	 */

	@Override
	public void applyTo(StudentPaper aStudentPaper) {
	// Postcondition 1: aStudentPaper includes paperScore and theFeedback 
	// consistent with studentAnswer and this rubric set
	// Post2: OrderRubric.totalScore is the highest possible ordering score
			
		// Goal 1 (Total): totalOrderScore = total score for all rubrics read from STANDARD_ORDER_RUBRICS 
			
		totalOrderScore = 0;  // reset

		// G2 (Feedback): aStudentPaper.theFeedback contains feedback for every rubric read from
		// STANDARD_ORDER_RUBRICS and correctly observed by aStudentPaper
		
		// AND
		
		// G3 (All read): All rubrics in STANDARD_ORDER_RUBRICS have been read
			
		try {		
			
			ArrayList<Integer> studentAnswer = aStudentPaper.studentAnswer;  // shorthand
			Scanner orderRubricsScanner = new Scanner(new File(STANDARD_ORDER_RUBRICS));
			int firstFragment = 0, firstFragmentIndex = 0, secondFragment = 0, 
					secondFragmentIndex = 0, orderScore = 0;
			String feedback = "";
			
			while (orderRubricsScanner.hasNext()) { // establishes G3		 
				// Read & parse next order rubric
				firstFragment = Integer.parseInt(orderRubricsScanner.next());
				firstFragmentIndex = // where first occurs in student's solution
						studentAnswer.indexOf(new Integer(firstFragment));
				secondFragment = Integer.parseInt(orderRubricsScanner.next());
				secondFragmentIndex = // where first occurs in student's solution 
						studentAnswer.indexOf(new Integer(secondFragment));
				orderScore = Integer.parseInt(orderRubricsScanner.next());
				feedback = orderRubricsScanner.nextLine(); 
				
				totalOrderScore +=orderScore; // G1 restored	
					
				// Restore G2
				if(firstFragmentIndex >= 0 && 
						(secondFragmentIndex > firstFragmentIndex)) { // both must be present
					aStudentPaper.paperScore += orderScore;  
					aStudentPaper.theFeedback.add(feedback); 
				}
			}
			orderRubricsScanner.close();
		}
		catch(Exception e) {
		    	 e.printStackTrace(); 
		} 
	}
}
