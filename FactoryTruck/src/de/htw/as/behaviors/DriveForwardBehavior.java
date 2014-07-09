package de.htw.as.behaviors;

import de.htw.as.util.DistanceConstances;
import de.htw.as.util.Justage;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DriveForwardBehavior implements Behavior {
	
	private static final double OPTIMAL_DISTANCE = 25;
	private static final double TOLERANCE = 2;
	private static final long WAIT_TIME = 2000;


	private UltrasonicSensor ultrasonicSensor;
	private LightSensor lightSensor;
	private DifferentialPilot pilot;
	private boolean suppressed;

	public DriveForwardBehavior(DifferentialPilot pilot, UltrasonicSensor ultrasonicSensor, LightSensor lightSensor) {
		this.pilot = pilot;
		this.ultrasonicSensor = ultrasonicSensor;
		this.lightSensor = lightSensor;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		return (ultrasonicSensor.getDistance() > DistanceConstances.TOO_CLOSE && ultrasonicSensor.getDistance() < DistanceConstances.TOO_FAR);
	}

	@Override
	public void action() {
		suppressed = false;
		int distance_1 = 0;
		int distance_2 = 0;
		while (!suppressed && takeControl()) {
			distance_1 = ultrasonicSensor.getDistance();
			System.out.println("D1: " + distance_1);
			//pilot.travel(15);
			drive(15);
			distance_2 = ultrasonicSensor.getDistance();
			
			System.out.println("D2: " + distance_2);
			double angle = Justage.getAngleToChange(15, distance_1 - distance_2);
			System.out.println("A: " + angle);		
			if(distance_2 >= DistanceConstances.TOO_FAR){
				System.out.println("BREAK");
				//pilot.rotate(angle);
				break;
			}
			
			double realDistance = Justage.wahrerAbstand(distance_2, angle);
			if(realDistance > OPTIMAL_DISTANCE){
				pilot.rotate(-45 + angle);
				double travel = Math.sqrt(2) * Math.abs(OPTIMAL_DISTANCE - realDistance);
				//pilot.travel(travel);
				drive(travel);
				pilot.rotate(45);
			} else if(realDistance < OPTIMAL_DISTANCE){
				pilot.rotate(45 + angle);
				double travel = Math.sqrt(2) * Math.abs(OPTIMAL_DISTANCE - realDistance);
				//pilot.travel(travel);
				drive(travel);
				pilot.rotate(-45);
			} else {
				pilot.rotate(angle);
			}
		}
		pilot.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
	
	private void stop(){
		System.out.println("L: " + lightSensor.getLightValue());
		if(lightSensor.getLightValue() > 50){
			System.out.println("Zone found, waiting...");
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Ready again!");
			pilot.travel(8);
		}
	}
	
	private void drive(double distance){
		for (double i = 0; i <= distance; i++) {
			stop();
			pilot.travel(1);
		}
	}
}
