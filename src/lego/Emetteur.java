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



public class Emetteur implements Behavior{


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


            //LCD.drawString(waiting, 0, 0);

            //LCD.refresh();

            //droite = 00:16:53:43:4E:26

            //gauche = 00:16:53:43:8E:49

            BTConnector bt = new BTConnector();

            BTConnection btc = bt.connect("00:16:53:82:76:33", NXTConnection.PACKET);//le premier param?tre est l'adresse du r?cepteur affich? sur l'?cra de l'?metteur apr?s association (pair) bluetooth

 //Adresse de Hodor15


 

            LCD.clear();

            LCD.drawString(connected, 0, 0);

            LCD.refresh();



            //InputStream is = btc.openInputStream();

            OutputStream os = btc.openOutputStream();

            //DataInputStream dis = new DataInputStream(is);

            ObjectOutputStream oos = new ObjectOutputStream(os);




                /* On utilise la norme suivante:

                 * 0 = "Rouge"

                 * 1 = "Bleu"

                 * 2 = "Vert"

                 * 3 = "Orange"

                 * 4 = "Blanc"

                 */

            int nombreAleatoire = (int)(Math.random() * 5);


            Couleur.printColor(nombreAleatoire);
 /*           String colorString = " " + nombreAleatoire;

            switch (nombreAleatoire) {

  case 0:  colorString = "Rouge";

          break;

  case 1:  colorString = "Bleu";

          break;

  case 2:  colorString = "Vert";

          break;

  case 3:  colorString = "Orange";

          break;

  case 4:  colorString = "Blanc";

          break;

   default: colorString = "Invalid color";

          break;

            }
*/

 

 


 

            int x1 = 0;

            int y1 = 0;

            int x2 = 1;

            int y2 = 1;



            int[] myIntArray = new int[]{nombreAleatoire,x1,y1,x2,y2};


 

 
            

 
            System.out.println("\n\nEnvoi en cours ");
            LCD.refresh();

            oos.writeObject(myIntArray); // ?crit une valeur dans le flux

            oos.flush(); // force l?envoi

            System.out.println("\nEnvoie fini");
            LCD.refresh();

            //dis.close();

            oos.close();

            btc.close();

            //LCD.clear();


 

 


 

        } catch (Exception e) {

        System.out.println(e);

        }

    }


 

 


 

}