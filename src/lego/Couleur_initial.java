package lego;
<<<<<<< Updated upstream
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;


public class Couleur_initial {
	
	
	private float[] initial_color() {
		Port s3 = LocalEV3.get().getPort("S3");
    	EV3ColorSensor colorSensor = new EV3ColorSensor(s3);

    	SampleProvider color =  colorSensor.getRGBMode();
    	float[] sample = new float[color.sampleSize()];
    	color.fetchSample(sample, 0);
    	
    	float RedValue = sample[0] ;
    	float GreenValue = sample[1] ;
    	float BlueValue = sample[2] ;


    	LCD.drawString("R: "+ Math.round(RedValue * 100.0) + "\n V: " +  Math.round(GreenValue * 100.0) + "\n B:" +  Math.round(BlueValue * 100.0),0,4);
    	Button.waitForAnyPress();

    	colorSensor.close();
    	
    	return sample
    }

	public static void main(String[] args) {
		

		
		
		LCD.drawString("Mettre sur rouge :",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.
        float[] red_initial = initial_color();
        
        LCD.drawString("Mettre sur bleu :",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.
        float[] blue_initial = initial_color();
        
        LCD.drawString("Mettre sur vert :",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.
        float[] green_initial = initial_color();
        
        LCD.drawString("Mettre sur orange :",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.
        float[] orange_initial = initial_color();
        
        LCD.drawString("Mettre sur blanc :",0,4);
		LCD.refresh();
		Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.
        float[] white_initial = initial_color();
		
		
	}
	
}
=======

import lejos.hardware.Button;

import lejos.hardware.ev3.LocalEV3;

import lejos.hardware.lcd.LCD;

import lejos.hardware.port.Port;

import lejos.hardware.sensor.EV3ColorSensor;

import lejos.robotics.SampleProvider;


 


 

public class Couleur_initial {

 

static float[] red_initial = new float[]{255,160,122};

    static float[] blue_initial = new float[]{30,144,255};

    static float[] green_initial = new float[]{50,205,50};

    static float[] orange_initial = new float[]{255,165,0};

    static float[] white_initial = new float[]{255,255,255};

 

 

private static float[] initial_color() {

Port s3 = LocalEV3.get().getPort("S3");

    EV3ColorSensor colorSensor = new EV3ColorSensor(s3);


 

    SampleProvider color =  colorSensor.getRGBMode();

    float[] sample = new float[color.sampleSize()];

    color.fetchSample(sample, 0);

     

    float RedValue = sample[0] ;

    float GreenValue = sample[1] ;

    float BlueValue = sample[2] ;


 


 

    LCD.drawString("R: "+ Math.round(RedValue * 100.0) + "\n V: " +  Math.round(GreenValue * 100.0) + "\n B:" +  Math.round(BlueValue * 100.0),0,4);

    Button.waitForAnyPress();


 

    colorSensor.close();

     

    return sample;

    }


 

public static void main(String[] args) {


LCD.drawString("Mettre sur rouge :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        red_initial = initial_color();

        

        LCD.drawString("Mettre sur bleu :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        blue_initial = initial_color();

        

        LCD.drawString("Mettre sur vert :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        green_initial = initial_color();

        

        LCD.drawString("Mettre sur orange :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        orange_initial = initial_color();

        

        LCD.drawString("Mettre sur blanc :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        white_initial = initial_color();

 

 

}

 

}
>>>>>>> Stashed changes
