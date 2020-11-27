package lego;

import java.lang.Thread;

public class ThreadColor extends Thread{​​​​​

		public void ThreadColor() {}// Constructeur

		// On redéfinit la méthode run(

		public void run() {
			Couleur.actual_color = Couleur.colorRGB();
		}​​​​​
		​​

}​​​​​

