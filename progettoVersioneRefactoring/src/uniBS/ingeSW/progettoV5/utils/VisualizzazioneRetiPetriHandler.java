package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.view.InterazioneUtente;

public class VisualizzazioneRetiPetriHandler {

    public boolean controlloRetePetriPresente(String nomeReteDaVisualizzare, GestoreRetiPetri listaReti) {
	boolean presente = false;
	for (String elem : listaReti.getKeyLIst()) {
	    if (elem.equals(nomeReteDaVisualizzare)) {
		presente = true;
		break;
	    }
	}
	return presente;
    }
}
