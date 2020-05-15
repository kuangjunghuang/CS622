package fragments;

//To represent a piece of a passage that's a heading

import java.util.ArrayList;

public class HeadingFragment extends Fragment {
	
	private ArrayList<Fragment> theSubFragments = new ArrayList<Fragment>();
	
	public HeadingFragment() {
		super();
	}
	
	public HeadingFragment(String aString) {
		super(aString);
	}
	
	public void add(Fragment aFragment) {
		theSubFragments.add(aFragment);
	}
	
	public void display() {
		System.out.println(text);		
	}	

	public void displayAll() {
	// Postcondition: the fragments for which this is a heading are on the console

		display();  // the heading itself
		
		// display sub-headings and their sub-headings
		for(Fragment fragment: theSubFragments) {
			if (fragment instanceof HeadingFragment) {
				((HeadingFragment)fragment).displayAll();
			}
			else {
				fragment.display();		
			}
		}
	}	

	public void setText(String someText) {
		text = someText.substring(0, someText.length());
	}
	
	public ArrayList<Fragment> getSubFragments() {
		return theSubFragments;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return "Heading fragment";
	}	
	
}
