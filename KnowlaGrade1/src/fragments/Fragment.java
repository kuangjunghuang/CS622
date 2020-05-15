package fragments;

// To represent a piece of a passage

public abstract class Fragment {
	
	protected String text = "text not determined yet";
	
	public Fragment() {
	}
	
	public Fragment(String someText) {
		text = someText;
	}
	
	public abstract void display();
	public abstract void setText(String someText);
	public abstract String getText();
	public abstract String getType();
}
