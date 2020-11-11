package lego;
import lejos.robotics.subsumption.Behavior;

public class Your_turn implements Behavior{
	
	public boolean takeControl() {
		return true; 		//Le robot exécute ce comportement s'il n'a rien de mieux à faire
	}
	
	public void suppress() {
	}

    
	public void action() {
		//Le robot attend de recevoir un message bluetooth
		
	}
}