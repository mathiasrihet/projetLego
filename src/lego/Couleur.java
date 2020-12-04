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

 

	private float[] red_initial = new float[]{255,160,122};

    private float[] blue_initial = new float[]{30,144,255};

    private float[] green_initial = new float[]{50,205,50};

    private float[] orange_initial = new float[]{255,165,0};

    private float[] white_initial = new float[]{255,255,255};



 

private float[] initial_color(SampleProvider color, float[] sample) {

    color.fetchSample(sample, 0);

     

    float RedValue = sample[0] ;

    float GreenValue = sample[1] ;

    float BlueValue = sample[2] ;



    LCD.drawString("R: "+ Math.round(RedValue * 100.0) + "\n V: " +  Math.round(GreenValue * 100.0) + "\n B:" +  Math.round(BlueValue * 100.0),0,4);

    Button.waitForAnyPress();


     

    return sample;

    }


 

public void color_init(SampleProvider color, float[] sample) {


LCD.drawString("Mettre sur rouge :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        this.red_initial = initial_color(color, sample);

        

        LCD.drawString("Mettre sur bleu :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        this.blue_initial = initial_color(color, sample);

        

        LCD.drawString("Mettre sur vert :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        this.green_initial = initial_color(color, sample);

        

        LCD.drawString("Mettre sur orange :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        this.orange_initial = initial_color(color, sample);

        

        LCD.drawString("Mettre sur blanc :",0,4);

LCD.refresh();

 Button.waitForAnyPress();//Le robot attend confirmation par pression d'un bouton.

        this.white_initial = initial_color(color, sample);

 

 

}

private float cosineSimilarityRGB(float[] vectorA, float[] vectorB) {
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


public int colorRGB(SampleProvider color, float[] sample) {
	color.fetchSample(sample, 0);

	
	//sample couleur détecter par le robot
    float cosRed = this.cosineSimilarityRGB(  sample, this.red_initial); 
    float cosBlue = this.cosineSimilarityRGB(  sample, this.blue_initial);
    float cosGreen = this.cosineSimilarityRGB(  sample, this.green_initial);
    float cosOrange = this.cosineSimilarityRGB(  sample, this.orange_initial);
    float cosWhite = this.cosineSimilarityRGB(  sample, this.white_initial);
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
 

public static void printColor(int n) {
    String colorString = "";
    switch (n) {
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
       LCD.clear();
       LCD.drawString("Couleur: " + colorString, 0, 4);
       LCD.refresh();
   	}
}
