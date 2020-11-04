package lego;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.*;




public class Deplacer implements Behavior {
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
    Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, 2);
    MovePilot pilot = new MovePilot(chassis);
    
    private void travel(int unite) {
    	double epaisseurTrait = 1.5;
    	double epaisseurCase = 12;
    	
    	double distance = unite*(epaisseurTrait+epaisseurCase)*10;
    	pilot.travel(distance);
    }
    
    
	public boolean takeControl() {
		return true;	
	}
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}
	
	public void action() {
		pilot.setLinearSpeed(20);
		this.travel(2);
	}
}

