package de.htw.as.pilot;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class FactoryTruck extends DifferentialPilot{
	
	private static final double AFTER_STOP_TRAVEL = 5;
	private static final long SLEEP = 2000;

	public FactoryTruck(double wheeleDiameter, double trackWidth, RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		super(wheeleDiameter, trackWidth, leftMotor, rightMotor);
	}
 
	public FactoryTruck(double wheeleDiameter, double trackWidth, RegulatedMotor leftMotor, RegulatedMotor rightMotor, double travelSpeed) {
		this(wheeleDiameter, trackWidth, leftMotor, rightMotor);
		setTravelSpeed(travelSpeed);
	}
	
	public float travelAndSearch(double distance, LightSensor sensor, int lightValue){
		float traveled = 0;
		
		while(traveled < distance){
			if(!isMoving()) forward();
			int light = sensor.getLightValue();
			System.out.println("L: " + light + " S: " + (light >= lightValue));
			//System.out.println("M: " + getMovement().getDistanceTraveled());
			traveled = getMovement().getDistanceTraveled();
			//System.out.println("T: " + traveled + " S: " + (traveled < distance));
			if(light >= lightValue){
				System.out.println("Waiting...");		
				stop();
				//Button.waitForAnyPress();
				try {
					Thread.sleep(SLEEP);
				} catch (InterruptedException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				travel(AFTER_STOP_TRAVEL);
				traveled = traveled + (float) AFTER_STOP_TRAVEL;
			}
			//System.out.println("T: " + traveled);
		}
		System.out.println("END");
		stop();
		return traveled;
	}

}
