package grading;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ConsecutiveRubricsTest{
	
  	@Test
	void applyTest() {
		
		// Reset
		RubricSet.eraseContentsOf(RubricSet.STANDARD_FEEDBACK_FILE);

		// Build new studentAnswer		
		ArrayList<Integer> studentAnswer = new ArrayList<Integer>();
		studentAnswer.add(new Integer(1));
		studentAnswer.add(new Integer(2));
		studentAnswer.add(new Integer(6));
		studentAnswer.add(new Integer(3));
		studentAnswer.add(new Integer(4));
		studentAnswer.add(new Integer(5));
		
		double[] scores = (new ConsecutiveRubrics()).apply(studentAnswer);
		assertEquals(15, scores[0]);
		assertEquals(17, scores[1]);
	}
} 
