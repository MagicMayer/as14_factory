package de.htw.as.behaviors;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class NormalBehavior implements Behavior {

	private UltrasonicSensor sensor;
	private boolean suppressed = false;

	public NormalBehavior(UltrasonicSensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		while (!suppressed) {
			System.out.println("Disstance: " + sensor.getDistance());
			System.out.println("Nothin to do!");
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
