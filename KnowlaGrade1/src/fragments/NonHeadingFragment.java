package fragments;

//To represent a piece of a passage that's not a heading

public class NonHeadingFragment extends Fragment {
	
	public NonHeadingFragment() {
		super();
	}
	
	public NonHeadingFragment(String aString) {
		super(aString);
	}

	public void display() {
		System.out.println("\t" + text);			
	}	

	public void setText(String someText) {
		text = someText.substring(0, someText.length());
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return "Non-heading fragment";
	}
	
}
