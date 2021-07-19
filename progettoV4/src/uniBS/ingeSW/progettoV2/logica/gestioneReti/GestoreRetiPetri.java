package uniBS.ingeSW.progettoV2.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV2.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV2.utils.eccezioni.giaPresenteException;

public class GestoreRetiPetri {

    private HashMap<String, RetePetri> listaRetiPetriConfiguratore;

    public GestoreRetiPetri() {
		this.listaRetiPetriConfiguratore = new HashMap<String, RetePetri>();
	}

    public HashMap<String, RetePetri> getListaRetiPetriConfiguratore() {
		return listaRetiPetriConfiguratore;
	}

	public String[] getKeyLIst() {
		return (String[]) listaRetiPetriConfiguratore.keySet().toArray(new String[0]);
	}

    /**
	 * Metodo che aggiunge una rete alla lista delle reti da salvare.
	 * Una rete può essere aggiunta solamente se ha un nome diverso da tutti quelli salvati.
	 * @param name nome della nuova rete da aggiungere
	 * @param toAdd rete da aggungere 
	 * @return boolean che indica se l'aggiunta è andata a buon fine o meno.
	 * @throws giaPresenteException
	 */
	public void addRete(String name, RetePetri toAdd) throws giaPresenteException {
		assert name != null : "name = null"; //precondizione
		assert toAdd !=null : "toAdd = null"; //precondizione
		int beforeAdd = listaRetiPetriConfiguratore.size();

		if (listaRetiPetriConfiguratore.containsKey(name)) throw new giaPresenteException("Questa rete di Petri è già presente.");			
		else {
			listaRetiPetriConfiguratore.put(name, toAdd);
			int nextAdd = listaRetiPetriConfiguratore.size();
			assert nextAdd == beforeAdd +1 : "size error"; //postcondizione
		}
	}

	/**
	 * Metodo che controlla se due reti sono uguali.
	 * @param rete1 prima rete da comparare
	 * @param rete2 seconda rete da comparare.
	 * @return boolean che indicano se sono uguali.
	 */
	public boolean isEqual(RetePetri rete1, RetePetri rete2) {
		assert rete1 != null : "rete1 = null"; //precondizione
		assert rete2 != null : "rete2 = null"; //precondizione
		return rete1.controlloRetePetriUguale(rete2);		
	}
    
}
