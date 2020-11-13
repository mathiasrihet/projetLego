package lego;

 
import java.io.DataInputStream;
import java.io.InputStream;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

 

public class Recepteur {

 

    public static void main(String[] args) {
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
            DataInputStream dis = new DataInputStream(is);
            //DataOutputStream dos = new DataOutputStream(os);

 

            int valeur = dis.read();

 


            dis.close();
            //dos.close();
            btc.close();

 

            String colorString;
            switch (valeur) {
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
        }
    }

 

}