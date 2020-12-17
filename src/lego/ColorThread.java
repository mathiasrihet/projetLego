package lego;
import java.lang.Thread;

import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

//Definition d'un Thread pour capter la couleur en temps réel
public class ColorThread extends Thread {
	private Recepteur cible;
	private SampleProvider color;
	private float[] sample;
	private Couleur refCouleur;
	
	ColorThread(Recepteur cible, SampleProvider color, float[] sample, Couleur refCouleur){
		this.cible = cible;
		this.color = color;
		this.sample = sample;
		this.refCouleur = refCouleur;
	}
	
	public void run() {
		cible.setColorA(refCouleur.colorRGB(color, sample));
		Couleur.printColor(cible.getColorA());
		Delay.msDelay(500);
	}
}
