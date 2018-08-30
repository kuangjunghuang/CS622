package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class OrderRubricsTest{
	
  	@Test
	void applyTest() {
		
		// Reset
		RubricSet.eraseContentsOf(RubricSet.STANDARD_FEEDBACK_FILE);

		// Build new studentAnswer		
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));
		studentAnswer.add(new Integer(2));
		studentAnswer.add(new Integer(6));
		studentAnswer.add(new Integer(4));
		studentAnswer.add(new Integer(3));
		studentAnswer.add(new Integer(5));
		
		double[] scores = (new OrderRubrics()).apply(studentAnswer);
		assertEquals(12, scores[0]);
		assertEquals(20, scores[1]);
	}
} 
