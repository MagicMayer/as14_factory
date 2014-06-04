package de.htw.as.behaviours;

import lejos.nxt.Motor;
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
		return shouldMove();
	}

	@Override
	public void action() {

		suppressed = false;
		while (!suppressed && shouldMove()) {
			System.out.println("Distance: " + sensor.getDistance());
			pilot.forward();
		}
		pilot.stop();

	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	private boolean shouldMove() {
		return (sensor.getDistance() > 5 && sensor.getDistance() < 20);
	}

}
