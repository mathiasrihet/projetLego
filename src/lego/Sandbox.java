package lego;

public class Sandbox {

	public static void main(String[] args) {
		//Variables: sa position, la position de l'autre robot, la couleur demandée
		int couleur = 3;
		int[][] position = {{0, 0},{-1,-1}};
		int[][] obstacle = {{1, 3},{2, 2}};
		
		//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
		int [] destination = Tests.lookFor(couleur, position, obstacle);
		
		System.out.println(destination[0] + " " + destination[1]);
		
		System.out.println(destination [Tests.is_parallel_to(position)]-position[0][Tests.is_parallel_to(position)]);
		
		System.out.println(Tests.colorchoice());
	}
	
}
