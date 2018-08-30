package grading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Student {

	public String theName = "Name not assigned yet";
	public ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
	
	public Student() {
	}
	
	public Student(String aName) {
		theName =  aName;
	}

	public ArrayList<Integer> getAndSetStudentAswer() {
		 // Postcondition: User was prompted for an answer as in 11 4 8 2 3
		 // Returns: answerReturn, the answer
		 // Known Shortcoming: No guarantee or check of user input
		 
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
		 studentAnswer = answerReturn;
		 return answerReturn;
	 } 
}
