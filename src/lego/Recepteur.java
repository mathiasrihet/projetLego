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
	private int actual_color = 5;
	private int[][] position = {{4, 1},{3,2}};
	private int[][] obstacle = {{3, 5},{4, 6}};

	
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
	
	//Setter pour position
	public void setPos(int[][] pos){
		this.position = pos;
	}
	
	//Setter pour obstacle
	public void setObstacle(int[][] pos){
		this.obstacle = pos;
	}

   
	
	
	/// Methodes de mouvement ///
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
    Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, 2);
    MovePilot pilot = new MovePilot(chassis);
    
    
	private void travel(double unite) {
    	double epaisseurTrait = 1.5;
    	double epaisseurCase = 12;
    	
    	double distance = unite*(epaisseurTrait+epaisseurCase)*10;
    	pilot.travel(distance);
    }
	
	
	/// Methodes de comportement ///
	
    public boolean takeControl() {
        return Button.ENTER.isDown();        //Le robot ex�cute ce comportement si on appuie sur le bouton du milieu
    }
    
    public void suppress() {
    }

 

    public void action() {
        // TODO Auto-generated method stub
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
        
        if(this.getColorS() != this.getColorA()) {
        	this.travel(3);
            

        }
    }

 

 

 

}