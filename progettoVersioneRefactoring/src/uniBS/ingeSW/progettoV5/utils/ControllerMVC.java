package uniBS.ingeSW.progettoV5.utils;


import java.util.ArrayList;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.ErroreFormatoException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;
import uniBS.ingeSW.progettoV5.view.ElemFlussoPresentation;
import uniBS.ingeSW.progettoV5.view.InterazioneUtente;
import uniBS.ingeSW.progettoV5.view.UIMenu;

public class ControllerMVC {
    
    boolean fineMenuPrincipale = false;
    boolean fineMenuFruitore = false;
    boolean fineMenuConfiguratore = false;
    UIMenu menu = new UIMenu();
    
    public void startMenuUI(GestoreReti listaReti, GestoreRetiPetri listaPN, GestoreRetiPetriPriorita listaPNP) {
	menu.printTitolo();
	do {
	    int scelta = menu.startMenuEsterno();
	    switch (scelta) {

	    case 1:
		startMenuConfiguratoreUI(listaReti, listaPN, listaPNP);
		break;

	    case 2:
		startMenuFruitoreUI(listaPN, listaPNP);
		break;

	    case 0:
		fineMenuPrincipale = true;
		break;

	    // default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
	    }
	} while (fineMenuPrincipale == false);
    }
    
    private void startMenuFruitoreUI(GestoreRetiPetri listaPN, GestoreRetiPetriPriorita listaPNP) {
	do {
	    int scelta = menu.startMenuFruitore();
	    switch (scelta) {

	    case 1:
		simulazioneEvoluzioneRete(listaPN);
		break;

	    case 2:
		simulazioneEvoluzioneRetePriorita(listaPNP);
		break;

	    case 0:
		fineMenuFruitore = true;
		break;

	    // default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
	    }
	} while (fineMenuFruitore == false);
    }
    
    private void startMenuConfiguratoreUI(GestoreReti listaReti, GestoreRetiPetri listaPN,
	    GestoreRetiPetriPriorita listaPNP) {
	do {
	    int scelta = menu.startMenuConfiguratore();
	    switch (scelta) {

	    case 1:
		ControllerMVC.aggiuntaRete(listaReti);
		break;

	    case 2:
		ControllerMVC.visualizzaRetiDaGestore(listaReti);
		break;

	    case 3:
		ControllerMVC.visualizzaRetiPetriDaGestore(listaPN);
		break;

	    case 4:
		ControllerMVC.estendiReteInPN(listaReti, listaPN);
		break;

	    case 5:
		ControllerMVC.estendiRetePNInPNConPriorita(listaPN, listaPNP);
		break;

	    case 6:
		ControllerMVC.visualizzaRetiPetriPrioritaDaGestore(listaPNP);
		break;

	    case 7:
		ControllerMVC.leggiReteDaFile(listaReti);
		break;

	    case 8:
		ControllerMVC.leggiRetePetriDaFile(listaPN, listaReti);
		break;

	    case 9:
		ControllerMVC.leggiRetePetriPrioritaDaFile(listaPNP, listaPN);
		break;

	    case 0:
		fineMenuConfiguratore = true;
		break;

	    // default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
	    }
	} while (fineMenuConfiguratore == false);
    }
    
    // CODICE PER PRESENTAZIONE SAETTI
    private static void aggiuntaTransizione(ReteSemplice daCreare) {
	assert daCreare != null; // precondizione
	boolean risposta = true;
	while (risposta != false) {

	    boolean presente = false;
	    do {
		presente = false;
		try {
		    String nome = InterazioneUtente.aggiuntaElemento(1);
		    CreazioneReteHandler handler = new CreazioneReteHandler();
		    handler.aggiuntaTransizione(daCreare, nome);
		} catch (giaPresenteException ex) {
		    System.out.println(ex.getMessage());
		    presente = true;
		}

	    } while (presente);
	    risposta = InterazioneUtente.continuareAggiuntaYesOrNo(1);
	}
    }

