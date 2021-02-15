package rete;

import java.util.HashSet;

public class Rete {
	
	//insieme non ordinato di elementi univoci
	//se provo ad aggiungere elem che cè già me lo ignora
	HashSet<Posto> insiemePosti;
	HashSet<Transizione> insiemeTransizioni;
	HashSet<ElemFlusso> relazioneFlusso;
	
	public Rete() {
		this.insiemePosti = new HashSet<Posto>();
		this.insiemeTransizioni = new HashSet<Transizione>();
		this.relazioneFlusso = new HashSet<ElemFlusso>();	
	}
	
	//controlla se uno dei tre è vuoto
	public boolean emptyControl() {
		if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty() || relazioneFlusso.isEmpty())
			return true;
		else return false;
	}
	
	//aggiunge una transizione, rstituisce bool così so se è andata
	// a buon fine nel metodo esterno che la chiama
	public boolean addTrans(Transizione trans) {
		if (insiemeTransizioni.contains(trans)) return false;
		insiemeTransizioni.add(trans);
		return true;
	}
	
	public boolean addPosto(Posto posto) {
		if (insiemePosti.contains(posto)) return false;
		insiemePosti.add(posto);
		return true;		
	}
	
	
	/**
	 * ordine di inserimento per rete corretta:
	 * 
	 * 
	 * 
	 */
}
