package lego;
import java.lang.Thread;

import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorThread extends Thread {
	private Recepteur obj;
	private SampleProvider color;
	private float[] sample;
	
	ColorThread(Recepteur obj, SampleProvider color, float[] sample){
		this.obj = obj;
		this.color = color;
		this.sample = sample;
	}
	
	public void run() {
		obj.setColorA(Couleur.colorRGB(color, sample));
		Couleur.printColor(obj.getColorA());
		Delay.msDelay(500);
	}
}
