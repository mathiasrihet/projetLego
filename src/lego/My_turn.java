package lego;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class My_turn implements Behavior{
	//Variables à trier
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
    	
    	//sample couleur dÃ©tecter par le robot

    	// couleurs en mÃ©moire
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
    //Variables à trier jusque là
    
    
	
	public boolean takeControl() {
		return Button.RIGHT.isDown();
	}
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}

    
	public void action() {
		//Variables: sa position, la position de l'autre robot, la couleur demandée
		int couleur = 4;
		int[][] position = {{4, 1},{3,0}};
		int[][] obstacle = {{3, 5},{4, 6}};
		
		//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
		int [] destination = Utils.lookFor(couleur, position, obstacle);
		
		//Le robot se déplace sur l'axe avec lequel il est aligné pour se rapprocher de la case
		if (Utils.sign(destination[Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)])== -1) {
			pilot.rotate(180);
		}
		pilot.setLinearSpeed(20);
		this.travel(Math.abs(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]));
		
		//Le robot tourne pour se déplacer sur l'autre axe
		//this.rotate() à écrire
		
		//Puis avance sur cet axe (avec lequel il est maintenant parallèle)
		//this.travel(destination [Tests.is_parallel_to(position)]-position[0][Tests.is_parallel_to(position)]);
		
		//On est arrivé ! Plus qu'à tirer un numéro et l'envoyer à l'autre robot !
		Utils.colorchoice();
		
		//Note: si le robot rencontre un obstacle, le comportement "Avoid" doit récupérer la priorité
		
	}
}