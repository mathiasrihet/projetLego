package lego;
import lejos.robotics.subsumption.Behavior;

//Comportement pour �viter un obstacle
public class Avoid implements Behavior{
	
	public boolean takeControl() {
		//Contr�le d�clench� par la d�tection d'un obstacle par le capteur ultrason
		return false;
	}
	
	public void suppress() {
	}

    
	public void action() {
		//Deux cas
		//Si la destination est de l'autre c�t� de l'obstacle, tourne, avance/recule un peu, tourne
		//Sinon, tourne
		
	}
}