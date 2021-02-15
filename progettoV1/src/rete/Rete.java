package rete;

import java.util.HashSet;

public class Rete {
	
	HashSet<Posto> insiemePosti = new HashSet<Posto>();
	HashSet<Transizione> insiemeTransizioni = new HashSet<Transizione>();
	HashSet<ElemFlusso> relazioneFlusso = new HashSet<ElemFlusso>();
	
	public boolean emptyControl() {
		if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty()) return true;
		else return false;
	}
}
