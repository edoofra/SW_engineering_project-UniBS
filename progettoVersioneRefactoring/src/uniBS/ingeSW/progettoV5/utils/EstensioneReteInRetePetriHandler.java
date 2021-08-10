package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;
import uniBS.ingeSW.progettoV5.view.ElemFlussoPresentation;

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
    
    public boolean controlloElementoPresente(RetePetri retePN, String nome) {
	boolean presente =false;
	for (ElemFlusso elem : retePN.getListaPesi().getListaElemFlusso()) {
	    if (new ElemFlussoPresentation(elem).getName().equalsIgnoreCase(nome)) {
		presente = true;
		break;
	    }
	}
	return presente;
    }
    
    public void impostaNuovoPeso(RetePetri retePN,String nome, int nuovoValore) throws NonPresenteException {
	ElemFlusso elemDaCambiare = retePN.getElemFlussoByName(nome);
        retePN.getListaPesi().impostaPeso(elemDaCambiare.getElem1().getName(), elemDaCambiare.getElem2().getName(), nuovoValore);
    }
    
    public boolean controlloRetePetriDuplicata(RetePetri daControllare, GestoreRetiPetri listaReti){
        assert daControllare !=null && listaReti !=null;

        for(String nomeRete : listaReti.getListaRetiPetriConfiguratore().keySet().toArray(new String[0])){
            if(daControllare.controlloRetePetriUguale(listaReti.getListaRetiPetriConfiguratore().get(nomeRete))) return true;
        }
        return false;
    }
    
    public void salvataggioRetePN(RetePetri retePN, GestoreRetiPetri listaPetriPN, String nomeSalvataggio) throws giaPresenteException {
	listaPetriPN.addRete(nomeSalvataggio, retePN);
        for(String name : listaPetriPN.getKeyLIst()){
            String retePNJSON = ConvertitoreJson.daOggettoAJson(listaPetriPN.getListaRetiPetriConfiguratore().get(name));
            salvataggioFile.salvaRetePetri(retePNJSON, name);
        }
        String listaRetiPNJSON = ConvertitoreJson.daOggettoAJson(listaPetriPN);
        salvataggioFile.salvaGestoreReti(listaRetiPNJSON, 1);
    }

}
