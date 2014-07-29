package de.htw.as.behaviors;

import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;
import de.htw.as.pilot.FactoryTruck;
import de.htw.as.util.DistanceConstants;
import de.htw.as.util.Justage;
import de.htw.as.util.NavigationConstants;

public class DriveForwardBehavior implements Behavior {
	
	private static final int LIGHT_STOP_VALUE = 55;
	private static final double TRAVEL_DISTANCE_PER_TEST = 15;

	private UltrasonicSensor ultrasonicSensor;
	private LightSensor lightSensor;
	private FactoryTruck pilot;
	private boolean suppressed;

	public DriveForwardBehavior(FactoryTruck pilot, UltrasonicSensor ultrasonicSensor, LightSensor lightSensor) {
		this.pilot = pilot;
		this.ultrasonicSensor = ultrasonicSensor;
		this.lightSensor = lightSensor;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return (ultrasonicSensor.getDistance() > DistanceConstants.TOO_CLOSE && ultrasonicSensor.getDistance() < DistanceConstants.TOO_FAR);
	}

	@Override
	public void action() {
		System.out.println("Drive forward...");
		suppressed = false;
		int firstDistance = 0;
		int secondDistance = 0;
		while (!suppressed && takeControl()) {	
			firstDistance = ultrasonicSensor.getDistance();
			if (!takeControl())	break;
			System.out.println("D1: " + firstDistance);
			float traveled = pilot.travelAndSearch(TRAVEL_DISTANCE_PER_TEST, lightSensor, LIGHT_STOP_VALUE);
			System.out.println("T1: " + traveled);
			secondDistance = ultrasonicSensor.getDistance();
			if(!takeControl()) break;
			System.out.println("D1: " + secondDistance);
			double angle = Justage.getAngleToChange(traveled, firstDistance - secondDistance);
			correctPath(secondDistance, angle);
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
	
	private void correctPath(double distance, double angle){
		//System.out.println("Correct Path, D: " + distance);
		if(distance > DistanceConstants.OPTIMAL_DISTANCE/* + DistanceConstances.TOLERANCE*/){
			
			pilot.rotate(NavigationConstants.RIGHT_90 + angle);
			double travel = Math.abs(DistanceConstants.OPTIMAL_DISTANCE - distance);
			
			pilot.travelAndSearch(travel, lightSensor, LIGHT_STOP_VALUE);
			pilot.rotate(NavigationConstants.LEFT_90);
		} else if(distance < DistanceConstants.OPTIMAL_DISTANCE/* - DistanceConstances.TOLERANCE*/){
			
			
			pilot.rotate(NavigationConstants.LEFT_90 + angle);
			double travel = Math.abs(DistanceConstants.OPTIMAL_DISTANCE - distance);
			
			pilot.travelAndSearch(travel, lightSensor, LIGHT_STOP_VALUE);;
			pilot.rotate(NavigationConstants.RIGHT_90);
		} 
		System.out.println("Path corrected");
	}
}
