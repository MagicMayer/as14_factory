package de.htw.as.behaviors;

import de.htw.as.util.DistanceConstances;
import de.htw.as.util.Justage;
import de.htw.as.util.LimitedQueue;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DriveForwardBehavior implements Behavior {
	
	private static final double OPTIMAL_DISTANCE = 20;

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
		return (sensor.getDistance() > DistanceConstances.TOO_CLOSE && sensor.getDistance() < DistanceConstances.TOO_FAR);
	}

	@Override
	public void action() {
		suppressed = false;
		int distance_1 = 0;
		int distance_2 = 0;
		while (!suppressed && takeControl()) {
			distance_1 = sensor.getDistance();
			System.out.println("D1: " + distance_1);
			pilot.travel(15);
			distance_2 = sensor.getDistance();
			if(distance_2 >= DistanceConstances.TOO_FAR){
				break;
			}
			System.out.println("D1: " + distance_2);
			double angle = Justage.getAngleToChange(15, distance_1 - distance_2);
			System.out.println("A: " + angle);
			
			double realDistance = Justage.wahrerAbstand(distance_2, angle);
			if(realDistance > OPTIMAL_DISTANCE){
				pilot.rotate(-45 + angle);
				double travel = Math.sqrt(2) * Math.abs(OPTIMAL_DISTANCE - realDistance);
				pilot.travel(travel);
				pilot.rotate(45);
			} else if(realDistance < OPTIMAL_DISTANCE){
				pilot.rotate(45 + angle);
				double travel = Math.sqrt(2) * Math.abs(OPTIMAL_DISTANCE - realDistance);
				pilot.travel(travel);
				pilot.rotate(-45);
			} else {
				pilot.rotate(angle);
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
