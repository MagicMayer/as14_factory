package de.htw.as.behaviors;

import de.htw.as.util.DistanceConstances;
import de.htw.as.util.NavigationConstances;
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
		return sensor.getDistance() <= DistanceConstances.TOO_CLOSE;
		
	}

	@Override
	public void action() {
		pilot.rotate(NavigationConstances.RIGHT_180);
		pilot.travel(DistanceConstances.OPTIMAL_DISTANCE - DistanceConstances.TOO_CLOSE);
		pilot.rotate(NavigationConstances.LEFT_180);

	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
