package uniBS.ingeSW.progettoV4.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV4.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV4.utils.eccezioni.giaPresenteException;

/**
 * Classe per la gestione delle <em> reti di Petri con Periorità </em> da salvare in modo persistente.
 * Possono essere inserite solamente reti corrette.
 * Ogni rete è salvata tramite un nome che deve essere univoco.
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
	 * Metodo che aggiunge una rete di Petri con Periorità alla lista delle reti da salvare.
	 * Una rete può essere aggiunta solamente se ha un nome diverso da tutti quelli salvati.
	 * @param name nome della nuova rete da aggiungere
	 * @param toAdd rete da aggungere 
	 * @return boolean che indica se l'aggiunta è andata a buon fine o meno.
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

    /** 
    * Metodo che controlla se due reti di Petri con Priorità sono uguali.
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
