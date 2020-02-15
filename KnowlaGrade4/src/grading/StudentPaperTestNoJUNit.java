package grading;
 
import java.util.ArrayList; 

class StudentPaperTestNoJUNit{
	
	public static void main(String[] args) {
		
		StudentPaper studentPaper = new StudentPaper("Adam Smith:");
		ArrayList<String> feedbacks = new ArrayList<String>();
		feedbacks.add("aaa");
		feedbacks.add("bbb");
		studentPaper.theFeedback = feedbacks;
		studentPaper.showStudentPaper();
	}
} 
