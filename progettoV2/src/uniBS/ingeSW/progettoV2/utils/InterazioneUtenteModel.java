package uniBS.ingeSW.progettoV2.utils;

import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV2.logica.rete.Posto;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;
import uniBS.ingeSW.progettoV2.logica.rete.Transizione;
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

                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(1);
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
        if(corretta && connessa) InterazioneUtente.controlloRete(2);
        boolean duplicata = false;
        for(String nomeRete : listaReti.getKeyLIst()){
			if(listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(daControllare)) {
				duplicata = true;
			}			
		}
        if(duplicata)InterazioneUtente.controlloRete(3);
        if(corretta && connessa && !duplicata) return true;
        else return false;
    }

    private static void salvataggioRete(Rete daSalvare, GestoreReti listaReti){

    }

    public static void aggiuntaRete(Rete daCreare, GestoreReti listaReti){
        creazioneRete(daCreare);
        boolean possibileSalvataggio = controlloRete(daCreare,listaReti);
        
    }
}
