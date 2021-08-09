package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;

public class VisualizzazioneRetiHandler {
    
    public boolean controlloRetePresente(GestoreReti listaReti, String nomeRete) {
	boolean presente = false;

	for (String elem : listaReti.getKeyLIst()) {
	    if (elem.equals(nomeRete)) {
		presente = true;
		break;
	    }
	}
	return presente;
    }

}
