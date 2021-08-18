package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class CaricamentoDaFileHandler {
    
    InterfaceTipoCaricamentoDaFile caricamento;
    
    public ReteSemplice caricaReteDaFile(String path) {
	caricamento = new CaricamentoReteSempliceDaFile();
	return (ReteSemplice)caricamento.caricaReteDaFile(path);
    }
    
    public void aggiuntaReteAGestore(GestoreReti listaReti, String nomeRete, ReteSemplice reteCaricata) throws giaPresenteException {
	listaReti.addRete(nomeRete, reteCaricata);
        for(String name : listaReti.getKeyLIst()){
            String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiConfiguratore().get(name));
            salvataggioFile.salvaRete(reteJSON,name);
        } 
    }
    
    public RetePetri caricaPNDaFile(String path) {
	caricamento = new CaricamentoPNDaFile();
	return (RetePetri)caricamento.caricaReteDaFile(path);
       }
    
    public void aggiuntaPNAGestore(GestoreRetiPetri listaReti, String nomeRete, RetePetri reteCaricata) throws giaPresenteException {
   	listaReti.addRete(nomeRete, reteCaricata);
           for(String name : listaReti.getKeyLIst()){
               String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiPetriConfiguratore().get(name));
               salvataggioFile.salvaRetePetri(reteJSON,name);
           } 
       }
    
    public boolean controlloAccettazioneRetePetri(RetePetri reteCaricata, GestoreReti listaReti){
        ReteSemplice reteBase = new ReteSemplice(reteCaricata.getInsiemePosti(), reteCaricata.getInsiemeTransizioni(), reteCaricata.getRelazioneFlusso());
        for(String nomeRete : listaReti.getKeyLIst()){
            if(listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(reteBase)){
                return true;
            }
        }
        return false;
    }
    
    public RetePetriPriorita caricaPNPDaFile(String path) {
	caricamento = new CaricamentoPNPDaFile();
	return (RetePetriPriorita)caricamento.caricaReteDaFile(path);
       }
    
    public void aggiuntaPNPAGestore(GestoreRetiPetriPriorita listaReti, String nomeRete, RetePetriPriorita reteCaricata) throws giaPresenteException {
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
