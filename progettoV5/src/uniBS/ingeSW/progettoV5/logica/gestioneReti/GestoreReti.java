package uniBS.ingeSW.progettoV5.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV5.logica.rete.Rete;
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

	private HashMap<String, Rete> listaRetiConfiguratore;

	/**
	 * Metodo per inizializzare la lista delle reti da salvare.
	 */
	public GestoreReti() {
		this.listaRetiConfiguratore = new HashMap<String, Rete>();
	}

	public HashMap<String, Rete> getListaRetiConfiguratore() {
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
	public void addRete(String name, Rete toAdd) throws giaPresenteException {
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

	/**
	 * Metodo che controlla se due reti sono uguali.
	 * @param rete1 prima rete da comparare
	 * @param rete2 seconda rete da comparare.
	 * @return boolean che indicano se sono uguali.
	 */
	public boolean isEqual(Rete rete1, Rete rete2) {
		assert rete1 != null : "rete1 = null"; //precondizione
		assert rete2 != null : "rete2 = null"; //precondizione
		return rete1.isEqual(rete2);		
	}

	
}