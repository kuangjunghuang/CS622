// Devices on poles that switch lights on or off according to cars present
// Known issue: inefficiency of repeated computation of location

package cingsim;

public class PoleDevice extends Thread {
	
	public int MILLISECONDS_BETWEEN_SENSING = 505;  // (of cars by pole devices)
	public int poleNumber = 0;
	public boolean lightOn = false;
	
	public static PoleDevice[] theDevices = {
			new PoleDevice(0),  // #0 -- on first pole
			new PoleDevice(1),  // #1
			new PoleDevice(2),  // #2
			new PoleDevice(3),  // #3
			new PoleDevice(4),  // #4
			new PoleDevice(5),  // #5
			new PoleDevice(6),  // #6
			new PoleDevice(7)   // #7
	};
	
	public PoleDevice() {
	}
	
	public PoleDevice(int aPoleNumber) {
		
		poleNumber = aPoleNumber;
	}
	
	private boolean vehicleAt(int aPole) {
		
		boolean returnBoolean = false;		
		for(Vehicle aVehicle : Vehicle.theVehicles) {
			if( aVehicle.polePosition() == aPole) {
				returnBoolean = true;
				break;
			}
		}		
		return returnBoolean;
	}
	
	public void run() {
	/*
	 * Postconditions: Every MILLISECONDS_BETWEEN_SENSING milliseconds ...
	 * 
	 * Postcondition 1 (Pole 0): EITHER 
	 * there is a car at pole 0 AND lights 0 and 1 are on
	 * OR 
	 * (there is no car at 0 and there is a car at 1) AND (light 0 is off and 1 is on)
	 * OR
	 * (there is no car at 0 or 1) AND (lights 0 and 1 are off)
	 * 
	 * Post2 (Poles 1-6): Poles 1-6 lights are on/off 0 or 1 poles ahead
	 * depending on whether there is a car at the light or one ahead
	 */ 
		while (MainCINGSIM.moreCars()) {

			// Post1 (Pole 0):
			
			if (poleNumber == 0 && vehicleAt(0)) {  //turn on
				lightOn = true;  // here
				theDevices[1].lightOn = true;
			}
			if (poleNumber == 0 && !vehicleAt(0) && vehicleAt(1)) {  
				lightOn = false;  // here				
				theDevices[1].lightOn = true;  // one ahead
			}
			if (poleNumber == 0 && !vehicleAt(0) && !vehicleAt(1)) {  
				lightOn = false;  // here				
				theDevices[1].lightOn = false;  // one ahead
			}
			
			// Post2 (Poles 1-6):
			
			if (poleNumber > 0 && poleNumber < 7 && vehicleAt(poleNumber)) {  // vehicle is here
				lightOn = true;  // here
				theDevices[poleNumber + 1].lightOn = true;  // one ahead
			}
			// No vehicle (turn off one ahead but not my own light necessarily)
			if (poleNumber > 0 && poleNumber < 7 && !vehicleAt(poleNumber) && 
						!vehicleAt(poleNumber + 1)) {
				theDevices[poleNumber + 1].lightOn = false;
			} 
						
			// Every half second
		
			try {
				Thread.sleep(MILLISECONDS_BETWEEN_SENSING); 
			} 
			catch (InterruptedException e) { 
				e.printStackTrace();
			}	
		}
	}	
}
