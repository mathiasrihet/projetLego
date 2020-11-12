package lego;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Your_turn implements Behavior{
	
	public boolean takeControl() {
		return true; 		//Le robot exécute ce comportement s'il n'a rien de mieux à faire
	}
	
	public void suppress() {
		LCD.clear();
	}

    
	public void action() {
		//Le robot attend de recevoir un message bluetooth
		LCD.drawString("Et maintenant ?",0,4);
		LCD.refresh();
		
	}
}