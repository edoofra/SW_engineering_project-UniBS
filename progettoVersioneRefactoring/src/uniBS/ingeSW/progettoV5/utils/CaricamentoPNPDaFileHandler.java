package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class CaricamentoPNPDaFileHandler {

    public RetePetriPriorita caricaDaFile(String path) {
   	RetePetriPriorita reteCaricata = LetturaFile.leggiRetePetriPrioritaDaFile(path); //modificato per single resp
   	return reteCaricata;
       }
       
       public void aggiuntaReteAGestore(GestoreRetiPetriPriorita listaReti, String nomeRete, RetePetriPriorita reteCaricata) throws giaPresenteException {
   	listaReti.addRete(nomeRete, reteCaricata);
           for(String name : listaReti.getKeyLIst()){
               String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiPetriPrioritaConfiguratore().get(name));
               salvataggioFile.salvaRetePetriPriorita(reteJSON,name);
           } 
       }
       
       public boolean controlloAccettazioneRetePetriPriorita(RetePetriPriorita reteCaricata, GestoreRetiPetri listaRetiPetri){
	        RetePetri retePetriBase = new RetePetri(reteCaricata.getInsiemePosti(), reteCaricata.getInsiemeTransizioni(), reteCaricata.getRelazioneFlusso(), 
	                                reteCaricata.getMarcatura(), reteCaricata.getListaPesi());
	        for(String nomeRete : listaRetiPetri.getKeyLIst()){
	            if(listaRetiPetri.getListaRetiPetriConfiguratore().get(nomeRete).isEqual(retePetriBase)){
	                return true;
	            }
	        }
	        return false;
	    }
	    
}
