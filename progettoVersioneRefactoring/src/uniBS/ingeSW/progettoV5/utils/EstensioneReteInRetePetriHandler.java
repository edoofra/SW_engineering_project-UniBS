package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;

public class EstensioneReteInRetePetriHandler {
    
    public boolean controlloRetePresente(GestoreReti listaReti, String nomeReteDaEstendere) {
	boolean presente = false;
	for (String nomeRete : listaReti.getKeyLIst()) {
	    if (nomeRete.equalsIgnoreCase(nomeReteDaEstendere)) {
		presente = true;
		break;
	    }
	}
	return presente;
    }
    
    public RetePetri creazioneRetePetri(GestoreReti listaReti, GestoreRetiPetri listaPetri, String nomeReteDaEstendere) {
	Rete reteScelta = listaReti.getListaRetiConfiguratore().get(nomeReteDaEstendere);        
        RetePetri retePN = listaPetri.creaRetePetri(reteScelta);
        return retePN;
    }
    
    public boolean controlloPostoPresente(RetePetri retePN, String nome) {
	boolean presente = false;
	for(Posto posto : retePN.getMarcatura().getListaPosti()){
            if(posto.getName().equalsIgnoreCase(nome)){
                presente = true;
                break;
            }
        }
	return presente;
    }
    
    public void impostaNuovaMarcatura(RetePetri retePN,String nome, int nuovoValore) {
	retePN.getMarcatura().impostaNuovaMarcatura(nome, nuovoValore);
    }

}
