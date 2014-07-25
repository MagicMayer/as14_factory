package de.htw.as.behaviors;

import de.htw.as.util.DistanceConstants;
import de.htw.as.util.NavigationConstants;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class TooCloseBehavior implements Behavior {
	
	private UltrasonicSensor sensor;
	private DifferentialPilot pilot;
	private boolean suppressed = false;
	
	public TooCloseBehavior(UltrasonicSensor sensor, DifferentialPilot pilot) {
		this.sensor = sensor;
		this.pilot = pilot;
	}

	@Override
	public boolean takeControl() {
		return sensor.getDistance() <= DistanceConstants.TOO_CLOSE;
		
	}

	@Override
	public void action() {
		pilot.rotate(NavigationConstants.RIGHT_180);
		pilot.travel(DistanceConstants.OPTIMAL_DISTANCE - DistanceConstants.TOO_CLOSE);
		pilot.rotate(NavigationConstants.LEFT_180);

	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
