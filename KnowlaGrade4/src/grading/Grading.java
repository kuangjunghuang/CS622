// Base class for sets of rubrics of one type

package grading;

public class Grading<ARubricSet> {
	
	private ARubricSet theRubricSet;
	
	public RubricSet getTheRubricSet(){
		
		return (RubricSet)this.theRubricSet;
	}
	
	public void setTheRubricSet(ARubricSet aRubricSet){
		
		this.theRubricSet=aRubricSet;
	}
	
	public void grade(StudentPaper aStudentPaper) {	
		
		getTheRubricSet().applyTo(aStudentPaper);		
	}
}
