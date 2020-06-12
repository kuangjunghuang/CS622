package cingsimtest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import cingsim.*;
import org.junit.*;

class MainCINGSIMTest{
	
	@Before
	public void setUp() throws Exception {
		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  		
  		Vehicle.createVehicle(0);
  		Vehicle.createVehicle(5);
	}
  	
  	@Test
  	void advanceClockTest() {
		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  		
  		Vehicle.createVehicle(0);  // Vehicle 0
  		Vehicle.createVehicle(5);  // Vehicle 1
  		assertEquals(2, Vehicle.theVehicles.size());
  		assertEquals(0, Vehicle.theVehicles.get(0).polePosition());
  		assertEquals(-1, Vehicle.theVehicles.get(1).polePosition());		
  		MainCINGSIM.advanceClock();
  		assertEquals(1, Vehicle.theVehicles.get(0).polePosition());
  		assertEquals(-1, Vehicle.theVehicles.get(1).polePosition());		
  	}
	
  	@Test
  	void moreCarsTest() {
		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  	
  		MainCINGSIM.clock = 0;
  		Vehicle.createVehicle(5);  // Vehicle 0
  		assertTrue(MainCINGSIM.moreCars());
  		for (int i = 0; i < 5; ++i) {  // ready to move
  			MainCINGSIM.advanceClock();
  		}
  		assertTrue(MainCINGSIM.moreCars()); 
  		for (int i = 0; i < MainCINGSIM.POLE_COUNT; ++i) {  // move beyond poles
  			MainCINGSIM.advanceClock();
  		}
  		assertFalse(MainCINGSIM.moreCars()); 

  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset 
  		MainCINGSIM.clock = 0;
  		Vehicle.createVehicle(2);  // Vehicle 0  		
  		while (MainCINGSIM.moreCars()) {
  			MainCINGSIM.advanceClock();
		} 

  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset 
  		MainCINGSIM.clock = 0;
  		Vehicle.createVehicle(2);  // Vehicle 0  
  		Vehicle.createVehicle(4);  // Vehicle 1  		
  		while (MainCINGSIM.moreCars()) {
  			MainCINGSIM.advanceClock();
		} 
  	}
  	
  	@Test
  	public void showVehiclePositionsTest() {
		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  	
  		MainCINGSIM.clock = 0;
  		Vehicle.createVehicle(5);  // Vehicle 0
  		System.out.println("\n----------Expect Vehicle # 0 at N:");
  		MainCINGSIM.showLightsAndVehiclePositions();
  		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  	
  		MainCINGSIM.clock = 20;
  		Vehicle.createVehicle(5);  // Vehicle 0
  		System.out.println("\n----------Expect Vehicle # 0 at G:");
  		MainCINGSIM.showLightsAndVehiclePositions();
  		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  	
  		MainCINGSIM.clock = 8;
  		Vehicle.createVehicle(5);  // Vehicle 0
  		System.out.println("\n----------Expect Vehicle # 0 at pole 3:");
  		MainCINGSIM.showLightsAndVehiclePositions();
  		
  		Vehicle.theVehicles = new ArrayList<Vehicle>();  // reset  	
  		MainCINGSIM.clock = 8;
  		Vehicle.createVehicle(9);  // Vehicle 0
  		Vehicle.createVehicle(3);  // Vehicle 1
  		Vehicle.createVehicle(0);  // Vehicle 2
  		System.out.println("\n----------Expect Vehicle # 0 at pole N:");
  		System.out.println("----------Expect Vehicle # 1 at pole 5:");
  		System.out.println("----------Expect Vehicle # 2 at pole G:");
  		MainCINGSIM.showLightsAndVehiclePositions();
  	}
} 
