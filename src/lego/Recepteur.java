package lego;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;


public class Recepteur implements Behavior{
	private int sent_color = 5;
	private int actual_color = 0;
	private int[][] position = {{0, 0},{-1,1}};
	private int[][] obstacle = {{4, 5},{5, 4}};
	
	private SampleProvider color;
	private float[] sample;
	private Couleur refCouleur;

	
	/// Gestion des variables ///
	
	//Getter & Setter pour actual_color
	public void setColorA(int n){
		this.actual_color = n;
	}
	
	public int getColorA(){
		return this.actual_color;
	}
	
	//Getter & Setter pour sent_color
	public void setColorS(int n){
		this.sent_color = n;
	}
	
	public int getColorS(){
		return this.sent_color;
	}
	
	//Getter & Setter pour position
	public void setPos(int[][] pos){
		this.position = pos;
	}
	
	public int[][] getPos() {
		return this.position;
	}
	
	//Setter pour obstacle
	public void setObstacle(int[][] pos){
		this.obstacle = pos;
	}
	
	public void setColor(SampleProvider color) {
		this.color = color;
	}
	
	public void setSample(float[] sample) {
		this.sample = sample;
	}
	
	public void setRefCouleur(Couleur refCouleur) {
		this.refCouleur = refCouleur;
	}

   
	
	
	/// Methodes de mouvement ///
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
    Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, 2);
    MovePilot pilot = new MovePilot(chassis);
    
    
    //Avancer de n cases
	private void travel(double unite) {
		
		//Mouvement
    	double epaisseurTrait = 1.5;
    	double epaisseurCase = 12;
    	
    	double distance = unite*(epaisseurTrait+epaisseurCase)*10;
    	pilot.travel(distance);
    	
    	//Gestion de coordonnées
      	this.position[0][Utils.is_parallel_to(position)] += unite*Utils.direction(this.position);
      	this.position[1][Utils.is_parallel_to(position)] += unite*Utils.direction(this.position);
    	
    }
	
	//Tourner à 180 degrés
	private void turn180(EV3GyroSensor gyro, SensorMode angleProvider, float[] angle) {
		gyro.reset();
		angle[0] = 0.00f;
		
		//Mouvement
		pilot.travel(1);
		
		while(pilot.isMoving())Thread.yield();
			Motor.B.forward();
		while(Math.abs(angle[0])<180.f) {
			Delay.msDelay(100);
			angleProvider.fetchSample(angle, 0);
		}
		Motor.B.stop(true);
		
		//Gestion de coordonnées
		int direction = Utils.direction(this.position);
		if(Utils.is_parallel_to(this.position)==0) {
			this.position[1][0] += 2*direction;
			this.position[1][1] += -2*direction;
		}else{
			this.position[1][0] += 2*direction;
			this.position[1][1] += 2*direction;
		}
		
		
	}
	

	//Tourne à droite
	private void turn_right(EV3GyroSensor gyro, SensorMode angleProvider, float[] angle) {
		gyro.reset();
		angle[0] = 0.00f;
		
		pilot.travel(1);
		
		while(pilot.isMoving())Thread.yield();
			Motor.B.forward();
		while(Math.abs(angle[0])<90) {
			Delay.msDelay(200);
			angleProvider.fetchSample(angle, 0);
			}
		Motor.B.stop(true);
		}
	
	
	//Tourne à gauche
	private void turn_left(EV3GyroSensor gyro, SensorMode angleProvider, float[] angle) {
		gyro.reset();
		angle[0] = 0.00f;
		
		pilot.travel(1);
		
		while(pilot.isMoving())Thread.yield();
			Motor.C.forward();
			Motor.B.backward();
		while(Math.abs(angle[0])<90) {
			Delay.msDelay(200);
			angleProvider.fetchSample(angle, 0);	
		}
		Motor.C.stop(true);
		Motor.B.stop(true);
	}
	
	
	/// Methodes Behavior ///
	
    public boolean takeControl() {
        return Button.ENTER.isDown();        //Le robot ex�cute ce comportement si on appuie sur le bouton du milieu
    }
    
    public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
    }

 

    public void action() {
        String connected = "Connected";
        String waiting = "Waiting";
        System.out.println("mode recepteur");


        try {
            //LCD.drawString(waiting, 0, 0);
            //LCD.refresh();

            BTConnector bt = new BTConnector();
            NXTConnection btc = bt.waitForConnection(100000, NXTConnection.PACKET);

            
            if (btc !=null) {
            LCD.clear();
            LCD.drawString(connected, 0, 0);
            LCD.refresh();


            InputStream is = btc.openInputStream();
            //OutputStream os = btc.openOutputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            //DataOutputStream dos = new DataOutputStream(os);

            int[] valeurs = (int[])ois.readObject();
            this.setColorS(valeurs[0]);
            this.setObstacle(new int[][] {{valeurs[1], valeurs[2]},{valeurs[3], valeurs[4]}});


            ois.close();
            //dos.close();
            btc.close();

            Couleur.printColor(sent_color);
            
            
            } else {
                System.out.println("Pas de connexion");
            }
        } catch (Exception e) {
            System.out.println(e);   	
            }
        
        
        
        
        
        //Partie déplacement //
        
            //Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
          	int [] destination = Utils.lookFor(this.sent_color, this.position, this.obstacle);
          	
          	EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S2);
    		SensorMode angleProvider = (SensorMode) gyro.getAngleMode();
    		gyro.reset();
    		float[] angle = new float[]{0.00f};
          	
          	pilot.setLinearSpeed(30);
          	
          	while(this.position[0][0]!= destination[0] || this.position[0][1] != destination[1]) {
          		
          	if(this.position[0][Utils.is_parallel_to(this.position)] != destination[Utils.is_parallel_to(this.position)]) {
          	//Déplacement sur l'axe parallèle à celui du robot
	          	if (Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[0][Utils.is_parallel_to(this.position)])>Math.abs(destination[Utils.is_parallel_to(this.position)]-this.position[1][Utils.is_parallel_to(this.position)])) {
					this.turn180(gyro, angleProvider, angle);
		          	}
	          		this.travel(Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));

				
			}else{//Le robot tourne a droite ou a gauche et change les coordonnées en fonction
				int diff = Utils.sign(destination[Utils.isnot_parallel_to(position)]-position[0][Utils.isnot_parallel_to(position)]);
				
				
				if(Utils.is_parallel_to(position)==0) {//Cas parallel_to X
					if(diff == Utils.direction(position)) {
						this.turn_left(gyro, angleProvider, angle);
						this.position[1][1] += -2*Utils.direction(position);

					}else {
						this.travel(0.5);
						this.turn_right(gyro, angleProvider, angle);
						this.travel(0.5);
						this.position[1][0] += 2*Utils.direction(position);
					}
				}else {								//Cas parallel_to Y
					if(diff == Utils.direction(position)) {
						this.travel(0.5);
						this.turn_right(gyro, angleProvider, angle);
						this.travel(0.5);
						this.position[1][1] += 2*Utils.direction(position);
						}else {
							this.turn_left(gyro, angleProvider, angle);
							this.position[1][0] += 2*Utils.direction(position);
						}
					}
				
			      	this.position[0][Utils.is_parallel_to(position)] += Utils.direction(this.position);
			      	this.position[1][Utils.is_parallel_to(position)] += Utils.direction(this.position);
				}
          	this.actual_color = refCouleur.colorRGB(this.color, this.sample);
    		Couleur.printColor(this.actual_color);
		}
        gyro.close();
		System.out.println("success !");

    }

 

 

 

}