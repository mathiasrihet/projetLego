package lego;
import lejos.robotics.subsumption.Behavior;

public class Avoid implements Behavior{
	
	public boolean takeControl() {
		//Contr�le d�clench� par la d�tection d'un obstacle par le capteur ultrason
		return false;
	}
	
	public void suppress() {
	}

    
	public void action() {
		//Deux cas
		//Si la destination est de l'autre c�t� de l'obstacle, tourne vers la droite, avance/recule un peu, tourne vers la gauche
		//Sinon, tourne vers la droite
		
	}
}