  //CODICE PER PRESENTAZIONE SAETTI
    private static void aggiuntaPosto(ReteSemplice daCreare) {
		assert daCreare != null; //precondizione
            boolean risposta = true;
            while (risposta != false) {

                boolean presente = false;
                do{
                    presente = false;
                    try{
                        String nome = InterazioneUtente.aggiuntaElemento(0);
                        CreazioneReteHandler handler = new CreazioneReteHandler();
    		    	handler.aggiuntaPosto(daCreare, nome);
                    }catch (giaPresenteException ex){
                        System.out.println(ex.getMessage());
                        presente = true;
                    }

                }while(presente);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(0);
            }
	}

  //CODICE PER PRESENTAZIONE SAETTI
    private static void aggiuntaElemFlusso(ReteSemplice daCreare) {
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
                        
                        CreazioneReteHandler handler = new CreazioneReteHandler();
    		    	handler.aggiuntaElemFlusso(daCreare, elem1, elem2);
                        
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
               
    private static void creazioneRete(ReteSemplice daCreare){
        assert daCreare != null;
        aggiuntaPosto(daCreare);
        aggiuntaTransizione(daCreare);
        aggiuntaElemFlusso(daCreare);
    }
    
    private static boolean controlloRete(ReteSemplice daControllare, GestoreReti listaReti) {
	CreazioneReteHandler handler = new CreazioneReteHandler();
	ArrayList<Boolean> esiti = handler.controlloRete(daControllare, listaReti);
	if(!esiti.get(0)) InterazioneUtente.controlloRete(0);
	if(!esiti.get(1)) InterazioneUtente.controlloRete(1);
	if(esiti.get(2)) InterazioneUtente.controlloRete(2);
	if(esiti.get(0) && esiti.get(1) && !esiti.get(2)) {
	    InterazioneUtente.controlloRete(3);
	    return true;
	}
	else return false;
	
    }

    private static void salvataggioRete(ReteSemplice daSalvare, GestoreReti listaReti) {
	String nomeRete = InterazioneUtente.salvataggioRete(0);
	try {

	    CreazioneReteHandler handler = new CreazioneReteHandler();
	    handler.salvataggioRete(daSalvare, listaReti, nomeRete);

	} catch (giaPresenteException e) {
	    System.out.println(e.getMessage());
	}
    }
    
    //CODICE PER PRESENTAZIONE SAETTI
    public static void aggiuntaRete(GestoreReti listaReti){
        assert listaReti != null;
        InterazioneUtente.aggiuntaRete();
        CreazioneReteHandler handler = new CreazioneReteHandler();
        ReteSemplice daCompletare = handler.creaRete(listaReti);
        creazioneRete(daCompletare);
        boolean possibileSalvataggio = controlloRete(daCompletare,listaReti);
        if(possibileSalvataggio) salvataggioRete(daCompletare,listaReti);        
    }

    public static void visualizzaRetiDaGestore(GestoreReti listaReti) {
	assert listaReti != null; // precondizione
	if (listaReti.getListaRetiConfiguratore().isEmpty()) {
	    InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
	} else {
	    boolean presente = false;
	    while (!presente) {
		String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
		VisualizzazioneRetiHandler handler = new VisualizzazioneRetiHandler();
		presente = handler.controlloRetePresente(listaReti, nomeReteDaVisualizzare);
		if (presente) {
		    InterazioneUtente.stampaReteSceltaPerVisualizzazione(
			    listaReti.getListaRetiConfiguratore().get(nomeReteDaVisualizzare));
		}
	    }
	    if (!presente)
		InterazioneUtente.printErrorReteNonPresente();
	}
    }
    
  //CODICE PER PRESENTAZIONE SAETTI
    public static void estendiReteInPN(GestoreReti listaReti, GestoreRetiPetri listaPetriPN){
        assert listaReti != null && listaPetriPN != null;
        if(listaReti.getListaRetiConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        boolean presente = false;
        String nomeReteDaEstendere = null;
	while (!presente) {
	    nomeReteDaEstendere = InterazioneUtente.estendiReteView(listaReti);
	    EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
	    presente = handler.controlloRetePresente(listaReti, nomeReteDaEstendere);
	    if (!presente)
		    InterazioneUtente.printErrorReteNonPresente();
	}
	

        if(nomeReteDaEstendere != null){
            EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
            RetePetri retePN = handler.creazioneRetePetri(listaReti, listaPetriPN, nomeReteDaEstendere);
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
                    EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
                    presente = handler.controlloPostoPresente(retePN, nome);
                    if(!presente) InterazioneUtente.printErrorPostoNonPresente();
                }
                if(nome != null){
                    int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(0);
                    EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
                    handler.impostaNuovaMarcatura(retePN, nome, nuovoValore);
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
                    EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
                    presente = handler.controlloElementoPresente(retePN, nome);
                    if(!presente) InterazioneUtente.printErrorElemFlussoNonPresente();
                }
                
                if(nome != null){
                    int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(1);
                    try {
                	EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
                	handler.impostaNuovoPeso(retePN, nome, nuovoValore);
                    } catch (NonPresenteException e) {
                        e.printStackTrace();
                    }
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(retePN);
                    risposta = InterazioneUtente.continuareAggiuntaYesOrNo(4);
                }
               
            }
        }
    }

