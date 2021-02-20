package uniBS.ingeSW.progetto.rete;

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

	//Ritorna la lista dei posti
	public HashSet<Posto> getInsiemePosti() {
		return insiemePosti;
	}
	//Ritorna la lista delle transizioni
	public HashSet<Transizione> getInsiemeTransizioni() {
		return insiemeTransizioni;
	}
	//Ritorna la relazione di flusso
	public HashSet<ElemFlusso> getRelazioneFlusso() {
		return relazioneFlusso;
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
	
	// Metodo che aggiunge un elemento di flusso alla relazione di flusso e 
	//controlla che un Elemento di flusso sia composto da una coppia (Posto, Transizione) o viceversa 
	//altrimenti non lo aggiunge e ritorna false 
	public boolean addElemFlusso(ElemFlusso elem) {
		
		boolean postoTransizione = elem.getElem1() instanceof Posto &&
				elem.getElem2() instanceof Transizione;
		
		boolean transizionePosto = elem.getElem1() instanceof Transizione &&
				elem.getElem2() instanceof Posto;
		
		if(postoTransizione || transizionePosto)
		{
			relazioneFlusso.add(elem);
			return true;
		}
		else return false; 
	}	
}
