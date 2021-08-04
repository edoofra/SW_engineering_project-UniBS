package uniBS.ingeSW.progettoV5.view;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;

/**
 * Classe per la gestione della <em>presentazione </em> della classe GestoreReti.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
public class GestoreRetiPresentation {
    GestoreReti daPresentare;

    public GestoreRetiPresentation(GestoreReti daPresentare) {
        this.daPresentare = daPresentare;
    }

    public String toString() {
		StringBuilder stringList = new StringBuilder("{ ");
		String[] keyList = daPresentare.getKeyLIst();
		for (int i = 0; i < keyList.length; i++) {
			stringList.append(( keyList[i] + ", "));
		}
		int size = stringList.length();
		stringList.delete(size-2, size-1);
		stringList.append(" }");
		return stringList.toString();

	}
}