// Base class for sets of rubrics of the same type

package grading;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class RubricSet {
// A set of one type of rubric, applicable to scrambling solutions
	
	// METHODS =============================================

	public abstract void applyTo(StudentPaper aStudentPaper);
	// Postcondition: aStudentPaper contains paperGrade and theFeedback for studentAnswer


	public static void eraseContentsOf(String aFileName) {
		 
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
