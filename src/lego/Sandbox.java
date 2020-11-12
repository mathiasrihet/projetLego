package lego;

public class Sandbox {

	public static void main(String[] args) {
		//Variables: sa position, la position de l'autre robot, la couleur demandée
		int couleur = 4;
		int[][] position = {{4, 1},{3,0}};
		int[][] obstacle = {{3, 5},{4, 6}};
		
		//Le robot cherche la case la plus proche de la couleur demandée (non-occupée)
		int [] destination = Utils.lookFor(couleur, position, obstacle);
		
		System.out.println(destination[0] + " " + destination[1]);
		
		System.out.println(destination [Utils.is_parallel_to(position)]-position[0][Utils.is_parallel_to(position)]);
		
		System.out.println(Utils.colorchoice());
	}
	
}
