package uniBS.ingeSW.progettoV4.view;

import uniBS.ingeSW.progettoV4.logica.gestioneReti.GestoreRetiPetriPriorita;

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
