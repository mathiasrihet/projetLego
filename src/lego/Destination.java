package lego;

public class Destination {

	public static void main(String[] args) {
		int couleur = 3;
		int[][] position = {{0, 0},{-1,-1}};
		int[][] obstacle = {{1, 3},{2, 2}};
		Case.lookFor(couleur, position, obstacle);
	}


}
