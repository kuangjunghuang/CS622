// Initiating class (main())

package grading;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainKGrade4 {
	
	public static final String STANDARD_UNGRADED_STUDENT_PAPERS = "student-ungraded-repository.dat";
	public static final String STANDARD_GRADED_STUDENT_PAPERS = "student-graded-repository.dat";
	
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
	
	public static void getStudentAnswers() {
	// Postcondition 1 (Prompted): User was prompted repeatedly for student names or "N" (no more)
	// Post2 (Stored): StudentPaper objects, including names and answers, are in 
	// STANDARD_STUDENT_ANSWER_REPOSITORY
		
		// For Post1 (Prompted)
		Scanner reader = new Scanner(System.in);	
		System.out.print("enter 'N' if no more students--otherwise, student's name: ");
		String name = reader.nextLine();	
		System.out.println("You input: " + name);

        try (ObjectOutputStream studentFile = new ObjectOutputStream
        		(new FileOutputStream(STANDARD_UNGRADED_STUDENT_PAPERS));)       
        {			
        	while (!name.matches("N")) {			
        		// For Post2 (Stored)
        		StudentPaper studentPaper = new StudentPaper(name);	  
        		studentPaper.getAndSetStudentAnswer(reader);
        		studentFile.writeObject(studentPaper);  
        		// For Post1 (Prompted)
        		System.out.print("Enter N if no more students--otherwise, student's name: ");
         		name = reader.nextLine();
        		System.out.println("You input: " + name);
        	}		
        	reader.close();
        }
        catch(Exception e) {
	         System.out.println("Unable to write in MainKGrade4.getStudentAnswers");
	         e.printStackTrace();            	
        }
	}
	
	public static void gradeStudentAnswers() {
	/*
	 * Precondition: StudentPaper objects, including names and answers, are in
	 * STANDARD_UNGRADED_STUDENT_PAPERS
	 * Postcondition 1: StudentPaper objects, including paperGrade corresponding to 
	 * gradeWithAllRubrics(), are in STANDARD_GRADED_STUDENT_PAPERS
	 * Post2 (Acknowledged): Grading was acknowledged for each paper, by name.
	 */

	     try
	     {
	         try (ObjectInputStream infile = new ObjectInputStream
	        		 (new FileInputStream(STANDARD_UNGRADED_STUDENT_PAPERS));)       
	         {
		         try (ObjectOutputStream studentFile = new ObjectOutputStream
		         		(new FileOutputStream(STANDARD_GRADED_STUDENT_PAPERS));)  
		         {	        	 
		        	 while (true)
		        	 {
	                      StudentPaper studentPaper = (StudentPaper)(infile.readObject());
	                      gradeWithAllRubrics(studentPaper);
	                      studentFile.writeObject(studentPaper);  
		        	 }	
		         }
	         }
	     }
	     catch (EOFException ex)
	     {
	         System.out.println("EOF reached in " + STANDARD_UNGRADED_STUDENT_PAPERS);    
	     }

	     catch (FileNotFoundException ex)
	     {
	         System.out.println("FileNotFoundException"); 
	         ex.printStackTrace();   
	     }

	     catch (IOException ex)
	     {
	         System.out.println("IOException");
	         ex.printStackTrace();    
	     }

	     catch (ClassNotFoundException ex)
	     {
	         System.out.println("ClassNotFoundException");
	         ex.printStackTrace();    
	     }
	     finally
	     {
	    	 System.out.println
	    	 		("Grading complete; records stored as StudentPaper objects in " + 
	    	 		STANDARD_GRADED_STUDENT_PAPERS);
	     }
	}
	
	public static double getTotalPoints() {
	// Precondition: ConsecutiveRubrics.totalConsecutiveScore + OrderRubrics.totalOrderScore
	// have been computed.
		
		return ConsecutiveRubrics.totalConsecutiveScore + OrderRubrics.totalOrderScore;
	}

	public static void gradeWithAllRubrics(StudentPaper aStudentPaper) {
	/*
	 * Precondition: aStudentPaper has fulfilled theName and studentAnswer fields
	 * 
	 * Postcondition 1: aStudentPaper.paperScore and .feedback corresponds to studentAnswer and the rubrics 
	 */	
		
		// Post3 Consecutive Scoring
		Grading<ConsecutiveRubrics> gradingConsecutiveRubrics = new Grading<ConsecutiveRubrics>();
		gradingConsecutiveRubrics.setTheRubricSet(new ConsecutiveRubrics());
		gradingConsecutiveRubrics.grade(aStudentPaper);
		
		// Order Scoring
		Grading<OrderRubrics> gradingOrderRubrics = new Grading<OrderRubrics>();
		gradingOrderRubrics.setTheRubricSet(new OrderRubrics());
		gradingOrderRubrics.grade(aStudentPaper);
	}

	public static void main(String[] args) {
	/*
	Intent: Initiating method for this application
	Postcondition 1: STANDARD_GRADED_STUDENT_PAPERS consists of student name/answers
	that have been entered.
	Post2: STANDARD_UNGRADED_STUDENT_PAPERS consists of student name/answers
	that have been entered.
	Post3: As per MainKGrade4.reportOnAllStudents()
	*/	
		eraseContentsOf(STANDARD_GRADED_STUDENT_PAPERS);  // Post1
		eraseContentsOf(STANDARD_UNGRADED_STUDENT_PAPERS);  // Post2 
		// Post3
		getStudentAnswers();
		gradeStudentAnswers();
		System.out.println("======THE TOTAL NUMBER OF POINTS IS======>" + getTotalPoints());
		MainKGrade4.reportOnAllStudents();  // Post4
	}
	
	public static void reportOnAllStudents() {
		/*
		 * Precondition: StudentPaper objects, including names, answers, grades, and feedback are in
		 * STANDARD_UNGRADED_STUDENT_PAPERS
		 * Postcondition: As per StudentPaper.showStudentPaper() for all in STANDARD_UNGRADED_STUDENT_PAPERS
		 */

		try (ObjectInputStream studentFile = new ObjectInputStream
				(new FileInputStream(STANDARD_GRADED_STUDENT_PAPERS));)  
		{	        	 
			while (true)
			{
				StudentPaper studentPaper = (StudentPaper)(studentFile.readObject());
						studentPaper.showStudentPaper();
			}	
		}
		catch (EOFException ex)
		{
			System.out.println("EOF reached in " + STANDARD_UNGRADED_STUDENT_PAPERS);    
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("FileNotFoundException"); 
			ex.printStackTrace();   
		}
		catch (IOException ex)
		{
			System.out.println("IOException");
			ex.printStackTrace();    
		}
		catch (ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException");
			ex.printStackTrace();    
		}
		finally
		{
			System.out.println("\n==========ALL STUDENTS REPORTED===========");
		}
	}
}