    private static void salvataggioRetePN(RetePetri retePN, GestoreRetiPetri listaPetriPN){
        assert retePN !=null && listaPetriPN !=null;
        EstensioneReteInRetePetriHandler handler = new EstensioneReteInRetePetriHandler();
        if(!handler.controlloRetePetriDuplicata(retePN, listaPetriPN)){
            boolean nomeDuplicato = false;
            do{
                String nomeSalvataggio = InterazioneUtente.salvataggioRete(1);
                if(nomeSalvataggio != null){
                    nomeDuplicato = false;
                    try {
                       handler.salvataggioRetePN(retePN, listaPetriPN, nomeSalvataggio);
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
                VisualizzazioneRetiPetriHandler handler = new VisualizzazioneRetiPetriHandler();
                presente = handler.controlloRetePetriPresente(nomeReteDaVisualizzare, listaReti);
                if(presente) {
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(listaReti.getListaRetiPetriConfiguratore().get(nomeReteDaVisualizzare));
                }
                if(!presente) InterazioneUtente.printErrorReteNonPresente();
            }           
        }
    }

    
    public static void simulazioneEvoluzioneRete(GestoreRetiPetri listaReti){
        assert listaReti !=null;
        ArrayList<ElemFlusso> possibiliTrans = new ArrayList<ElemFlusso>();
        if(listaReti.getListaRetiPetriConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        else{
            String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
            if(listaReti.getListaRetiPetriConfiguratore().containsKey(nomeReteDaVisualizzare)){
                RetePetri reteScelta = listaReti.getListaRetiPetriConfiguratore().get(nomeReteDaVisualizzare);
                InterazioneUtente.stampaReteSceltaPerVisualizzazione(reteScelta);
                boolean finito = false;
                while(!finito){
                    possibiliTrans = reteScelta.getPossibiliTransizioni();
                    if(possibiliTrans == null || possibiliTrans.isEmpty()){
                        InterazioneUtente.printErrorDeadlock(nomeReteDaVisualizzare);
                        finito = true;
                    } 
                    else{
                        boolean presente = true;
                        do{
                            presente= true;
                            InterazioneUtente.printPossibiliTransizioniPerSimulazione(possibiliTrans);
                            String nomeElemFlussoScelto = InterazioneUtente.leggiElementoDaCambiare(2);
                            try {
                                SimulazioneReteHandler handler = new SimulazioneReteHandler();
                                handler.simulaEvoluzione(reteScelta, nomeElemFlussoScelto);
                                InterazioneUtente.stampaReteSceltaPerVisualizzazione(reteScelta);
                            } catch (NonPresenteException e) {
                                System.out.println(e.getMessage());
                                presente = false;
                            }
                        }while(!presente);
                        finito = InterazioneUtente.domandaContinuareSimulazione(); 
                    }
                }
            }
	    else {
		InterazioneUtente.printErrorReteNonPresente();
	    }
	}
    }

  //CODICE PER PRESENTAZIONE SAETTI
    public static void estendiRetePNInPNConPriorita(GestoreRetiPetri listaRetiPN, GestoreRetiPetriPriorita listaPetriPNPriorita){
        assert listaPetriPNPriorita !=null && listaRetiPN != null;
        if(listaRetiPN.getListaRetiPetriConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        boolean presente = false;
        String nomeReteDaEstendere = null;
        while(!presente){
            nomeReteDaEstendere = InterazioneUtente.estendiRetePNinPrioritaView(listaRetiPN);
            EstensionePNInPNPHandler handler = new EstensionePNInPNPHandler();
            presente = handler.controlloRetePresente(listaRetiPN, nomeReteDaEstendere);
            if(!presente) InterazioneUtente.printErrorReteNonPresente();
        }
        
        if(nomeReteDaEstendere!=null){
            EstensionePNInPNPHandler handler = new EstensionePNInPNPHandler();
            RetePetriPriorita retePNPriorita = handler.creazioneRetePNP(listaRetiPN, nomeReteDaEstendere, listaPetriPNPriorita);
            cambiaPriorita(retePNPriorita);
            salvataggioRetePNPriorita(retePNPriorita, listaPetriPNPriorita);
        }  
    }

    private static void cambiaPriorita(RetePetriPriorita retePNPriorita){
        assert retePNPriorita !=null;
        boolean risposta = InterazioneUtente.domandaCambiamentoDatiRetePetri(2);
        if(risposta){
            while(risposta != false){
                boolean presente = false; 
                String nomeTransizione = null;
                while(!presente){
                    InterazioneUtente.printListaPriorita(retePNPriorita.getPriorita()); 
                    nomeTransizione = InterazioneUtente.leggiElementoDaCambiare(3);
                    EstensionePNInPNPHandler handler = new EstensionePNInPNPHandler();
                    presente = handler.controlloTransizionePresente(retePNPriorita, nomeTransizione);
                    if(!presente) InterazioneUtente.printErrorTransizioneNonPresente();

                }
                if(nomeTransizione != null){
                    int nuovoValore = InterazioneUtente.leggiNuovoValoreDaInserirePerCambiamentoDati(1);
                    EstensionePNInPNPHandler handler = new EstensionePNInPNPHandler();
                    handler.impostaNuovaPriorita(retePNPriorita, nomeTransizione, nuovoValore);
                    InterazioneUtente.stampaReteSceltaPerVisualizzazione(retePNPriorita);
                    risposta = InterazioneUtente.continuareAggiuntaYesOrNo(5);
                }                
            }           
        }
    }

    private static void salvataggioRetePNPriorita(RetePetriPriorita retePNP, GestoreRetiPetriPriorita listaPetriPNP){
        assert retePNP !=null && listaPetriPNP !=null;
        EstensionePNInPNPHandler handler = new EstensionePNInPNPHandler();
        if(!handler.controlloRetePetriPrioritaDuplicata(retePNP, listaPetriPNP)){
            boolean nomeDuplicato = false;
            do{
                String nomeSalvataggio = InterazioneUtente.salvataggioRete(2);
                if(nomeSalvataggio != null){
                    nomeDuplicato = false;
                    try {
                       handler.salvataggioRetePNP(retePNP, listaPetriPNP, nomeSalvataggio);
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

    public static void visualizzaRetiPetriPrioritaDaGestore(GestoreRetiPetriPriorita listaReti) {
	assert listaReti != null; // precondizione
	if (listaReti.getListaRetiPetriPrioritaConfiguratore().isEmpty()) {
	    InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
	} else {
	    boolean presente = false;
	    while (!presente) {
		String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
		VisualizzazioneRetiPetriPrioritaHandler handler = new VisualizzazioneRetiPetriPrioritaHandler();
		presente = handler.controlloRetePetriPrioritaPresente(nomeReteDaVisualizzare, listaReti);
		if (presente) {
		    InterazioneUtente.stampaReteSceltaPerVisualizzazione(
			    listaReti.getListaRetiPetriPrioritaConfiguratore().get(nomeReteDaVisualizzare));
		}
		if (!presente)
		    InterazioneUtente.printErrorReteNonPresente();
	    }

	}
    }


    public static void simulazioneEvoluzioneRetePriorita(GestoreRetiPetriPriorita listaReti){
        assert listaReti != null;
        ArrayList<ElemFlusso> possibiliTrans = new ArrayList<ElemFlusso>();
        if(listaReti.getListaRetiPetriPrioritaConfiguratore().isEmpty()) {
            InterazioneUtente.messaggioErroreListaRetiDaVisualizzareVuota();
        }
        else{
            String nomeReteDaVisualizzare = InterazioneUtente.getNomeReteDaVisualizzare(listaReti);
                if(listaReti.getListaRetiPetriPrioritaConfiguratore().containsKey(nomeReteDaVisualizzare)){
                RetePetriPriorita reteScelta = listaReti.getListaRetiPetriPrioritaConfiguratore().get(nomeReteDaVisualizzare);
                InterazioneUtente.stampaReteSceltaPerVisualizzazione(reteScelta);
                boolean finito = false;
                while(!finito){
                    possibiliTrans = reteScelta.getPossibiliTransizioni();
                    if(possibiliTrans == null || possibiliTrans.isEmpty()){
                        InterazioneUtente.printErrorDeadlock(nomeReteDaVisualizzare);
                        finito = true;
                    } 
                    else{
                        ArrayList<ElemFlusso> transizioniPossibiliMaxP = reteScelta.getTransizioniPrioritaMaggiore(possibiliTrans);
                        boolean presente = true;
                        do{
                            presente = true;
                            InterazioneUtente.printPossibiliTransizioniPerSimulazione(transizioniPossibiliMaxP);
                            String nomeElemFlussoScelto = InterazioneUtente.leggiElementoDaCambiare(2);
                            try {
                                SimulazioneRetePNPHandler handler = new SimulazioneRetePNPHandler();
                                handler.simulaEvoluzione(reteScelta, nomeElemFlussoScelto);
                                InterazioneUtente.stampaReteSceltaPerVisualizzazione(reteScelta);
                            } catch (NonPresenteException e) {
                                System.out.println((e.getMessage()));
                                presente = false;
                            }
                        }while(!presente);
                        finito = InterazioneUtente.domandaContinuareSimulazione();
                    }
                    
                }
            }else InterazioneUtente.printErrorReteNonPresente();
            
        }
    }

    public static void leggiReteDaFile(GestoreReti listaReti){
        String path = InterazioneUtente.leggiPath();       
        CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
        ReteSemplice reteCaricata = handler.caricaReteDaFile(path);
        if(!reteCaricata.emptyControl()){
            String nomeRete = InterazioneUtente.salvataggioRete(0);
            if(nomeRete != null){
                try {
                    handler.aggiuntaReteAGestore(listaReti, nomeRete, reteCaricata);
                    InterazioneUtente.msgLetturaDaFileCompletata();
                    
                } catch (giaPresenteException e) {
                    System.out.println(e.getMessage());
                }
            }
        }           
    }
        
    public static void leggiRetePetriDaFile(GestoreRetiPetri listaRetiPetri, GestoreReti listaReti){
        String path = InterazioneUtente.leggiPath();
        CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
        RetePetri reteCaricata = handler.caricaPNDaFile(path);
        boolean accettata = handler.controlloAccettazioneRetePetri(reteCaricata, listaReti);
        if(!reteCaricata.emptyControl() && accettata){
            String nomeRete = InterazioneUtente.salvataggioRete(0);
            if(nomeRete != null){
                try {
                    handler.aggiuntaPNAGestore(listaRetiPetri, nomeRete, reteCaricata);
                    InterazioneUtente.msgLetturaDaFileCompletata();
                    
                } catch (giaPresenteException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else{
            InterazioneUtente.printErrorRetePetriNonAccettata();
        }        
    }
       
    public static void leggiRetePetriPrioritaDaFile(GestoreRetiPetriPriorita listaRetiPetriPriorita, GestoreRetiPetri listaRetiPetri){
        String path = InterazioneUtente.leggiPath();
        CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
        RetePetriPriorita reteCaricata = handler.caricaPNPDaFile(path);
        boolean accettata = handler.controlloAccettazioneRetePetriPriorita(reteCaricata, listaRetiPetri);
        if(!reteCaricata.emptyControl() && accettata){
            String nomeRete = InterazioneUtente.salvataggioRete(0);
            if(nomeRete != null){
                try {
                    handler.aggiuntaPNPAGestore(listaRetiPetriPriorita, nomeRete, reteCaricata);
                    InterazioneUtente.msgLetturaDaFileCompletata();
                    
                } catch (giaPresenteException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            InterazioneUtente.printErrorRetePetriPrioritaNonAccettata();
        }          
    }
        
   
    
}
