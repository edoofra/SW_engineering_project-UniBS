package uniBS.ingeSW.progetto.gestioneReti;

import java.util.HashSet;
import uniBS.ingeSW.progetto.rete.Rete;

//classe per la gestione di tutte le reti salvate dal configuratore
public class GestoreReti {

    HashSet<Rete> listaRetiConfiguratore;

    public GestoreReti() {

	this.listaRetiConfiguratore = new HashSet<Rete>();
    }

    public boolean addRete(Rete toAdd) {

	if (listaRetiConfiguratore.contains(toAdd))
	    return false;
	listaRetiConfiguratore.add(toAdd);
	return true;
    }

}