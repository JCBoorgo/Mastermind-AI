package app;
/**
 * Jeu en tant que tel
 * @author Jean-Christophr Bourgault
 * @version 1.0
 */
import java.util.Scanner;

public class Jeu {
	private ListePossibles liste = null;
	private Scanner sc = null;
	private boolean victoire = false;
	private int tour = 0;

	public void jouer() {
		initVars();
		System.out.println("Jouons au Mastermind!");
		System.out.println("Choisis une combinaison de " + liste.LARGEUR_GUESS + " couleurs...");
		System.out.println("(gardes-la dans ta tête ou notes-la, mais pas besoin de me l'écrire ;) )");
		String couleurs = "";
		for (int i = 0; i < liste.COULEURS_POSSIBLES.length; i++) {
			couleurs += liste.COULEURS_POSSIBLES[i] + " ";
		}
		System.out.println("Les couleurs permises sont:");
		System.out.println(couleurs);
		System.out.println("Quand c'est fait, appuie sur \"Enter\" (dans la console).");
		System.out.println("(tu as quand même " + liste.combinaisonsPossibles.size() + " choix...)");
		sc.nextLine();
		while (!victoire) {
			System.out.println("TOUR " + tour);
			System.out.println("Mon essai est\n" + liste.choisirGuess());
			System.out.println("Combien ai-je de blancs? (un blanc est une couleur à la bonne place)");
			int[] res = new int[2];
			res[0] = sc.nextInt();
			System.out.println("Et combien de noirs? (un noir est une couleur bonne, mais à la mauvaise place)");
			res[1] = sc.nextInt();
			if (res[0] == liste.LARGEUR_GUESS) {
				victoire = true;
				System.out.println("Haha! J'ai gagné en " + tour + " tours!");
			} else {
				liste.purgerListe(res);
				System.out.println("D'accord, il me reste donc " + liste.combinaisonsPossibles.size() + " choix...");
				tour++;
			}
		}
	}

	public void initVars() {
		liste = new ListePossibles();
		sc = new Scanner(System.in);
		victoire = false;
		tour = 1;
	}
}
