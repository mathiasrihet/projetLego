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
	//Main avec les 3 "vrais" Behavior ainsi qu'un exemple du découpage initialement envisagé

	public static void main(String[] args) {
		//Création des variables nécessaires au bon fonctionnement du capteur couleur
		Port s3 = LocalEV3.get().getPort("S3");
		EV3ColorSensor colorSensor = new EV3ColorSensor(s3);
		SampleProvider color =  colorSensor.getRGBMode();
		float[] sample = new float[color.sampleSize()];
		
		//Creation d'un reference pour les couleurs et initialisation de celle-ci
		Couleur refCouleur = new Couleur();
		refCouleur.color_init(color, sample);

		

		Recepteur b1 = new Recepteur(); //Button enter, le robot attend que l'autre lui envoie un signal bluetooth puis cherche à atteindre la bonne case.
		Emetteur b2 = new Emetteur(); //Button up, le robot envoie un signal bluetooth à son compagnon.
		Stop b3 = new Stop(); //Button left, le robot s'arrete.
		Behavior[] bArray = {b1, b2, b3}; // Du moins prioritaire au plus prioritaire.
		
		//My_turn b4 = new My_turn(); //return false, comportement non developpe	
		//Avoid b5 = new Avoid(); //return false, comportement non developpe
		//Behavior[] bArray = {b1, b4, b5, b3}; // Arbitrateur initialement envisagé
		
		
		Arbitrator arby = new Arbitrator(bArray);
		
		//Initialisation de divers proprietes des objets
		b3.setArbi(arby);
		b3.setSensor(colorSensor);
		b1.setColor(color);
		b1.setSample(sample);
		b1.setRefCouleur(refCouleur);
		b2.setPos(b1.getPos());
		
		//ColorThread CaptationCouleur = new ColorThread(b5, color ,sample); Thread non implemente

		
		LCD.drawString("Je suis prêt!",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation du demarrage par pression d'un bouton.
		
		//CaptationCouleur.start();Thread non implemente
		arby.go();
		}
	}

