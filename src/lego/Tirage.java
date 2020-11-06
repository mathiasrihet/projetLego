package lego;

public class Tirage {
	public static void main(String[] args) {
		Random rand = new Random();
		int couleur = rand.nextInt(5);
		System.out.println(couleur);
	}
}
