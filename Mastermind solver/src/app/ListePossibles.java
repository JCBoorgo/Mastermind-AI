package app;
/**
 * Pour gérer la liste des possibilités
 * @author Jean-Christophr Bourgault
 * @version 1.0
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class ListePossibles {
	public final int LARGEUR_GUESS = 4;
	public final String[] COULEURS_POSSIBLES = { "blanc", "rose", "jaune", "vert", "rouge", "bleu" };

	public ArrayList<String[]> combinaisonsPossibles = null;
	public String[] currentGuess;

	public ListePossibles() {
		initialiserTout();
	}

	private void initialiserTout() {
		// combinaisonsPossibles
		combinaisonsPossibles = new ArrayList<String[]>();
		int[] compteurs = new int[LARGEUR_GUESS];
		for (int i = 0; i < compteurs.length; i++) {
			compteurs[i] = 0;
		}
		boolean fini = false;
		while (!fini) {
			String[] comb = new String[LARGEUR_GUESS];
			for (int i = 0; i < compteurs.length; i++) {
				comb[i] = COULEURS_POSSIBLES[compteurs[i]];
			}
			combinaisonsPossibles.add(comb);
			boolean comptage = true;
			int ajusteur = compteurs.length - 1;
			while (comptage) {
				compteurs[ajusteur]++;
				if (compteurs[ajusteur] >= COULEURS_POSSIBLES.length) {
					compteurs[ajusteur] = 0;
					ajusteur--;
					if (ajusteur == -1) {
						comptage = false;
						fini = true;
					}
				} else {
					comptage = false;
				}
			}
		}
		// currentGuess
		currentGuess = new String[LARGEUR_GUESS];
	}

	public String choisirGuess() {
		String choix = "";
		int choixIndex = -1;
		int plusPetitHitCount = (int) Math.pow(COULEURS_POSSIBLES.length, LARGEUR_GUESS);
		int[] hitCounts = faireHitCounts();
		for (int i = 0; i < hitCounts.length; i++) {
			if (hitCounts[i] < plusPetitHitCount) {
				choixIndex = i;
				plusPetitHitCount = hitCounts[i];
			}
		}
		currentGuess = combinaisonsPossibles.get(choixIndex);
		for (int i = 0; i < currentGuess.length; i++) {
			choix += currentGuess[i] + " ";
		}

		return choix;
	}

	public int[] faireHitCounts() {
		int[] hitCounts = new int[combinaisonsPossibles.size()];
		for (int i = 0; i < combinaisonsPossibles.size(); i++) {
			hitCounts[i] = hitCountIndiv(combinaisonsPossibles.get(i));
		}

		return hitCounts;
	}

	public int hitCountIndiv(String[] comb) {
		int[][] tableau = new int[LARGEUR_GUESS + 1][LARGEUR_GUESS + 1];
		int hitCount = 0;
		for (int i = 0; i < combinaisonsPossibles.size(); i++) {
			int[] res = trouverResultat(comb, combinaisonsPossibles.get(i));
			tableau[res[0]][res[1]]++;
		}
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				if (tableau[i][j] > hitCount)
					hitCount = tableau[i][j];
			}
		}

		return hitCount;
	}

	public int[] trouverResultat(String[] comb1, String[] comb2) {
		// Testé, semble fonctionnel
		// Exprimé en [blanc, noir]
		// Blanc est bonne couleur bonne place, Noir est bonne couleur mais
		// mauvaise place
		// Une combinaison gagnante c'est [4, 0]
		int[] res = { 0, 0 };
		// Setup
		boolean[] matched1 = new boolean[LARGEUR_GUESS];
		boolean[] matched2 = new boolean[LARGEUR_GUESS];
		for (int i = 0; i < matched1.length; i++) {
			matched1[i] = false;
		}
		for (int i = 0; i < matched2.length; i++) {
			matched2[i] = false;
		}
		// Blanc
		for (int i = 0; i < matched1.length; i++) {
			if (comb1[i].equals(comb2[i])) {
				res[0]++;
				matched1[i] = true;
				matched2[i] = true;
			}
		}
		// Noir
		for (int i = 0; i < matched1.length; i++) {
			for (int j = 0; j < matched2.length; j++) {
				if (!matched1[i] && !matched2[j] && comb1[i].equals(comb2[j])) {
					res[1]++;
					matched1[i] = true;
					matched2[j] = true;
				}
			}
		}

		return res;
	}

	public void purgerListe(int[] res) {
		for (int i = combinaisonsPossibles.size() - 1; i >= 0; i--) {
			if (!Arrays.equals(trouverResultat(currentGuess, combinaisonsPossibles.get(i)), res))
				combinaisonsPossibles.remove(i);
		}
	}
}
