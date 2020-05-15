package fragments_test;
import java.util.ArrayList;

/*
 * Example of a hierarchy of heading- and non-heading- fragments, and their display
 */
import fragments.Fragment;
import fragments.HeadingFragment;
import fragments.NonHeadingFragment;

class FragmentDisplayExample {
	
	public static ArrayList<Fragment> theFragments = new ArrayList<Fragment>();
	
	public static void main(String[] args) { // ?? temp
		
		System.out.println("=========FragmentDisplayExample==========");
		displayFragmentHierarchyExample();
	}

	static void displayFragmentHierarchyExample() {  
	/*
	 * Postcondition 1 (theFragments): theFragments consists of every Fragment created
	 * Post2 (Various): Various NonHeadingFragment objects exists within HeadingFragment objects
	 * within a HeadingFragment with name "My Expenses Heading"
	 * Post3 (Display): As for displayAll() for "My Expenses Heading"
	 * Post4 (Counts): As for displayTypeCount()
	 */
				
		// displayAll() tested on nontrivial example
		
		/* myExpensesHeading denotes the following 
		 My Expenses Heading
		 Taxes Heading
		   	Estate taxes
		   	Income taxes
		 Housing Heading
		   	Mortgage for housing
		   	Maintenance for housing
		Food
		 */

		// Post1 (theFragments) AND Post2 (Various):
		
		HeadingFragment myExpensesHeading = new HeadingFragment("My Expenses Heading");
		theFragments.add(myExpensesHeading); // for Post1
		 
		NonHeadingFragment estateTaxes = new NonHeadingFragment("Estate taxes");
		theFragments.add(estateTaxes); // for Post1
		NonHeadingFragment incomeTaxes = new NonHeadingFragment("Income taxes");
		theFragments.add(incomeTaxes); // for Post1
		HeadingFragment taxesHeading = new HeadingFragment("Taxes Heading");
		theFragments.add(taxesHeading); // for Post1
		taxesHeading.add(estateTaxes);
		taxesHeading.add(incomeTaxes);
		
		NonHeadingFragment mortgageForHousing = new NonHeadingFragment("Mortgage for housing");
		theFragments.add(mortgageForHousing); // for Post1
		NonHeadingFragment maintenanceForHousing = new NonHeadingFragment("Maintenance for housing");
		theFragments.add(maintenanceForHousing); // for Post1
		HeadingFragment housingHeading = new HeadingFragment("Housing Heading");
		theFragments.add(housingHeading); // for Post1
		housingHeading.add(mortgageForHousing);
		housingHeading.add(maintenanceForHousing);		

		NonHeadingFragment food = new NonHeadingFragment("Food");
		theFragments.add(food); // for Post1
		
		myExpensesHeading.add(taxesHeading);
		myExpensesHeading.add(housingHeading);
		myExpensesHeading.add(food);
		
		// Post3 (Display):
		System.out.println("========= Should display 8 items ==========");
		myExpensesHeading.displayAll();		
		// Post4 (Counts)
		displayTypeCount();
	}
	
	static void displayTypeCount() {
	// Postcondition: The counts of heading- and non-heading type fragments in theFragments
	// are on the console
		
	    int headingCount = 0, nonHeadingCount = 0;
		for (Fragment fragment : theFragments) {
	        if (fragment.getType() == "Heading fragment") {
	        	headingCount += 1;
	        }
	        else {
	        	nonHeadingCount += 1;
	        }
	    }
		System.out.println("\n" + headingCount + " headings and " + nonHeadingCount + " others.");
	}
}
