package lego;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.utility.Delay;


public class Couleur {

 

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


 

public static void color_init() {


LCD.drawString("Mettre sur rouge :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        Couleur.red_initial = initial_color();

        

        LCD.drawString("Mettre sur bleu :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        Couleur.blue_initial = initial_color();

        

        LCD.drawString("Mettre sur vert :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        Couleur.green_initial = initial_color();

        

        LCD.drawString("Mettre sur orange :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        Couleur.orange_initial = initial_color();

        

        LCD.drawString("Mettre sur blanc :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        Couleur.white_initial = initial_color();

 

 

}

private static float cosineSimilarityRGB(float[] vectorA, float[] vectorB) {
    float dotProduct = 0;
    float normA = 0;
    float normB = 0;
    for (int i = 0; i < vectorA.length; i++) {
        dotProduct += vectorA[i] * vectorB[i];
        normA += Math.pow(vectorA[i], 2);
        normB += Math.pow(vectorB[i], 2);
    }   
    
    
     	
    return (float) (dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));
    
}


public static int colorRGB() {
	Port s3 = LocalEV3.get().getPort("S3");
	EV3ColorSensor colorSensor = new EV3ColorSensor(s3);

	SampleProvider color =  colorSensor.getRGBMode();
	float[] sample = new float[color.sampleSize()];
	color.fetchSample(sample, 0);


	colorSensor.close();
	
	//sample couleur détecter par le robot
    float cosRed = cosineSimilarityRGB(  sample, Couleur.red_initial); 
    float cosBlue = cosineSimilarityRGB(  sample, Couleur.blue_initial);
    float cosGreen = cosineSimilarityRGB(  sample, Couleur.green_initial);
    float cosOrange = cosineSimilarityRGB(  sample, Couleur.orange_initial);
    float cosWhite = cosineSimilarityRGB(  sample, Couleur.white_initial);
    float cosMax = Math.max(Math.max(Math.max(cosRed,cosBlue),Math.max(cosGreen,cosOrange)),cosWhite);
    
    int colorInt = 5;
    if (cosMax == cosRed ){
        colorInt = 0;
    }
    if (cosMax == cosBlue){
        colorInt = 1;
    }
    if (cosMax == cosGreen ){
        colorInt = 2;
    }
    if (cosMax == cosOrange ){
        colorInt = 3;
    }
    if (cosMax == cosWhite ){
        colorInt = 4;
    }
	return colorInt ;
}
 

}
