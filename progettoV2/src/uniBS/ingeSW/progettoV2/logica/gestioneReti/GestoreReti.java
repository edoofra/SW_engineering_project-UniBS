package uniBS.ingeSW.progettoV2.logica.gestioneReti;

import java.util.HashMap;

import uniBS.ingeSW.progettoV2.logica.rete.Rete;

//classe per la gestione di tutte le reti salvate dal configuratore
public class GestoreReti {

	private HashMap<String, Rete> listaRetiConfiguratore;

	public GestoreReti() {
		this.listaRetiConfiguratore = new HashMap<String, Rete>();
	}

	public HashMap<String, Rete> getListaRetiConfiguratore() {
		return listaRetiConfiguratore;
	}

	// ritorna la lista delle chiavi come Array ( piu' comodo da gestire )
	public String[] getKeyLIst() {
		return (String[]) listaRetiConfiguratore.keySet().toArray(new String[0]);
	}

	// aggiunge una rete alla map con controlli
	// ritorna codice errore 1 se esiste gia' nome
	// ritorna codice errore 2 se esiste gia' rete
	// gestione codice errori andra' fatta in interazione con utente

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

	
	public boolean isEqual(Rete rete1, Rete rete2) {
		assert rete1 != null : "rete1 = null"; //precondizione
		assert rete2 != null : "rete2 = null"; //precondizione
		return rete1.isEqual(rete2);		
	}

	//da togliere
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