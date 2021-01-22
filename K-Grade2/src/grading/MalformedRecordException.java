package grading;
// based on *

public class MalformedRecordException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "BAD RUBRIC";

	public void messageToConsole()
	{
		System.out.println(MESSAGE);
	}
}