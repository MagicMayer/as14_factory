package de.htw.as.base;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import de.htw.as.behaviors.DriveForwardBehavior;
import de.htw.as.behaviors.NormalBehavior;
import de.htw.as.behaviors.RightCurveBehavior;
import de.htw.as.pilot.FactoryTruck;
import de.htw.as.pilot.PilotFactory;

public class FactoryTruckNXT {
	
	private static final NXTRegulatedMotor SENSOR_MOTOR = Motor.C;
	private static final SensorPort ULTRASONIC_SENSOR_PORT= SensorPort.S1;
	private static final SensorPort LIGHT_SENSOR_PORT= SensorPort.S2;
	
	/* Do not make an instance of the main class. */
	private FactoryTruckNXT() {}
	
	public static void main (String[] args){
		FactoryTruck pilot = PilotFactory.getDefaultPilot();
		pilot.setRotateSpeed(50.0);
		UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(ULTRASONIC_SENSOR_PORT);
		LightSensor lightSensor = new LightSensor(LIGHT_SENSOR_PORT);
		
		System.out.println("Press a Button" + "\nto start...");
		Button.waitForAnyPress();
		Sound.beep();
		Behavior[] behaviors = {
			new NormalBehavior(ultrasonicSensor),
			new DriveForwardBehavior(pilot, ultrasonicSensor, lightSensor),
			new RightCurveBehavior(pilot, ultrasonicSensor)
		};
		Arbitrator arbitrator = new Arbitrator(behaviors, true);
		arbitrator.start();
	}
}
