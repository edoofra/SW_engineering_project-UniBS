package uniBS.ingeSW.progettoV5.utils;


import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class EstensionePNInPNPHandler {
    
    public boolean controlloRetePresente(GestoreRetiPetri listaReti, String nomeReteDaEstendere) {
	boolean presente = false;
	for (String nomeRete : listaReti.getListaRetiPetriConfiguratore().keySet()) {
	    if (nomeRete.equalsIgnoreCase(nomeReteDaEstendere)) {
		presente = true;
		break;
	    }
	}
	return presente;
    }
    
    public RetePetriPriorita creazioneRetePNP(GestoreRetiPetri listaPN, String nomeReteDaEstendere, GestoreRetiPetriPriorita listaPNP) {
	RetePetri reteScelta = listaPN.getListaRetiPetriConfiguratore().get(nomeReteDaEstendere);        
        RetePetriPriorita retePNPriorita = listaPNP.creaRetePetriPriorita(reteScelta);
        return retePNPriorita;
    }
    
    public boolean controlloTransizionePresente(RetePetriPriorita retePNP, String nome) {
	boolean presente = false;
	for(Transizione trans : retePNP.getPriorita().getListaTransizioni()){
            if(trans.getName().equalsIgnoreCase(nome)){
                presente = true;
                break;
            }
        }
	return presente;
    }
    
    public void impostaNuovaPriorita(RetePetriPriorita retePNP,String nome, int nuovoValore) {
	retePNP.getPriorita().impostaNuovaPriorita(nome, nuovoValore);
    }
    
    public boolean controlloRetePetriPrioritaDuplicata(RetePetriPriorita daControllare, GestoreRetiPetriPriorita listaReti){
        assert daControllare != null && listaReti != null;

        for(String nomeRete : listaReti.getListaRetiPetriPrioritaConfiguratore().keySet().toArray(new String[0])){
            if(daControllare.controlloRetePetriPrioritaUguale(listaReti.getListaRetiPetriPrioritaConfiguratore().get(nomeRete))) return true;
        }
        return false;
    }
    
    public void salvataggioRetePNP(RetePetriPriorita retePNP, GestoreRetiPetriPriorita listaPetriPNP, String nomeSalvataggio) throws giaPresenteException {
	 listaPetriPNP.addRete(nomeSalvataggio, retePNP);
         for(String name : listaPetriPNP.getKeyLIst()){
             String retePNJSON = ConvertitoreJson.daOggettoAJson(listaPetriPNP.getListaRetiPetriPrioritaConfiguratore().get(name));
             salvataggioFile.salvaRetePetriPriorita(retePNJSON, name);
         }
    }


}
