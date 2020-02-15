package grading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentPaper implements Serializable{
	
	// CONSTANTS ================================================================
	
	public static int MINIMUM_ANSWER = 1;
	public static int MAXIMUM_ANSWER = 10;
	
	// ATTRIBUTES ===============================================================

	public ArrayList<String> theFeedback = new ArrayList<String>();
	public String theName = "Name not assigned yet";
	public ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
	public double paperScore = 0;
	
	// CONSTRUCTORS =============================================================
	
	public StudentPaper() {
	}
	
	public StudentPaper(String aName) {
		
		theName =  aName;
	}
	
	// METHODS ==================================================================
	
	public void compareAnswerWithBounds(int aMinimum, int aMaximum) {
	/* Postcondition: Input (student answers) were screened for aMinimum 
	 * and aMaximum; only numbers between these were accepted. The latter 
	 * were reported to console without breaks.
	 */ 		
		System.out.println("Sorted entries within bounds (not blank-separated): ");
		studentAnswer
	   		.stream()
	   		.filter(w -> (w.intValue() >= aMinimum))
	   		.filter(w -> (w.intValue() <= aMaximum))
	   		.sorted()
	   		.forEach(System.out::print);
		System.out.print("\n");
	}

	public ArrayList<Integer> getAndSetStudentAnswer(Scanner aScanner) {
	// Postcondition 1: User was prompted for an answer as in 11 4 8 2 3
	// Post2: answerReturn was entered AND = studentAnswer
	// Returns: answerReturn
	// Known Shortcoming: No guarantee or check of user input
		 
		 System.out.print("Please enter student answer as in '1 2 3 6 8 4': ");
		 String answer = aScanner.nextLine();
		 System.out.println("Student answer is--->" + answer);
		 StringTokenizer answerTokens = new StringTokenizer(answer);
		 ArrayList<Integer> answerReturn = new ArrayList<Integer>();
		 while (answerTokens.hasMoreTokens()){
			 answerReturn.add(new Integer(answerTokens.nextToken()));
		 }
		 studentAnswer = answerReturn;
		 compareAnswerWithBounds(MINIMUM_ANSWER, MAXIMUM_ANSWER);
		 return answerReturn;
	} 
	
	public void showStudentPaper() {  // (on the console)
				
		String displayString = "\n==>" + theName + ": Answer ..."
				+ studentAnswer + " Grade ..." + paperScore;
		System.out.println(displayString);
		
		System.out.println("Feedback:");
		for(String aFeedback: theFeedback) {
			System.out.println(aFeedback);
		}		
	}
}
