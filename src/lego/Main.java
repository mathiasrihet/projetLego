package lego;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	//Main avec les 4 Behavior

	public static void main(String[] args) {

		//Your_turn b1 = new Your_turn(); //Le robot attends que l'autre lui envoie un signal bluetooth.
		My_turn b2 = new My_turn(); //return down	
		Avoid b3 = new Avoid(); //return false
		Recepteur b5 = new Recepteur(); //Button enter
		Emetteur b6 = new Emetteur(); //Button up
		Stop b4 = new Stop(); //Button left
		Behavior[] bArray = {b5, b6, b2, b3, b4}; // Du moins prioritaire au plus prioritaire.
		Arbitrator arby = new Arbitrator(bArray);
		b4.setArbi(arby);

		
		//Couleur.color_init();
		
		LCD.drawString("Je suis prêt!",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation du dï¿½marrage par pression d'un bouton.
		
		
		arby.go();
		}
	}

