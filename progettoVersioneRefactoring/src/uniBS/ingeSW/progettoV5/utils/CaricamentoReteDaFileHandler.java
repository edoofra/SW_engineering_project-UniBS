package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class CaricamentoReteDaFileHandler {
    
    public Rete caricaDaFile(String path) {
	Rete reteCaricata = LetturaFile.leggiReteDaFile(path); //modificato per single resp
	return reteCaricata;
    }
    
    public void aggiuntaReteAGestore(GestoreReti listaReti, String nomeRete, Rete reteCaricata) throws giaPresenteException {
	listaReti.addRete(nomeRete, reteCaricata);
        for(String name : listaReti.getKeyLIst()){
            String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiConfiguratore().get(name));
            salvataggioFile.salvaRete(reteJSON,name);
        } 
    }

}
