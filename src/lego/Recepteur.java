package lego;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;

 

public class Recepteur implements Behavior{

	public boolean takeControl() {
		return Button.ENTER.isDown();		//Le robot exécute ce comportement s'il n'a rien de mieux à faire
	}
	
	public void suppress() {
	}

	public void action() {
        // TODO Auto-generated method stub
        String connected = "Connected";
        String waiting = "Waiting";

 

        try {
            //LCD.drawString(waiting, 0, 0);
            //LCD.refresh();

 

            BTConnector bt = new BTConnector();
            NXTConnection btc = bt.waitForConnection(100000, NXTConnection.PACKET);

 

            if (btc !=null) {
            LCD.clear();
            LCD.drawString(connected, 0, 0);
            LCD.refresh();

 

            InputStream is = btc.openInputStream();
            //OutputStream os = btc.openOutputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            //DataOutputStream dos = new DataOutputStream(os);

 

            int[] valeurs = (int[])ois.readObject();
            int couleur = valeurs[0];
            int x1 = valeurs[1];
            int y1 = valeurs[2];
            int x2 = valeurs[3];
            int y2 = valeurs[4];

 

 

            ois.close();
            //dos.close();
            btc.close();

 

            String colorString;
            switch (couleur) {
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

 

            System.out.println("Couleur reçue : " + colorString);
            Button.RIGHT.waitForPressAndRelease();
            LCD.clear();
            } else {
                System.out.println("Pas de connexion");
                Button.RIGHT.waitForPressAndRelease();
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
    }

 

}