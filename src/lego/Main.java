package lego;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		//float[] s = new float[]{0};
		Behavior b1 = new Deplacer(); // Avancer
		Arreter b2 = new Arreter(); //Arreter -> prioritaire sur Avancer!
		Behavior[] bArray = {b1, b2}; // du moins prioritaire au plus prioritaire
		Arbitrator arby = new Arbitrator(bArray);
		b2.setArbi(arby);
		
		LCD.drawString("Je suis pas petit",0,4);
		LCD.refresh();
		Button.waitForAnyPress();
		
		
		arby.go();
		}
	}

