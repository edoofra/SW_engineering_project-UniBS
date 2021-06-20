package uniBS.ingeSW.progettoV2.utils;

import java.io.File;

import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV2.logica.rete.Posto;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;
import uniBS.ingeSW.progettoV2.logica.rete.Transizione;
import uniBS.ingeSW.progettoV2.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV2.utils.eccezioni.ErroreFormatoException;
import uniBS.ingeSW.progettoV2.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV2.utils.eccezioni.giaPresenteException;

public class InterazioneUtenteModel {
    
    private static void aggiuntaTransizione(Rete daCreare) {
		assert daCreare != null; //precondizione
		try{
            boolean risposta = true;
            while (risposta != false) {

                String nome = InterazioneUtente.aggiuntaElemento(1);
                var nuovo = new Transizione(nome);
                daCreare.addTrans(nuovo);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(1);
            }
        }
        catch (giaPresenteException ex){
            ex.printStackTrace();
        }
	}

    private static void aggiuntaPosto(Rete daCreare) {
		assert daCreare != null; //precondizione
		try{
            boolean risposta = true;
            while (risposta != false) {

                String nome = InterazioneUtente.aggiuntaElemento(0);
                var nuovo = new Posto(nome);
                daCreare.addPosto(nuovo);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(0);
            }
        }
        catch (giaPresenteException ex){
            ex.printStackTrace();
        }
	}

    private static void aggiuntaElemFlusso(Rete daCreare) {
		assert daCreare != null; //precondizione
		try{
            boolean risposta = true;
            while (risposta != false) {

                ElementoSemplice elem1 = null;
		        ElementoSemplice elem2 = null;
                String[] nomi = InterazioneUtente.aggiuntaFlusso(daCreare);
                String nome1 = nomi[0];
                String nome2 = nomi[1];
                if(nome1.charAt(0)=='P') elem1 = daCreare.getPostoByName(nome1);
		        else elem1 = daCreare.getTransByName(nome1);
		
                if(nome2.charAt(0)=='P') elem2 = daCreare.getPostoByName(nome2);
                else elem2 = daCreare.getTransByName(nome2);

                daCreare.addElemFlusso(new ElemFlusso(elem1,elem2));

                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(2);
            }
        }
        catch (giaPresenteException ex){
            ex.printStackTrace();
        }
        catch(NonPresenteException ex1){
            ex1.printStackTrace();
        }
        catch(ErroreFormatoException ex2){
            ex2.printStackTrace();
        }
	}

    private static void creazioneRete(Rete daCreare){
        assert daCreare != null;
        aggiuntaPosto(daCreare);
        aggiuntaTransizione(daCreare);
        aggiuntaElemFlusso(daCreare);
    }

    private static boolean controlloRete(Rete daControllare, GestoreReti listaReti){
        assert daControllare != null && listaReti != null; //precondizioni
		boolean connessa = daControllare.controlloConnessione();
        if(!connessa) InterazioneUtente.controlloRete(0);
		boolean corretta = daControllare.controlloCorrettezza();
        if(!corretta) InterazioneUtente.controlloRete(1);
        if(corretta && connessa) InterazioneUtente.controlloRete(3);
        boolean duplicata = false;
        for(String nomeRete : listaReti.getKeyLIst()){
			if(listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(daControllare)) {
				duplicata = true;
			}			
		}
        if(duplicata) InterazioneUtente.controlloRete(2);
        if(!corretta || !connessa || duplicata) return false;
        else return true;
    }

