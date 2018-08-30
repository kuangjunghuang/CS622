// Base class for sets of rubrics of the same type

package grading;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class RubricSet {
// A set of one type of rubric, applicable to scrambling solutions
	
	static String STANDARD_FEEDBACK_FILE = "feedback.txt";
	
	// METHODS =============================================
	
	public static void appendToFeedback(String someFeedback) { 
		// Postcondition 1: someFeedback is in local file STANDARD_FEEDBACK_FILE
		// Post2 (Echoed): A report on this appendage is on the console
		// Post3 (Pointer): The file pointer for STANDARD_FEEDBACK_FILE points to 
		// the first unwritten line, at the end
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(STANDARD_FEEDBACK_FILE, true)); // (append flag)
			bw.write(someFeedback);  // Post1
			System.out.println(someFeedback + "<--written to feedback.txt");  // Post2
			bw.newLine(); // Post3
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
	
	public abstract double[] apply(ArrayList<Integer> aStudentAnswer);
	// STANDARD_FEEDBACK_FILE contains feedback for every rubric violated in this set
	// Returns: [score from this rubric set, total possible points from this rubric set]
	

	static void resetFeedback() {
		eraseContentsOf(STANDARD_FEEDBACK_FILE); 
	}

	public static void eraseContentsOf(String aFileName) {
		 
		 System.out.println(STANDARD_FEEDBACK_FILE + "...being built from scratch.");
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
}
