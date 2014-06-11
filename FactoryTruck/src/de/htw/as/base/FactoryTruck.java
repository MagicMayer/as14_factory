package de.htw.as.base;

import de.htw.as.behaviours.DriveForwardBehavior;
import de.htw.as.behaviours.NormalBehavior;
import de.htw.as.pilot.PilotFactory;
import de.htw.as.sensor.MoveableUltrasonicSensor;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class FactoryTruck {
	
	private static final NXTRegulatedMotor SENSOR_MOTOR = Motor.C;
	private static final SensorPort ULTRASONIC_SENSOR_PORT= SensorPort.S1;
	
	/**
	
	/**
	 * Do not make an instance of the main class.
	 */
	private FactoryTruck() {}
	
	public static void main (String[] args){
		DifferentialPilot pilot = PilotFactory.getDefaultPilot();
		pilot.setRotateSpeed(50.0);
		/*
		MoveableUltrasonicSensor sensor = new MoveableUltrasonicSensor(
				ULTRASONIC_SENSOR_PORT,
				SENSOR_MOTOR
		);
		*/
		System.out.println("Press a Button"
				+ "\nto start...");
		Button.waitForAnyPress();
		Sound.beep();
		
		pilot.travel(30);;
		pilot.rotate(90.0);
		
		/*
		Behavior[] behaviors = {
				new NormalBehavior(),
				new DriveForwardBehavior(pilot, sensor)
		};
		
		Arbitrator arbitrator = new Arbitrator(behaviors, true);
		arbitrator.start();
		*/
		
		/*
		sensor.rotate(90);
		System.out.println("Press a Button..");
		Button.waitForAnyPress();
		sensor.rotate(-180);
		System.out.println("Programm End!");
		*/
		//pilot.rotate(90);
		
	}

}