    private static void salvataggioRete(Rete daSalvare, GestoreReti listaReti){
        String nomeRete = InterazioneUtente.salvataggioRete(0);
        if(nomeRete != null){
            try {
                listaReti.addRete(nomeRete, daSalvare);
                SerializzatoreOggetti.salvaSingoloOggetto(new File("ListaRetiConfiguratore.txt"), listaReti);
            } catch (giaPresenteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void aggiuntaRete(Rete daCreare, GestoreReti listaReti){
        InterazioneUtente.aggiuntaRete();
        creazioneRete(daCreare);
        boolean possibileSalvataggio = controlloRete(daCreare,listaReti);
        if(possibileSalvataggio) salvataggioRete(daCreare,listaReti);        
    }

    public static void visualizzaRetiDaGestore(GestoreReti listaReti){
        assert listaReti != null; //precondizione
        if(listaReti.getListaRetiConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        else{
            String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
            for(String elem : listaReti.getKeyLIst()){
				if (elem.equals(nomeReteDaVisualizzare)){
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(listaReti.getListaRetiConfiguratore().get(nomeReteDaVisualizzare));
                }
            }    
        }
    }

    public static void estendiReteInPN(GestoreReti listaReti, GestoreRetiPetri listaPetriPN){
        if(listaReti.getListaRetiConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        String nomeReteDaEstendere = InterazioneUtente.estendiReteView(listaReti);
        Rete reteScelta = listaReti.getListaRetiConfiguratore().get(nomeReteDaEstendere);        
        RetePetri retePN = new RetePetri(reteScelta);
        cambiaMarcatura(retePN);
        cambiaPesi(retePN);
        salvataggioRetePN(retePN, listaPetriPN);
    }

    private static void cambiaMarcatura(RetePetri retePN){
        boolean risposta = InterazioneUtente.domandaCambiamentoDatiRetePetri(0);
        if(risposta){
            while(risposta != false){
                InterazioneUtente.printListaMarcature(retePN.getMarcatura()); 
                String nome = InterazioneUtente.leggiElementoDaCambiare(0);
                int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(0);
                retePN.getMarcatura().impostaMarcatura(nome, nuovoValore);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(3);
            }
           
        }
    }

    private static void cambiaPesi(RetePetri retePN){
        boolean risposta = InterazioneUtente.domandaCambiamentoDatiRetePetri(1);
        if(risposta){
            while(risposta != false){
                InterazioneUtente.printListaPesi(retePN.getListaPesi()); 
                String nome = InterazioneUtente.leggiElementoDaCambiare(1);
                int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(1);
                try {
                    ElemFlusso elemDaCambiare = retePN.getElemFlussoByName(nome);
                    retePN.getListaPesi().impostaPeso(elemDaCambiare.getElem1().getName(), elemDaCambiare.getElem2().getName(), nuovoValore);
                } catch (NonPresenteException e) {
                    e.printStackTrace();
                }
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(4);
            }
        }
    }

    private static boolean controlloRetePetriDuplicata(RetePetri daControllare, GestoreRetiPetri listaReti){

        for(String nomeRete : listaReti.getListaRetiPetriConfiguratore().keySet().toArray(new String[0])){
            if(daControllare.controlloRetePetriUguale(listaReti.getListaRetiPetriConfiguratore().get(nomeRete))) return true;
        }
        return false;
    }

    private static void salvataggioRetePN(RetePetri retePN, GestoreRetiPetri listaPetriPN){
        if(!controlloRetePetriDuplicata(retePN, listaPetriPN)){
            String nomeSalvataggio = InterazioneUtente.salvataggioRete(1);
            if(nomeSalvataggio != null){
                try {
                    listaPetriPN.addRete(nomeSalvataggio, retePN);
                    SerializzatoreOggetti.salvaSingoloOggetto(new File("ListaRetiPetriConfiguratore.txt"), listaPetriPN);
                } catch (giaPresenteException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            InterazioneUtente.printErroreRetePNDuplicata();
        }
    }

    public static void visualizzaRetiPetriDaGestore(GestoreRetiPetri listaReti){
        assert listaReti != null; //precondizione
        if(listaReti.getListaRetiPetriConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        else{
            String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
            for(String elem : listaReti.getKeyLIst()){
				if (elem.equals(nomeReteDaVisualizzare)){
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(listaReti.getListaRetiPetriConfiguratore().get(nomeReteDaVisualizzare));
                }
            }    
        }
    }
}
