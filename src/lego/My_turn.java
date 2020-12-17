package lego;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class My_turn implements Behavior{
	
	public boolean takeControl() {
		return false;
	}
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
		
		//cf. action() de Emetteur
	}

    
	public void action() {
		//cf. boucle while de Recepteur
	}
}