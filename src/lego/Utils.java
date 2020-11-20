package lego;
import java.util.Random;

//Classe avec différentes fonctions permettant au robot de tester certaines choses

public class Utils {
	
	/* On utilise la norme suivante:
	 * 0 = "Rouge"
	 * 1 = "Bleu"
	 * 2 = "Vert"
	 * 3 = "Orange"
	 * 4 = "Blanc"
	 */
	
	//Tapis de jeu
	private static final int[][] tapis = {
			{0,1,1,1,1,1,1},
			{2,2,2,2,3,3,1},
			{1,2,3,4,1,2,1},
			{1,2,3,2,2,2,1},
			{1,1,1,1,1,0,1}
	  };

	
   //Retourne la case la plus proche de la couleur demandée		
   public static int[] lookFor (int couleur, int[][] position, int[][] obstacle) {

	   int[] closest = {5, 7, 12};
	   for(int x = 0; x<5; x++) {
		   for(int y = 0; y<7; y++) {
			   if (Utils.tapis[x][y] == couleur) {					   
				   int diff = Math.abs(x-position[0][0])+ Math.abs(y-position[0][1]);
				   int[] pos = {x, y, diff};
				   if (pos[2]<closest[2]) {
					   if (!is_other_in(obstacle, x, y)) {							   
						  closest = pos;							   
					   }
				  }
			   }
		   }
	   }
	   if (closest[2] == 12) {
		   System.out.println("Erreur: aucune case de la couleur demandée n'est accessible");
		   return position [0];
   }
	   else {
		   return closest;
		   }
	   }
   
   //Return true si l'autre robot occupe au moins en partie les coordonnées fournies (à retravailler)
   public static boolean is_other_in(int[][] obstacle, int x, int y){
	   if ((x==obstacle[0][0] || x==obstacle[1][0])&&(y==obstacle[0][1] || y==obstacle[1][1])){
		   return true;
	   }
	   else {
		   return false;
	   }
   }
   
   //Nécessaire pour le if de "is_parallel_to"
   public static int sign(int i) {

	   if(i>0) {
		   return 1;
	   }
	   if(i<0) {
		   return -1;
	   }
	   else {
		   return 0;
	   }
   }
   
   //Renvoie 0 si le robot est parallèle à l'axe x, 1 si parallèle à y
   public static int is_parallel_to(int[][] position) {
	   if (Utils.sign(position[0][0]-position[1][0])==Utils.sign(position[0][1]-position[1][1])){
		   return 1;
	   }
	   else {
		   return 0;
	   }
   }
   
   //Retourne l'équivalent int d'une couleur aléatoire
   public static int colorchoice() {
		Random rand = new Random();
		int couleur = rand.nextInt(5);
		return couleur;
   }
}
