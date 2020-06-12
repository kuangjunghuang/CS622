package cingsim;

import java.util.ArrayList;

public class Vehicle {
	
	// Class Invariant
	// theVehicles consists of all Vehicle's
	
	public int arrivalTime = 0;
	public static ArrayList<Vehicle> theVehicles = new ArrayList<Vehicle>();
	
	public Vehicle() {
	}
	
	public Vehicle(int anArrivalTime) {
		arrivalTime = anArrivalTime;
	}

	public static void createVehicle(int anArrivalTime) {
	// (Postcondition 1 = Class Invariant)
	// Post2: theVehicles has a new Vehicle at the end
	// Returns index of new Vehicle in theVehicles
		Vehicle newVehicle = new Vehicle(anArrivalTime);
		theVehicles.add(newVehicle);
		int indexOfThisVehicle = theVehicles.size() - 1;
		System.out.println("Vehicle " + indexOfThisVehicle + 
				" created, arriving " + anArrivalTime + 
				" seconds after sim begins.");
	}
	
	public int polePosition() {
	/*
	 * Define: clockMinusArrival as MainCINGSIM.clock - arrivalTime
	 * e.g. arrival time 2, clock 5 ==> pole 5-2 = 3
	 * 
	 * -1 returned for negative clockMinusArrival,
	 * POLE_COUNT for clockMinusArrival >= POLE_COUNT,
	 * clockMinusArrival otherwise.
	 * 
	 * Known Issue: Inefficiency due to repeated computation of this??
	 */
		int clockMinusArrival = MainCINGSIM.clock - arrivalTime;
		if(clockMinusArrival < 0) 
			return -1;
		else if (clockMinusArrival >= MainCINGSIM.POLE_COUNT)
			return MainCINGSIM.POLE_COUNT;
		else
			return clockMinusArrival;
	}
}
