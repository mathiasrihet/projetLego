package lego;
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
	//Variables ï¿½ trier
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
    
	private void rotate(float threshold) {
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S2);
		SensorMode angleProvider = (SensorMode) gyro.getAngleMode();
		gyro.reset();
		float[] angle = new float[]{0.0f};
		
		pilot.travel(20);
		
		while(pilot.isMoving())Thread.yield();
			Motor.C.forward();
		while(Math.abs(angle[0])<threshold) {
			Delay.msDelay(500);
			angleProvider.fetchSample(angle, 0);
		}
		gyro.close();
	}
	
	public boolean takeControl() {
		return false;
	}
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

    
	public void action() {
		//Variables: sa position, la position de l'autre robot, la couleur demandée
		int couleur = 4;
		int[][] position = {{4, 1},{3,0}};
		int[][] obstacle = {{3, 5},{4, 6}};
		
		//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
		int [] destination = Utils.lookFor(couleur, position, obstacle);
		
		//Le robot se déplace sur l'axe avec lequel il est aligné pour se rapprocher de la case
		pilot.setLinearSpeed(20);
		
		if (Utils.sign(destination[Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)])== -1) {
			this.rotate(180.f);
		}

		this.travel(Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));
		
		//Le robot tourne pour se déplacer sur l'autre axe
		//this.rotate() à écrire
		
		//Puis avance sur cet axe (avec lequel il est maintenant parallèle)
		//this.travel(destination [Tests.is_parallel_to(position)]-position[0][Tests.is_parallel_to(position)]);
		
		//On est arrivé ! Plus qu'à tirer un numéro et l'envoyer à l'autre robot !
		Utils.colorchoice();
		
		//Note: si le robot rencontre un obstacle, le comportement "Avoid" doit récupérer la priorité
	}
}