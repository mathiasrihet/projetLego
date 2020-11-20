package lego;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	//Main avec les 4 Behavior

	public static void main(String[] args) {
		
		Your_turn b1 = new Your_turn(); //Le robot attends que l'autre lui envoie un signal bluetooth.
		My_turn b2 = new My_turn(); //Le robot a reï¿½u un signal bluetooth et effectue son tour de jeu (i.e. va se placer ï¿½ la case la plus proche de la couleur indiquï¿½e).		
		Avoid b3 = new Avoid(); //Le robot a dï¿½tectï¿½ un obstacle et se met en position pour le contourner
		Recepteur b5 = new Recepteur();
		Emetteur b6 = new Emetteur();
		Stop b4 = new Stop(); //Le robot s'interrompt suite ï¿½ une pression sur le bouton gauche.
		Behavior[] bArray = {b5, b6, b1, b2, b3, b4}; // Du moins prioritaire au plus prioritaire.
		Arbitrator arby = new Arbitrator(bArray);
		b4.setArbi(arby);
		
		Couleur.color_init();
		
		LCD.drawString("Je suis prêt!",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation du dï¿½marrage par pression d'un bouton.
		
		
		arby.go();
		}
	}

