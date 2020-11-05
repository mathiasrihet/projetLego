package lego;


public class Case {
	
	/* On utilise la norme suivante:
	 * 0 = "Rouge"
	 * 1 = "Bleu"
	 * 2 = "Vert"
	 * 3 = "Orange"
	 * 4 = "Blanc"
	 */

	private static final int[][] tapis = {
			{0,1,1,1,1,1,1},
			{2,2,2,2,3,3,1},
			{1,2,3,4,1,2,1},
			{1,2,3,2,2,2,1},
			{1,1,1,1,1,0,1}
	  };

		
	   public static void lookFor (int couleur, int[][] position, int[][] obstacle) {
		   int[] closest = {5, 7, 12};
		   for(int x = 0; x<5; x++) {
			   for(int y = 0; y<7; y++) {
				   if (Case.tapis[x][y] == couleur) {					   
					   int diff = Math.abs(x-position[0][0])+ Math.abs(y-position[0][1]);
					   int[] pos = {x, y, diff};
					   if (pos[2]<closest[2]) {
						   if (!((x==obstacle[0][0] || x==obstacle[1][0])&&(y==obstacle[0][1] || y==obstacle[1][1]))) {							   
							  closest = pos;							   
						   }
					   }
				   }
			   }
		   }
		   if (closest[2] == 12) {
			   System.out.println("Erreur: aucune case de la couleur demandée n'est accessible");
	   }
		   else {
			   System.out.println(closest[0]+" "+closest[1]+" "+closest[2]);
			   }
		   }

}
