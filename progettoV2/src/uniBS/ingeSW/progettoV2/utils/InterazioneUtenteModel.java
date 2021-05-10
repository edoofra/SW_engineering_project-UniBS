package uniBS.ingeSW.progettoV2.utils;

import uniBS.ingeSW.progettoV2.logica.rete.Rete;
import uniBS.ingeSW.progettoV2.logica.rete.Transizione;
import uniBS.ingeSW.progettoV2.utils.eccezioni.giaPresenteException;

public class InterazioneUtenteModel {
    
    private static void aggiuntaTransizione(Rete daCreare) {
		assert daCreare != null; //precondizione
		try{
            boolean risposta = true;
            while (risposta != false) {

                String nome = InterazioneUtente.aggiuntaElemento(1);
                Transizione nuovo = new Transizione(nome);
                daCreare.addTrans(nuovo);
                risposta = InterazioneUtente.continuareAggiuntaYesOrNo(1);
            }
        }
        catch (giaPresenteException ex){
            ex.printStackTrace();
        }
        
	}
}
