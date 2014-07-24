package de.htw.as.pilot;

import lejos.nxt.LightSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class FactoryTruck extends DifferentialPilot{
	
	private static final double AFTER_STOP_TRAVEL = 5;
	private static final long SLEEP = 5;

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
			forward();
			traveled = traveled + getMovementIncrement();
			if(sensor.getLightValue() >= lightValue);{
				stop();
				try {
					Thread.sleep(SLEEP);
				} catch (InterruptedException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				travel(AFTER_STOP_TRAVEL);
				traveled = traveled + (float) AFTER_STOP_TRAVEL;
			}
		}
		stop();
		return traveled;
	}

}
