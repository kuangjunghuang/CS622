// See main() for purpose
package grading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;	

public class Grading { 
	
	// CONSTANTS =================================================
	
	static String STANDARD_FEEDBACK_FILE = "feedback.txt";
	static String STANDARD_ORDER_RUBRIC_FILE = "order-rubrics.txt";
	static String STANDARD_CONSECUTIVE_RUBRIC_FILE = "consecutive-rubrics.txt";
	
	
	// CLASS INVARIANTS ==========================================

	// C1 (studentAnswer): studentAnswer contains the student's answer to 
	// the scrambled exercise--positive integers
	public static ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
	// C2 (theScore): theScore is the sum score from all rubrics applied 
	// (not necessarily 100)  
	public static int theScore = 0;
	// C3 (Feedback): The feedback from all rubrics applied is in local file 
	// STANDARD_FEEDBACK_FILE
	// C4 (Feedback Pointer): The file pointer for STANDARD_FEEDBACK_FILE 
	// points to the first unwritten line
	{
		eraseContentsOf(STANDARD_FEEDBACK_FILE);
	}
	// C4 (theMaxScore): theMaxScore = the maximum score from all rubrics applied.
	public static int theMaxScore = 0;
	// C5 (Rubric Source): The source of rubrics are the "STANDARD_X_FILE"'s 
	

	// METHODS ===================================================
	
