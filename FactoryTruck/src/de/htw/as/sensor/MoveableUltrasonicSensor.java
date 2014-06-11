package de.htw.as.sensor;

import lejos.nxt.I2CPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;

public class MoveableUltrasonicSensor extends UltrasonicSensor {
	
	private static final int DEFAULT_MOTOR_SPEED = 50;
    private int direction = 0;
	
	private NXTRegulatedMotor motor;

	public MoveableUltrasonicSensor(I2CPort port, NXTRegulatedMotor motor) {
		super(port);
		this.motor = motor;
		motor.setSpeed(DEFAULT_MOTOR_SPEED);
	}
	
	public void rotate(int angle){
        // neue richtung normalisiert (0 - 359)
        int new_direction = (angle + direction + 360) % 360;
        
        int alpha = new_direction - direction;
        direction = new_direction;
        
		motor.rotate(alpha);
	}
	
	public void rotateTo(int angle){
        motor.rotate(angle);
	}
	
	public void setSpeed(float speed){
		motor.setSpeed(speed);
	}
}
