package de.htw.as.behaviors;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class StopBehavior implements Behavior {

	private LightSensor sensor;
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	
	public StopBehavior(LightSensor sensor, DifferentialPilot pilot) {
		this.sensor = sensor;
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		return sensor.getLightValue() > 50;
	}

	@Override
	public void action() {
		suppressed = false;
		try {
			System.out.println("L: " + sensor.getLightValue());
			Thread.sleep(2000);
			while (!suppressed && takeControl()) {
				System.out.println("L: " + sensor.getLightValue());
				pilot.travel(2);	
				Thread.sleep(1000);
			}		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
