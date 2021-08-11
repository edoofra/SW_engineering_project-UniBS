package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class CaricamentoPNDaFileHandler {

    public RetePetri caricaDaFile(String path) {
   	RetePetri reteCaricata = LetturaFile.leggiRetePetriDaFile(path); //modificato per single resp
   	return reteCaricata;
       }
       
       public void aggiuntaReteAGestore(GestoreRetiPetri listaReti, String nomeRete, RetePetri reteCaricata) throws giaPresenteException {
   	listaReti.addRete(nomeRete, reteCaricata);
           for(String name : listaReti.getKeyLIst()){
               String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiPetriConfiguratore().get(name));
               salvataggioFile.salvaRetePetri(reteJSON,name);
           } 
       }
       
       public boolean controlloAccettazioneRetePetri(RetePetri reteCaricata, GestoreReti listaReti){
	        Rete reteBase = new Rete(reteCaricata.getInsiemePosti(), reteCaricata.getInsiemeTransizioni(), reteCaricata.getRelazioneFlusso());
	        for(String nomeRete : listaReti.getKeyLIst()){
	            if(listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(reteBase)){
	                return true;
	            }
	        }
	        return false;
	    }
}
