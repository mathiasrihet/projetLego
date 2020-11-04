package lego;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class Avancer implements Behavior {

	public boolean takeControl() {
		return true;	
	}
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}
	
	public void action() {
		Motor.B.setSpeed(5);
		Motor.C.setSpeed(5);
		Motor.B.forward();
		Motor.C.forward();
	}
}
