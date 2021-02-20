package uniBS.ingeSW.progetto.gestioneReti;

import java.util.HashMap;
import uniBS.ingeSW.progetto.rete.Rete;

//classe per la gestione di tutte le reti salvate dal configuratore
public class GestoreReti {

    HashMap<String, Rete> listaRetiConfiguratore;

    //costruttore
    public GestoreReti() {

	this.listaRetiConfiguratore = new HashMap<String, Rete>();
    }

    public HashMap<String, Rete> getListaRetiConfiguratore() {

	return listaRetiConfiguratore;
    }
    
    //ritorna la lista delle chiavi come Array ( più comodo da gestire )
    public String[] getKeyLIst() {
	
	return (String[]) listaRetiConfiguratore.keySet().toArray();
    }

    //aggiunge una rete alla map con controlli
    //ritorna codice errore 1 se esiste già nome
    //ritorna codice errore 2 se esiste già rete
    //gestione codice errori andrà fatta in interazione con utente
    public int addRete(String name, Rete toAdd) {

	if (listaRetiConfiguratore.containsKey(name))
	    return 1;
	if (listaRetiConfiguratore.containsValue(toAdd))
	    return 2;
	listaRetiConfiguratore.put(name, toAdd);
	return 0;
    }

    public String toString() {

	StringBuilder StringList = new StringBuilder();
	String[] keyList = getKeyLIst();
	for (int i = 0; i < keyList.length; i++) {
	    StringList.append(keyList[i]);
	}
	return StringList.toString();

    }

}