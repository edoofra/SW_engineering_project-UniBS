package uniBS.ingeSW.progettoV5.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

/**
 * Classe per la gestione delle reti da salvare in modo persistente.
 * Possono essere inserite solamente reti corrette.
 * Ogni rete e' salvata tramite un nome che deve essere univoco.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 2.0 - attuazione separazione modello-vista.
 */
public class GestoreReti {

	private HashMap<String, ReteSemplice> listaRetiConfiguratore;
	private static GestoreReti instance;

	/**
	 * Metodo per inizializzare la lista delle reti da salvare.
	 */
	private GestoreReti() {
		this.listaRetiConfiguratore = new HashMap<String, ReteSemplice>();
	}

	public HashMap<String, ReteSemplice> getListaRetiConfiguratore() {
		return listaRetiConfiguratore;
	}

	public String[] getKeyLIst() {
		return (String[]) listaRetiConfiguratore.keySet().toArray(new String[0]);
	}

	/**
	 * Metodo che aggiunge una rete alla lista delle reti da salvare.
	 * Una rete può essere aggiunta solamente se ha un nome diverso da tutti quelli salvati.
	 * @param name nome della nuova rete da aggiungere
	 * @param toAdd rete da aggungere 
	 * @return boolean che indica se l'aggiunta e' andata a buon fine o meno.
	 * @throws giaPresenteException
	 */
	public void addRete(String name, ReteSemplice toAdd) throws giaPresenteException {
		assert name != null : "name = null"; //precondizione
		assert toAdd !=null : "toAdd = null"; //precondizione
		int beforeAdd = listaRetiConfiguratore.size();

		if (listaRetiConfiguratore.containsKey(name)) throw new giaPresenteException("Questa rete è già presente.");			
		else {
			listaRetiConfiguratore.put(name, toAdd);
			int nextAdd = listaRetiConfiguratore.size();
			assert nextAdd == beforeAdd +1 : "size error"; //postcondizione
		}
	}
	
	//CODICE PER PRESENTAZIONE SAETTI
	public ReteSemplice creaRete() {
	    return new ReteSemplice();
	}

	/**
	 * Metodo che controlla se due reti sono uguali.
	 * @param rete1 prima rete da comparare
	 * @param rete2 seconda rete da comparare.
	 * @return boolean che indicano se sono uguali.
	 */
	public boolean isEqual(ReteSemplice rete1, ReteSemplice rete2) {
		assert rete1 != null : "rete1 = null"; //precondizione
		assert rete2 != null : "rete2 = null"; //precondizione
		return rete1.isEqual(rete2);		
	}
	
	//singleton
	public static synchronized GestoreReti getInstance() {
	  if (instance == null) {
	      instance = new GestoreReti();
	  }
	  return instance;
	}

	
}