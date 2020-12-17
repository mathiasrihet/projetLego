package lego;
import lejos.robotics.subsumption.Behavior;

//Comportement pour éviter un obstacle
public class Avoid implements Behavior{
	
	public boolean takeControl() {
		//Contrôle déclenché par la détection d'un obstacle par le capteur ultrason
		return false;
	}
	
	public void suppress() {
	}

    
	public void action() {
		//Deux cas
		//Si la destination est de l'autre côté de l'obstacle, tourne, avance/recule un peu, tourne
		//Sinon, tourne
		
	}
}