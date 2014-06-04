package de.htw.as.behaviours;

import lejos.robotics.subsumption.Behavior;

public class NormalBehavior implements Behavior {
	
	private boolean suppressed = false;
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		while (!suppressed) {
			System.out.println("Nothin to do!");
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
