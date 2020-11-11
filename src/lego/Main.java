package lego;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	//Main avec les 4 Behavior

	public static void main(String[] args) {
		//float[] s = new float[]{0};
		Your_turn b1 = new Your_turn(); //Le robot attends que l'autre lui envoie un signal bluetooth.
		My_turn b2 = new My_turn(); //Le robot a reçu un signal bluetooth et effectue son tour de jeu (i.e. va se placer à la case la plus proche de la couleur indiquée).
		Avoid b3 = new Avoid(); //Le robot a détecté un obstacle et se met en position pour le contourner
		Stop b4 = new Stop(); //Le robot s'interrompt suite à une pression sur le bouton gauche.
		Behavior[] bArray = {b1, b2, b3, b4}; // Du moins prioritaire au plus prioritaire.
		Arbitrator arby = new Arbitrator(bArray);
		b4.setArbi(arby);
		
		LCD.drawString("Je suis pas petit",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation du démarrage par pression d'un bouton.
		
		
		arby.go();
		}
	}

