package lego;
import java.lang.Thread;

public class ColorThread extends Thread {
	
	public void run() {
		Recepteur.setColorA(Couleur.colorRGB());
		Couleur.printColor(Recepteur.getColorA());
	}
}
