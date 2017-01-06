package app;

import java.util.Scanner;

public class Jeu {
	private ListePossibles liste = null;
	private Scanner sc = null;
	private boolean victoire = false;
	private int tour = 0;

	public void jouer() {
		initVars();
		System.out.println("Jouons au Mastermind!");
		System.out.println("Choisis une combinaison de " + liste.largeurGuess + " couleurs...");
		System.out.println("(gardes-la dans ta t�te ou notes-la, mais pas besoin de me l'�crire ;) )");
		String couleurs = "";
		for (int i = 0; i < liste.couleursPossibles.length; i++) {
			couleurs += liste.couleursPossibles[i] + " ";
		}
		System.out.println("Les couleurs permises sont:");
		System.out.println(couleurs);
		System.out.println("Quand c'est fait, appuie sur \"Enter\" (dans la console).");
		System.out.println("(tu as quand m�me " + liste.combinaisonsPossibles.size() + " choix...)");
		sc.nextLine();
		while (!victoire) {
			System.out.println("TOUR " + tour);
			System.out.println("Mon essai est\n" + liste.choisirGuess());
			System.out.println("Combien ai-je de blancs? (un blanc est une couleur � la bonne place)");
			int[] res = new int[2];
			res[0] = sc.nextInt();
			System.out.println("Et combien de noirs? (un noir est une couleur bonne, mais � la mauvaise place)");
			res[1] = sc.nextInt();
			if (res[0] == liste.largeurGuess) {
				victoire = true;
				System.out.println("Haha! J'ai gagn� en " + tour + " tours!");
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
