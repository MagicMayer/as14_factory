package de.htw.as.behaviors;

import de.htw.as.util.DistanceConstances;
import de.htw.as.util.NavigationConstances;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class RightCurveBehavior implements Behavior {
	
	private static final double ROTATE_ANGLE = -90.0;
	private static final double TRAVEL_DISTANCE = 40.0;
	
	private UltrasonicSensor sensor;
	private DifferentialPilot pilot;
	private boolean suppressed;
	
	public RightCurveBehavior(DifferentialPilot pilot, UltrasonicSensor sensor) {
		this.pilot = pilot;
		this.sensor = sensor;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		/* TODO Auto-generated method stub */
		return sensor.getDistance() >= DistanceConstances.TOO_FAR;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Start Right Curve.");
		pilot.travel(15);
		pilot.rotate(NavigationConstances.RIGHT_90);
		
		while (takeControl()) {
			pilot.forward();	
		}
		pilot.stop();
		
		//pilot.travelArc(ROTATE_ANGLE, TRAVEL_DISTANCE);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
