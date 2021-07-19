package uniBS.ingeSW.progettoV4.view;

import uniBS.ingeSW.progettoV4.logica.gestioneReti.GestoreRetiPetriPriorita;

/**
 * Classe per la gestione della <em>presentazione </em> della classe GestoreRetiPetriPriorita.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */

public class GestoreRetiPetriPrioritaPresentation {
    
    GestoreRetiPetriPriorita daPresentare;

    public GestoreRetiPetriPrioritaPresentation(GestoreRetiPetriPriorita daPresentare) {
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
