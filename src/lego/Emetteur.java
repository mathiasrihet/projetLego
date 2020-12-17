package lego;


 

import java.io.OutputStream;

import lejos.hardware.Button;

import lejos.hardware.ev3.EV3;

import lejos.hardware.ev3.LocalEV3;

import lejos.hardware.lcd.LCD;

import lejos.remote.nxt.BTConnection;

import lejos.remote.nxt.BTConnector;

import lejos.remote.nxt.NXTConnection;

import lejos.robotics.subsumption.Behavior;


 

import java.io.ObjectOutputStream;


//Comportement qui envoir une couleur
public class Emetteur implements Behavior{
	private int[][] position;
	
public void setPos(int[][] position) {
	this.position = position;
}


public boolean takeControl() {
return Button.UP.isDown();
}


public void suppress() {
}



public void action() {

        String connected = "Connected";
        String waiting = "Waiting";
        EV3 ev = LocalEV3.get();

        System.out.println("mode emetteur");

        try {


            BTConnector bt = new BTConnector();

            BTConnection btc = bt.connect("00:16:53:82:76:33", NXTConnection.PACKET);//le premier parametre est l'adresse du recepteur affiche sur l'ecran de l'emetteur apres association (pair) bluetooth

 //Adresse de Hodor15


 

            LCD.clear();

            LCD.drawString(connected, 0, 0);

            LCD.refresh();


            OutputStream os = btc.openOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(os);




                /* On utilise la norme suivante:

                 * 0 = "Rouge"

                 * 1 = "Bleu"

                 * 2 = "Vert"

                 * 3 = "Orange"

                 * 4 = "Blanc"

                 */

            int nombreAleatoire = (int)(Math.random() * 5);
            //int nombreAleatoire = 4; //pour les tests


            Couleur.printColor(nombreAleatoire);
            
            //(x1, y1) sont les coordonnes du capteur couleur
            int x1 = this.position[0][0];
            int y1 = this.position[0][1];
            
            //(x2, y2) sont les coordonnes du point du robot les plus eloigne du capteur couleur
            int x2 = this.position[1][0];
            int y2 = this.position[1][1];


            //Objet envoyé par bluetooth
            int[] myIntArray = new int[]{nombreAleatoire,x1,y1,x2,y2};



 
            System.out.println("\n\nEnvoi en cours ");
            LCD.refresh();

            oos.writeObject(myIntArray); // ecrit une valeur dans le flux

            oos.flush(); // force l envoi

            System.out.println("\nEnvoie fini");
            LCD.refresh();

            oos.close();

            btc.close();


        } catch (Exception e) {

        System.out.println(e);

        }

    }


 

 


 

}