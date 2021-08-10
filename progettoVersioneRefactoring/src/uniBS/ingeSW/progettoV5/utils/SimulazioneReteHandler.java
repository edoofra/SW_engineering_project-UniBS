package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;

public class SimulazioneReteHandler {
    
    public void simulaEvoluzione(RetePetri reteScelta, String nomeElemFlussoScelto) throws NonPresenteException {
	ElemFlusso elemScelto = reteScelta.getElemFlussoByName(nomeElemFlussoScelto);
        reteScelta.aggiornaMarcaturaPerSimulazione(elemScelto);
    }
}
