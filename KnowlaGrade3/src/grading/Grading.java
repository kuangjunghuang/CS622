// Base class for sets of rubrics of one type

package grading;

import java.util.ArrayList;

public class Grading<ARubricSet> {
	
	private ARubricSet theRubricSet;
	
	public RubricSet getTheRubricSet(){
		
		return (RubricSet)this.theRubricSet;
	}
	
	public void setTheRubricSet(ARubricSet aRubricSet){
		
		this.theRubricSet=aRubricSet;
	}
	
	public double[] grade(Student aStudent) {	
		
		ArrayList<Integer> studentAnswer = aStudent.studentAnswer;		
		return getTheRubricSet().apply(studentAnswer);		
	}
}