	public static void appendToFeedback(String someFeedback) { 
	// Postcondition (Echoed): someFeedback is on the console 
	// (C1 & 2 are unaffected; C3 and C4)

		// Postcondition, C3 and C4:
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(STANDARD_FEEDBACK_FILE, true)); 
					// (append flag)
			bw.write(someFeedback);  
					// C3 (Feedback)
			System.out.println(someFeedback + "<--written to feedback");  
					// Postcondition (Echoed)
			bw.newLine(); // C4 (Feedback Pointer)
			bw.flush();
		} 
		catch (IOException e) {
			System.out.println("Could not append to feedback.txt");
			e.printStackTrace();
		} 
		finally { // (close file)
			if (bw != null) 
				try {
					bw.close();
				} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
	} 
	 
	 public static void applyConsecutiveRubrics(String aConsecutiveGradingFile) 
			 throws IOException, MalformedRecordException{
	 // Postcondition: All the rubrics in aConsecutiveGradingFile have been read 
	 // (and the class invariants have been preserved)

		 // Goal 1: consecutiveRubrics points to the first unread line of
		 // aConsecutiveGradingFile
		 Scanner consecutiveRubrics = new Scanner(new File(aConsecutiveGradingFile));
		 
		 // G2 (All): There are no more rubrics for consecutiveRubrics to read
		 try {
		 while (consecutiveRubrics.hasNext()) { // establishes the postcondition
			 
			 // Read & parse next consecutive rubric
			 int firstFragment = Integer.parseInt(consecutiveRubrics.next());
			 int firstFragmentIndex = // where first occurs in student's solution
					 studentAnswer.indexOf(new Integer(firstFragment));
			 int secondFragment = Integer.parseInt(consecutiveRubrics.next());
			 int secondFragmentIndex = // where first occurs in student's solution 
					 studentAnswer.indexOf(new Integer(secondFragment));
			 int consecutiveScore = Integer.parseInt(consecutiveRubrics.next());
			 String feedback = consecutiveRubrics.nextLine();
			 
			 // Restore C2-4
			 theMaxScore += consecutiveScore; // C4 restored
			 if(firstFragmentIndex >= 0 && 
					 (secondFragmentIndex == firstFragmentIndex + 1)) { 
				 	 // both must be present
				 theScore += consecutiveScore; // C2
				 appendToFeedback(feedback); // C3
			 } // if 
		 } // while
		 } // try
		 catch(Exception e) {
				 throw new MalformedRecordException();
		 }
		 consecutiveRubrics.close();
	     // C1 and C5 are unaffected
	 }
   
	 public static void doGrading() throws IOException, MalformedRecordException {
	 // Precondition: feedback.txt is empty
	 // Postcondition 1: As for orderRubricsApplied(STANDARD_ORDER_RUBRIC_FILE) 	
	 // Post2: As for consecutiveRubricsApplied(STANDARD_CONSECUTIVE_RUBRIC_FILE) 		 
		 
		 applyOrderRubrics(STANDARD_ORDER_RUBRIC_FILE); 
		 // (preserves the class invariants)
		 applyConsecutiveRubrics(STANDARD_CONSECUTIVE_RUBRIC_FILE);
	 }
	 
	 public static void applyOrderRubrics(String anOrderGradingFile) 
			 throws IOException, MalformedRecordException{
	 // Postcondition: All the rubrics in anOrderGradingFile have been read 
	 // (and the class invariants have been preserved) 

		 // Goal 1: orderRubrics points to the first unread line of
		 // aConsecutiveGradingFile
		 Scanner orderRubrics = new Scanner(new File(anOrderGradingFile));
		 
		 // G2 (All): There are no more rubrics for orderRubrics to read
		 try {
		 while (orderRubrics.hasNext()) { // establishes the postcondition
			 
			 // Read & parse next order rubric
			 int firstFragment = Integer.parseInt(orderRubrics.next());
			 int firstFragmentIndex = // where first occurs in student's solution
					 studentAnswer.indexOf(new Integer(firstFragment));
			 int secondFragment = Integer.parseInt(orderRubrics.next());
			 int secondFragmentIndex = // where first occurs in student's solution 
					 studentAnswer.indexOf(new Integer(secondFragment));
			 int orderScore = Integer.parseInt(orderRubrics.next());
			 String feedback = orderRubrics.nextLine();
			 
			 // Restore C2-4
			 theMaxScore += orderScore; // C4 restored
			 if(firstFragmentIndex >= 0 && 
					 (secondFragmentIndex > firstFragmentIndex)) { // both must be present
				 theScore += orderScore; // C2
				 appendToFeedback(feedback); // C3
			 } // if 
		} // while
		} // try
		catch(Exception e) {
				throw new MalformedRecordException();
		}
	     orderRubrics.close();
	     // C1 and C5 are unaffected
	 }
	 
	 public static void eraseContentsOf(String aFileName) {
		 
		 System.out.println(Grading.STANDARD_FEEDBACK_FILE + "...being built from scratch.");
		 PrintWriter writer;
		 try {
			 writer = new PrintWriter(new File(aFileName));
			 writer.print("");
			 writer.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static ArrayList<Integer> getStudentAswer() {
		 // Postcondition: User was prompted for an answer as in 11 4 8 2 3
		 // Returns answerReturn, the answer
		 
		 System.out.print("Please enter student answer as in '11 4 8 2 3': ");
		 Scanner reader = new Scanner(System.in);
		 String answer = reader.nextLine();
		 System.out.println("Student answer is--->" + answer);
		 reader.close();
		 StringTokenizer answerTokens = new StringTokenizer(answer);
		 ArrayList<Integer> answerReturn = new ArrayList<Integer>();
		 while (answerTokens.hasMoreTokens()){
			 answerReturn.add(new Integer(answerTokens.nextToken()));
		 }
		 return answerReturn;
	 }
	 
	 public static void main(String[] args) {
	/* 
	 * Precondition 1: order-rubrics.txt contains the desired order rubrics
	 * Pre2: consecutive-rubrics.txt contains the desired consecutive rubrics
	 * 
	 * Postcondition 1 (Prompt): As for getStudentAswer()
	 * Post2 (Order): K-Grade applied order-rubrics.txt
	 * Post3 (Consecutive): K-Grade applied consecutive-rubrics.txt
	 * Post4 (Score): The score percentage is on the monitor
	 * Post5 (Feedback): The feedback is on the monitor 
	 */				
		 ArrayList<Integer> studentAnswer = getStudentAswer();
		 System.out.println(studentAnswer);  // echo
		 try {
			 doGrading();
		 }
		 catch(Exception e) {
			 System.out.println(e);
		 }	
	}
 } 