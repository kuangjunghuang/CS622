// Controls simulation of on-demand street lights for vehicles

package cingsim;

import java.util.Scanner;
import java.util.StringTokenizer;

public class MainCINGSIM extends Thread { 

	public static int clock = 0;  // seconds since start of simulation
	public static int A_LITTLE_LESS_THAN_HALF_SECOND = 495;
	public static int MAX_VEHICLES = 3;
	public static int POLE_COUNT = 8;

	public static void advanceClock() {  
	/*
	 * Postcondition: Every second, clock = old(clock) + 1
	 */
		
		try {
			Thread.sleep(1000);
			clock += 1;
		} 
		catch (InterruptedException e) { 
			e.printStackTrace();
		}		
	} 
	
	public static void getVehicleArrivalTimes() {
	/*
	 * Postcondition 1: User was prompted for increasing sequence, as in 2 3 5 8
	 * Post2: For every digit d in the sequence, post as for Vehicle.createVehicle(d)
	 * Known Issue: No check for input validity
	 */		 
		 System.out.print("Please enter up to " + MAX_VEHICLES + " arrival times,"
		 		+ "\nnatural numbers, as in '2 3 5': ");
		 Scanner sequenceScanner = new Scanner(System.in);
		 String line = sequenceScanner.nextLine();  // for convenience
		 sequenceScanner.close();
		 System.out.println("Vehicles arriving at--->" + line);
		 StringTokenizer timeTokens = new StringTokenizer(line); 
		 while (timeTokens.hasMoreTokens()){
			 Integer arrivalTime = new Integer(timeTokens.nextToken());
			 Vehicle.createVehicle(arrivalTime.intValue()); 
		 } 		
	}
	
	public static void main(String[] args) {
	/*
	 * Postcondition 1: As for getVehicleArrivalTimes()
	 * Post2: As for showLightsAndVehiclePositions()
	 * Post3: Every PoleDevice.theDevices started
	 * Post4: As for reportStatus()
	 */
		getVehicleArrivalTimes();
		System.out.println("========AT CLOCK TIME 0========");
		showLightsAndVehiclePositions();
		for (PoleDevice device : PoleDevice.theDevices) {
			device.start(); 
		}
		(new MainCINGSIM()).start();
		reportStatus();
	}
	
	public static boolean moreCars() {
	// Returns whether v.polePosition < POLE_COUNT for some Vehicle v
		
		boolean moreCars = false;
		for (Vehicle vehicle : Vehicle.theVehicles) {
			if (vehicle.polePosition() < POLE_COUNT) {  // within range
				moreCars = true;
				break;
			}
		}	
		return moreCars;
	}

	public static void reportStatus() {  
	/*
	 * Postcondition: As for showLightsAndVehiclePositions() every A_LITTLE_LESS_THAN_HALF_SECOND
	 */
		while(moreCars()) {
			try {
				Thread.sleep(A_LITTLE_LESS_THAN_HALF_SECOND);
				System.out.println
					("==================AT APPROX. TIME " + clock + "==================");
				showLightsAndVehiclePositions();
			} 
			catch (InterruptedException e) { 
				e.printStackTrace();
			}
		} 
	} 
	
	public static void showLightsAndVehiclePositions() {
	/*
	 * Postcondition 1 (Top Line): The following is on the console
	 * Column N = "not arrived yet"; G = "gone from range"
	 * Example:
	 * N--0--1--2--3--4--5--6--7--G     (this is line 0 of console output)
	 * 2           						(this is line 1 of console output)
	 *    1
	 *             0
	 *             
	 * Post2 (Lights): The second console line shows lights on (N) or off (F)
	 * 
	 * Post3 (vehicleNumber): The first unreported vehicle is # vehicleNumber
	 * 
	 * Post4 (All Vehicles): Location of every vehicle is on a separate line
	 */ 
		// Post1 (Top Line):  

		System.out.println
				("'T' = to arrive; 'G' = gone; 'N' = light on; 'F' = light off");
		System.out.println("T--0--1--2--3--4--5--6--7--G");
		
		// Post2 (Lights):
		
		StringBuffer lights = 
				new StringBuffer("                            "); 	
		int poleDisplayPosition = 3; 
		for(PoleDevice aPole : PoleDevice.theDevices) {  	
			lights.replace(poleDisplayPosition, poleDisplayPosition, 
					aPole.lightOn?"N":"F");
			poleDisplayPosition +=3;
		}
		System.out.println(lights); 
		
		// Post3 (vehicleNumber):
		
		int vehicleNumber = 0;
		
		// Post4 (All Vehicles):
		
		for(Vehicle aVehicle : Vehicle.theVehicles) {    
			
			int poleNum = aVehicle.polePosition();
			String vehicleNumString = (new Integer(vehicleNumber)).toString();
			if (poleNum < 0) {  // vehicle # at "N" position
				System.out.println(vehicleNumString);
			}
			else if (poleNum >= POLE_COUNT) {  // vehicle # at "G" position
				System.out.println
						("                           " + vehicleNumString);				
			}
			else { // vehicle # at pole number position
				StringBuffer vehiclePosition = 
						new StringBuffer("                            "); 	
				int replacementSite = 3 + 3*poleNum;
				vehiclePosition.replace
						(replacementSite, replacementSite, vehicleNumString);
				System.out.println(vehiclePosition);
			}
			vehicleNumber += 1;  // restore G2
		}
	}
	
	public void run() {
	// Postcondition 1 (clock): As for advanceClock	as long as there are vehicles
	// Post2 (Last Light): light 7 is off
		
		while (moreCars()) {
			advanceClock();
		} 
		PoleDevice.theDevices[7].lightOn = false;  // no more cars 
	}
}
