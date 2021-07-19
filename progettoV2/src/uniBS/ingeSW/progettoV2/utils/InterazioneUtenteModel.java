package uniBS.ingeSW.progettoV2.utils;

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
import uniBS.ingeSW.progettoV2.view.ElemFlussoPresentation;

public class InterazioneUtenteModel {
    
    private static void aggiuntaTransizione(Rete daCreare) {
		assert daCreare != null; //precondizione
            boolean risposta = true;
            while (risposta != false) {

                boolean presente = false;
                do{
                    presente = false;
                    try{
                        String nome = InterazioneUtente.aggiuntaElemento(1);
                        var nuovo = new Transizione(nome);
                        daCreare.addTrans(nuovo);
                    }catch (giaPresenteException ex){
                        System.out.println(ex.getMessage());
                        presente = true;
                    }

                }while(presente);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(1);
            }        
	}

    private static void aggiuntaPosto(Rete daCreare) {
		assert daCreare != null; //precondizione
            boolean risposta = true;
            while (risposta != false) {

                boolean presente = false;
                do{
                    presente = false;
                    try{
                        String nome = InterazioneUtente.aggiuntaElemento(0);
                        var nuovo = new Posto(nome);
                        daCreare.addPosto(nuovo);
                    }catch (giaPresenteException ex){
                        System.out.println(ex.getMessage());
                        presente = true;
                    }

                }while(presente);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(0);
            }
	}

