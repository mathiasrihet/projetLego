package lego;

import java.lang.Thread;

public class ThreadColor extends Thread{​​​​​

		ThreadColor() {this.start();}// Constructeur

		// On redéfinit la méthode run(

		public void run() {
			Recepteur.setColorA(Couleur.colorRGB());
		}​​​​​
		​​
}​​​​​

