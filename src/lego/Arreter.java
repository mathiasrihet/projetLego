package lego;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Arreter implements Behavior{
	private Arbitrator arby;
	
	public boolean takeControl() {
		return Button.LEFT.isDown();
	}
	
	public void suppress() {
	}


	public void setArbi(Arbitrator a){
		this.arby = a;
	}
    
	public void action() {
		Motor.B.stop(true);
		Motor.C.stop(true);
		if(arby!=null) {
			arby.stop();
		}
		System.exit(0);
	}
}
