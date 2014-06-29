package de.htw.as.behaviors;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DriveForwardBehavior implements Behavior {

	private UltrasonicSensor sensor;
	private DifferentialPilot pilot;
	private boolean suppressed;

	public DriveForwardBehavior(DifferentialPilot pilot, UltrasonicSensor sensor) {
		this.pilot = pilot;
		this.sensor = sensor;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return (sensor.getDistance() > 5 && sensor.getDistance() < 40);
	}

	@Override
	public void action() {
		suppressed = false;
		while (!suppressed && takeControl()) {
			System.out.println("Distance: " + sensor.getDistance());
			pilot.travel(3);
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
