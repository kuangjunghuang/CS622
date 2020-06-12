package cingsimtest;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import cingsim.*;

class VehicleTest{
	
  	@Test
	void createVehicleTest() {
		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset
  		
  		Vehicle.createVehicle(3);
  		Vehicle.createVehicle(22);
  		
  		assertEquals(22, Vehicle.theVehicles.get(1).arrivalTime);
  	}
  	
  	@Test
  	void polePositionTest() {

  		Vehicle vehicle = new Vehicle(5);
  		MainCINGSIM.clock = 2;
  		assertEquals(-1, vehicle.polePosition());
  		MainCINGSIM.clock = 5;
  		assertEquals(0, vehicle.polePosition());
  		MainCINGSIM.clock = 9;
  		assertEquals(4, vehicle.polePosition());
  		MainCINGSIM.clock = 12;
  		assertEquals(7, vehicle.polePosition());
  		MainCINGSIM.clock = 13;
  		assertEquals(8, vehicle.polePosition());  		
  	}
} 
