package uniBS.ingeSW.progetto.gestioneReti;

import java.util.HashMap;
import uniBS.ingeSW.progetto.rete.Rete;

/**
 * Classe per la gestione delle reti da salvare in modo persistente.
 * Possono essere inserite solamente reti corrette.
 * Ogni rete è salvata tramite un nome che deve essere univoco.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 1.0
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
	 * @return boolean che indica se l'aggiunta è andata a buon fine o meno.
	 */
	public boolean addRete(String name, Rete toAdd) {
		assert name != null : "name = null"; //precondizione
		assert toAdd !=null : "toAdd = null"; //precondizione
		int beforeAdd = listaRetiConfiguratore.size();

		if (listaRetiConfiguratore.containsKey(name)) return false;			
		else {
			listaRetiConfiguratore.put(name, toAdd);
			int nextAdd = listaRetiConfiguratore.size();
			assert nextAdd == beforeAdd +1 : "size error"; //postcondizione
			return true;
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

	public String toString() {
		StringBuilder stringList = new StringBuilder("{ ");
		String[] keyList = getKeyLIst();
		for (int i = 0; i < keyList.length; i++) {
			stringList.append(( keyList[i] + ", "));
		}
		int size = stringList.length();
		stringList.delete(size-2, size-1);
		stringList.append(" }");
		return stringList.toString();

	}
}