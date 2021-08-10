package uniBS.ingeSW.progettoV5.utils;


import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;

public class VisualizzazioneRetiPetriPrioritaHandler {

    public boolean controlloRetePetriPrioritaPresente(String nomeReteDaVisualizzare, GestoreRetiPetriPriorita listaReti) {
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
