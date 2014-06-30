package de.htw.as.pilot;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class PilotFactory {
	
	private static final double DEFAULT_TRAVEL_SPEED = 10.0;
	private static final double DEFAULT_WHEELDIAMETER = DifferentialPilot.WHEEL_SIZE_NXT2;
	private static final double DEFAULT_TRACK_WIDTH = 9.0; //6.0;
	private static final NXTRegulatedMotor DEFAULT_LEFT_MOTOR = Motor.A;
	private static final NXTRegulatedMotor DEFAULT_RIGHT_MOTOR = Motor.B;

	/* Singleton */
	private PilotFactory() {
	}

	public static DifferentialPilot getDifferentialPilot(NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor, double wheelDiameter, double trackWidth, double travelSpeed) {
		DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor);
		pilot.setTravelSpeed(travelSpeed);
		return pilot;
	}

	public static DifferentialPilot getDefaultPilot(){
		return getDifferentialPilot(
			DEFAULT_LEFT_MOTOR,
			DEFAULT_RIGHT_MOTOR,
			DEFAULT_WHEELDIAMETER, 
			DEFAULT_TRACK_WIDTH,
			DEFAULT_TRAVEL_SPEED
			);
	}
}
