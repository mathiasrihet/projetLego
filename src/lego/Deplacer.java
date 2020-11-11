package lego;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.*;
//Classe � supprimer




public class Deplacer implements Behavior {
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
    Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
    Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, 2);
    MovePilot pilot = new MovePilot(chassis);
    
    private void travel(int unite) {
    	double epaisseurTrait = 1.5;
    	double epaisseurCase = 12;
    	
    	double distance = unite*(epaisseurTrait+epaisseurCase)*10;
    	pilot.travel(distance);
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
    
    @SuppressWarnings("unused")
	private String colorRGB() {
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
    	
    	//sample couleur détecter par le robot

    	// couleurs en mémoire
        float[] red = new float[]{255,160,122};
        float[] blue = new float[]{30,144,255};
        float[] green = new float[]{50,205,50};
        float[] orange = new float[]{255,165,0};
        float[] white = new float[]{255,255,255};
        

        float cosRed = cosineSimilarityRGB(  sample, red); 
        float cosBlue = cosineSimilarityRGB(  sample, blue);
        float cosGreen = cosineSimilarityRGB(  sample, green);
        float cosOrange = cosineSimilarityRGB(  sample, orange);
        float cosWhite = cosineSimilarityRGB(  sample, white);
        float cosMax = Math.max(Math.max(Math.max(cosRed,cosBlue),Math.max(cosGreen,cosOrange)),cosWhite);
        String colorString = "";
        if (cosMax == cosRed ){
            colorString = "Rouge";
        }
        if (cosMax == cosBlue){
            colorString = "Bleu";
        }
        if (cosMax == cosGreen ){
            colorString = "Vert";
        }
        if (cosMax == cosOrange ){
            colorString = "Orange";
        }
        if (cosMax == cosWhite ){
            colorString = "Blanc";
        }
        System.out.println(colorString);
    	return colorString ;
    }
    
    
	@Override
	public boolean takeControl() {
		return true;	
	}
	
	@Override
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}
	
	@Override
	public void action() {
		pilot.setLinearSpeed(20);
		this.travel(2);
	}
}

