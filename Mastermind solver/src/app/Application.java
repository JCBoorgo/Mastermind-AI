package app;

public class Application {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		// À re-enable pour vrai jeu
		jeu.jouer();
		
		// Tests trouverResultat
		/*
		 * ListePossibles liste = new ListePossibles(); int[] resTest = new
		 * int[2]; String[] comb1 = {"1", "3", "3", "4"}; String[] comb2 = {"4",
		 * "3", "2", "3"}; resTest = liste.trouverResultat(comb1, comb2);
		 * System.out.println(resTest[0] + " " + resTest[1]);
		 */
	}
}
