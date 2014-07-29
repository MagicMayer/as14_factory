package de.htw.as.behaviors;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import de.htw.as.util.Const;

public class RightCurveBehavior implements Behavior {
	
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
		return sensor.getDistance() >= Const.TOO_FAR;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Start Right Curve.");		
		pilot.travel(15);
		pilot.rotate(Const.RIGHT_90 + 10);
		
		while (takeControl()) {
			pilot.forward();
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
