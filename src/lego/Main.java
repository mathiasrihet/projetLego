package lego;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	//Main avec les 4 Behavior

	public static void main(String[] args) {
		Port s3 = LocalEV3.get().getPort("S3");
		EV3ColorSensor colorSensor = new EV3ColorSensor(s3);
		SampleProvider color =  colorSensor.getRGBMode();
		float[] sample = new float[color.sampleSize()];
		
		Couleur refCouleur = new Couleur();
		refCouleur.color_init(color, sample);

		//Your_turn b1 = new Your_turn(); //Le robot attends que l'autre lui envoie un signal bluetooth.
		My_turn b2 = new My_turn(); //return false	
		Avoid b3 = new Avoid(); //return false
		Recepteur b5 = new Recepteur(); //Button enter
		Emetteur b6 = new Emetteur(); //Button up
		Stop b4 = new Stop(); //Button left
		Behavior[] bArray = {b5, b6, b2, b3, b4}; // Du moins prioritaire au plus prioritaire.
		Arbitrator arby = new Arbitrator(bArray);
		b4.setArbi(arby);
		b4.setSensor(colorSensor);
		b5.setColor(color);
		b5.setSample(sample);
		b5.setRefCouleur(refCouleur);
		b6.setPos(b5.getPos());
		
		//ColorThread CaptationCouleur = new ColorThread(b5, color ,sample);

		
		LCD.drawString("Je suis prêt!",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation du dï¿½marrage par pression d'un bouton.
		
		//CaptationCouleur.start();
		arby.go();
		}
	}

