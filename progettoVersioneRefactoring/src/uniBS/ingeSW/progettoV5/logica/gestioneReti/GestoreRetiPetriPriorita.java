package uniBS.ingeSW.progettoV5.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

/**
 * Classe per la gestione delle <em> reti di Petri con Priorita' </em> da salvare in modo persistente.
 * Possono essere inserite solamente reti corrette.
 * Ogni rete e' salvata tramite un nome che deve essere univoco.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 1.0
 */
public class GestoreRetiPetriPriorita {
    
    private HashMap<String, RetePetriPriorita> listaRetiPetriPrioritaConfiguratore;

    public GestoreRetiPetriPriorita() {
		this.listaRetiPetriPrioritaConfiguratore = new HashMap<String, RetePetriPriorita>();
	}

    public HashMap<String, RetePetriPriorita> getListaRetiPetriPrioritaConfiguratore() {
		return listaRetiPetriPrioritaConfiguratore;
	}

	public String[] getKeyLIst() {
		return (String[]) listaRetiPetriPrioritaConfiguratore.keySet().toArray(new String[0]);
	}

     /**
	 * Metodo che aggiunge una rete di Petri con priorita' alla lista delle reti da salvare.
	 * Una rete puo' essere aggiunta solamente se ha un nome diverso da tutti quelli salvati.
	 * @param name nome della nuova rete da aggiungere
	 * @param toAdd rete da aggungere 
	 * @return boolean che indica se l'aggiunta e' andata a buon fine o meno.
	 * @throws giaPresenteException
	 */
	public void addRete(String name, RetePetriPriorita toAdd) throws giaPresenteException {
		assert name != null : "name = null"; //precondizione
		assert toAdd !=null : "toAdd = null"; //precondizione
		int beforeAdd = listaRetiPetriPrioritaConfiguratore.size();

		if (listaRetiPetriPrioritaConfiguratore.containsKey(name)) throw new giaPresenteException("Questa rete di Petri con Priorità è già presente.");			
		else {
			listaRetiPetriPrioritaConfiguratore.put(name, toAdd);
			int nextAdd = listaRetiPetriPrioritaConfiguratore.size();
			assert nextAdd == beforeAdd +1 : "size error"; //postcondizione
		}
	}
	
	//CODICE PER PRESENTAZIONE SAETTI
	public RetePetriPriorita creaRetePetriPriorita(RetePetri daEstendere) {
	    return new RetePetriPriorita(daEstendere);
	}

    /** 
    * Metodo che controlla se due reti di Petri con Priorita' sono uguali.
    * @param rete1 prima rete da comparare
    * @param rete2 seconda rete da comparare.
    * @return boolean che indicano se sono uguali.
    */
   public boolean isEqual(RetePetriPriorita rete1, RetePetriPriorita rete2) {
       assert rete1 != null : "rete1 = null"; //precondizione
       assert rete2 != null : "rete2 = null"; //precondizione
       return rete1.controlloRetePetriPrioritaUguale(rete2);		
   }
   


}
