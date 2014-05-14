package de.htw.as.sensor;

import lejos.nxt.I2CPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;

public class MoveableUltrasonicSensor extends UltrasonicSensor {
	
	private static final int MOTOR_SPEED = 100;
	
	private NXTRegulatedMotor motor;

	public MoveableUltrasonicSensor(I2CPort port, NXTRegulatedMotor motor) {
		super(port);
		this.motor = motor;
		motor.setSpeed(MOTOR_SPEED);
	}
	
	public void rotate(int angle){
		motor.rotate(angle);
	}

}
