package uniBS.ingeSW.progettoV5.utils;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.utils.eccezioni.ErroreFormatoException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class CreazioneReteHandler {

    // CODICE PER PRESENTAZIONE SAETTI
    public void aggiuntaTransizione(Rete daCreare,String nome) throws giaPresenteException {
	assert daCreare != null; // precondizione
	var nuovo = daCreare.creaTransizione(nome); //creator grasp
	daCreare.addTrans(nuovo);
    }

    // CODICE PER PRESENTAZIONE SAETTI
    public void aggiuntaPosto(Rete daCreare, String nome) throws giaPresenteException {
	assert daCreare != null; // precondizione
	var nuovo = daCreare.creaPosto(nome);
	daCreare.addPosto(nuovo);
    }

    // CODICE PER PRESENTAZIONE SAETTI
    public void aggiuntaElemFlusso(Rete daCreare, ElementoSemplice elem1, ElementoSemplice elem2)
	    throws giaPresenteException, NonPresenteException, ErroreFormatoException {
	assert daCreare != null; // precondizione
	ElemFlusso daAggiungere = daCreare.creaElemFlusso(elem1, elem2);
	daCreare.addElemFlusso(daAggiungere);
    }

    public ArrayList<Boolean> controlloRete(Rete daControllare, GestoreReti listaReti){
	
	assert daControllare != null && listaReti != null; // precondizioni
	ArrayList<Boolean> esiti = new ArrayList<Boolean>();
	
	boolean connessa = daControllare.controlloConnessione();
	if (!connessa)
	    esiti.add(false);
	else {
	    esiti.add(true);
	}
	
	boolean corretta = daControllare.controlloCorrettezza();
        if(!corretta) esiti.add(false);
        else {
	    esiti.add(true);
	}
        
	boolean duplicata = false;
	for (String nomeRete : listaReti.getKeyLIst()) {
	    if (listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(daControllare)) {
		duplicata = true;
	    }
	}
	if (duplicata)
	    esiti.add(true);
	else {
	    esiti.add(false);
	}
        return esiti;
    }

    public void salvataggioRete(Rete daSalvare, GestoreReti listaReti, String nomeRete) throws giaPresenteException {
	if (nomeRete != null) {
	    listaReti.addRete(nomeRete, daSalvare);
	    for (String name : listaReti.getKeyLIst()) {
		String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiConfiguratore().get(name));
		salvataggioFile.salvaRete(reteJSON, name);
	    }
	}
    }
    
    public Rete creaRete(GestoreReti listaReti) {
	return listaReti.creaRete(); //creator GRASP
    }

   }