    private static void aggiuntaElemFlusso(Rete daCreare) {
		assert daCreare != null; //precondizione
            boolean risposta = true;
            while (risposta != false) {
                boolean giusto = true;
                do{
                    try{
                        giusto = true;
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
        
                       

                    }catch (giaPresenteException ex){
                        System.out.println(ex.getMessage());
                        giusto = false;
                    }
                    catch(NonPresenteException ex1){
                        System.out.println(ex1.getMessage());
                        giusto = false;
                    }
                    catch(ErroreFormatoException ex2){
                        System.out.println(ex2.getMessage());
                        giusto = false;
                    }
                    
                }while(!giusto);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(2);
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
                for(String name : listaReti.getKeyLIst()){
                    String reteJSON = ConvertitoreJson.daOggettoAJson(listaReti.getListaRetiConfiguratore().get(name));
                    salvataggioFile.salvaRete(reteJSON,name);
                } 
				
            } catch (giaPresenteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void aggiuntaRete(Rete daCreare, GestoreReti listaReti){
        assert daCreare != null && listaReti != null;
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
            boolean presente = false;
            while(!presente){
                String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
                for(String elem : listaReti.getKeyLIst()){
                    if (elem.equals(nomeReteDaVisualizzare)){
                        presente = true;
                        InterazioneUtente.stampaReteSceltaPerVisualizzazione(listaReti.getListaRetiConfiguratore().get(nomeReteDaVisualizzare));
                        break;
                    }
                }
                if(!presente) InterazioneUtente.printErrorReteNonPresente();    
            }           
        }
    }

    public static void estendiReteInPN(GestoreReti listaReti, GestoreRetiPetri listaPetriPN){
        if(listaReti.getListaRetiConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        boolean presente = false;
        String nomeReteDaEstendere = null;
        while(!presente){
            nomeReteDaEstendere = InterazioneUtente.estendiReteView(listaReti);
            for(String nomeRete : listaReti.getKeyLIst()){
                if(nomeRete.equalsIgnoreCase(nomeReteDaEstendere)){
                    presente = true;
                    break;
                }
            }
            if(!presente) InterazioneUtente.printErrorReteNonPresente();
        }
        if(nomeReteDaEstendere != null){
            Rete reteScelta = listaReti.getListaRetiConfiguratore().get(nomeReteDaEstendere);        
            RetePetri retePN = new RetePetri(reteScelta);
            cambiaMarcatura(retePN);
            cambiaPesi(retePN);
            salvataggioRetePN(retePN, listaPetriPN);
        }
        
    }

    private static void cambiaMarcatura(RetePetri retePN){
        assert retePN != null;
        boolean risposta = InterazioneUtente.domandaCambiamentoDatiRetePetri(0);
        if(risposta){
            while(risposta != false){
                String nome = null;
                boolean presente = false;
                while(!presente){
                    InterazioneUtente.printListaMarcature(retePN.getMarcatura()); 
                    nome = InterazioneUtente.leggiElementoDaCambiare(0);
                    for(Posto posto : retePN.getMarcatura().getListaPosti()){
                        if(posto.getName().equalsIgnoreCase(nome)){
                            presente = true;
                            break;
                        }
                    }
                    if(!presente) InterazioneUtente.printErrorPostoNonPresente();
                }
                if(nome != null){
                    int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(0);
                    retePN.getMarcatura().impostaNuovaMarcatura(nome, nuovoValore);
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(retePN);
                    risposta = InterazioneUtente.continuareAggiuntaYesOrNo(3);
                }
                
            }           
        }
    }

    private static void cambiaPesi(RetePetri retePN){
        assert retePN !=null;
        boolean risposta = InterazioneUtente.domandaCambiamentoDatiRetePetri(1);
        if(risposta){
            while(risposta != false){
                boolean presente = false;
                String nome = null;
                while(!presente){
                    InterazioneUtente.printListaPesi(retePN.getListaPesi()); 
                    nome = InterazioneUtente.leggiElementoDaCambiare(1);
                    for(ElemFlusso elem : retePN.getListaPesi().getListaElemFlusso()){
                        if(new ElemFlussoPresentation(elem).getName().equalsIgnoreCase(nome)){
                            presente = true;
                            break;
                        }
                    }
                    if(!presente) InterazioneUtente.printErrorElemFlussoNonPresente();
                }
                
                if(nome != null){
                    int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(1);
                    try {
                        ElemFlusso elemDaCambiare = retePN.getElemFlussoByName(nome);
                        retePN.getListaPesi().impostaPeso(elemDaCambiare.getElem1().getName(), elemDaCambiare.getElem2().getName(), nuovoValore);
                    } catch (NonPresenteException e) {
                        e.printStackTrace();
                    }
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(retePN);
                    risposta = InterazioneUtente.continuareAggiuntaYesOrNo(4);
                }
               
            }
        }
    }

    private static boolean controlloRetePetriDuplicata(RetePetri daControllare, GestoreRetiPetri listaReti){
        assert daControllare !=null && listaReti !=null;
        for(String nomeRete : listaReti.getListaRetiPetriConfiguratore().keySet().toArray(new String[0])){
            if(daControllare.controlloRetePetriUguale(listaReti.getListaRetiPetriConfiguratore().get(nomeRete))) return true;
        }
        return false;
    }

    private static void salvataggioRetePN(RetePetri retePN, GestoreRetiPetri listaPetriPN){
        assert retePN !=null && listaPetriPN !=null;
        if(!controlloRetePetriDuplicata(retePN, listaPetriPN)){
            boolean nomeDuplicato = false;
            do{
                String nomeSalvataggio = InterazioneUtente.salvataggioRete(1);
                if(nomeSalvataggio != null){
                    nomeDuplicato = false;
                    try {
                        listaPetriPN.addRete(nomeSalvataggio, retePN);
                        for(String name : listaPetriPN.getKeyLIst()){
                            String retePNJSON = ConvertitoreJson.daOggettoAJson(listaPetriPN.getListaRetiPetriConfiguratore().get(name));
                            salvataggioFile.salvaRetePetri(retePNJSON, name);
                        }
                        String listaRetiPNJSON = ConvertitoreJson.daOggettoAJson(listaPetriPN);
                        salvataggioFile.salvaGestoreReti(listaRetiPNJSON, 1);
                    } catch (giaPresenteException e) {
                        System.out.println(e.getMessage());
                        nomeDuplicato = true;
                    }
                }
            }while(nomeDuplicato);
            
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
            boolean presente = false;
            while(!presente){
                String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
                for(String elem : listaReti.getKeyLIst()){
                    if (elem.equals(nomeReteDaVisualizzare)){
                        presente = true;
                        InterazioneUtente.stampaReteSceltaPerVisualizzazione(listaReti.getListaRetiPetriConfiguratore().get(nomeReteDaVisualizzare));
                        break;
                    }
                }    
                if(!presente) InterazioneUtente.printErrorReteNonPresente();
            }           
        }
    }
}
