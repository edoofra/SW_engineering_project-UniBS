package uniBS.ingeSW.progetto.rete;

import java.util.ArrayList;
import java.util.HashMap;

public class Rete {

	// insieme non ordinato di elementi univoci
	// se provo ad aggiungere elem che c'e' gia' me lo ignora
	private ArrayList<Posto> insiemePosti;
	private ArrayList<Transizione> insiemeTransizioni;
	private ArrayList<ElemFlusso> relazioneFlusso;

	public Rete() {
		this.insiemePosti = new ArrayList<Posto>();
		this.insiemeTransizioni = new ArrayList<Transizione>();
		this.relazioneFlusso = new ArrayList<ElemFlusso>();
	}

	// Ritorna la lista dei posti come Array (piu' facile da gestire)
	public Posto[] getInsiemePosti() {
		return (Posto[]) insiemePosti.toArray(new Posto[0]);
	}

	// Ritorna la lista delle transizioni come Array
	public Transizione[] getInsiemeTransizioni() {
		return (Transizione[]) insiemeTransizioni.toArray(new Transizione[0]);
	}

	// Ritorna la relazione di flusso come Array
	public ElemFlusso[] getRelazioneFlusso() {
		return (ElemFlusso[]) relazioneFlusso.toArray(new ElemFlusso[0]);
	}

	// restituisce un posto dalla lista cercandolo per nome
	public Posto getSinglePosto(String daCercare) {
		for (Posto elem : getInsiemePosti()) {
			if (elem.getName().equalsIgnoreCase(daCercare))
				return elem;
		}
		return null;
	}

	// restituisce una trans dalla lista cercandola per nome
	public Transizione getSingleTrans(String daCercare) {
		for (Transizione elem : getInsiemeTransizioni()) {
			if (elem.getName().equalsIgnoreCase(daCercare))
				return elem;
		}
		return null;
	}

	// ritorna la lista delle stringhe con i nomi di posti / transizioni
	// metodo usato nel toString
	// metodo overloaded
	public String getStringList(ElementoSemplice[] list) {
		StringBuilder stringList = new StringBuilder("{ ");
		for (int i = 0; i < list.length; i++) {
			stringList.append(list[i].getProperties() + ", ");
		}
		int last = stringList.length();
		stringList.delete(last-2, last-1);
		stringList.append("}");
		return stringList.toString();
	}

	// ritorna la lista delle stringhe con i nomi delle coppie del flusso
	// metodo usato nel toString
	// metodo overloaded
	public String getStringList(ElemFlusso[] list) {
		StringBuilder stringList = new StringBuilder("{ ");
		for (int i = 0; i < list.length; i++) {
			stringList.append(list[i].getName() + ", ");
		}
		int last = stringList.length();
		stringList.delete(last-2, last-1);
		stringList.append("}");
		return stringList.toString();
	}

	// controlla se uno dei tre e' vuoto
	public boolean emptyControl() {
		if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty() || relazioneFlusso.isEmpty())
			return true;
		else
			return false;
	}

	// aggiunge una transizione, rstituisce bool cosi' so se e' andata
	// a buon fine nel metodo esterno che la chiama
	public boolean addTrans(Transizione toAdd) {
		if (insiemeTransizioni.contains(toAdd))
			return false;
		insiemeTransizioni.add(toAdd);
		return true;
	}

	public boolean addPosto(Posto toAdd) {
		if (insiemePosti.contains(toAdd))
			return false;
		insiemePosti.add(toAdd);
		return true;
	}

	//controlla che elementi flusso siano univoci
	private boolean uniqueElemFlusso(ElemFlusso toCheck){
		for(ElemFlusso elem : this.getRelazioneFlusso()){
			if(elem.getElem1().getName().equalsIgnoreCase(toCheck.getElem1().getName())){
				if(elem.getElem2().getName().equalsIgnoreCase(toCheck.getElem2().getName())){
					return false;
				}
			}
		}
		return true;
	}

	// Metodo che aggiunge un elemento di flusso alla relazione di flusso e
	// controlla che un Elemento di flusso sia composto da una coppia (Posto,
	// Transizione) o viceversa
	// altrimenti non lo aggiunge e ritorna false
	public boolean addElemFlusso(ElemFlusso elem) {

		boolean postoTransizione = elem.getElem1() instanceof Posto && elem.getElem2() instanceof Transizione;
		boolean transizionePosto = elem.getElem1() instanceof Transizione && elem.getElem2() instanceof Posto;

		if (postoTransizione || transizionePosto) {

			if(uniqueElemFlusso(elem)){
				relazioneFlusso.add(elem);
				return true;
			}
			
		} 
		return false;
	}

	public boolean controlloConnessione(){
		HashMap<ElementoSemplice,Boolean> visitati1 = new HashMap<ElementoSemplice,Boolean>();
		HashMap<ElementoSemplice,Boolean> visitati2 = new HashMap<ElementoSemplice,Boolean>();
		for (ElemFlusso elem : this.getRelazioneFlusso()){
			visitati1.put(elem.getElem2(), true);
			visitati2.put(elem.getElem1(), true);
		}
		for (Posto posto : this.getInsiemePosti()){
			if(! visitati1.containsKey(posto)) {
				if(! visitati2.containsKey(posto)){
					return false;
				}
			}
		}

		for (Transizione trans : this.getInsiemeTransizioni()){
			if(! visitati1.containsKey(trans)) {
				if(! visitati2.containsKey(trans)){
					return false;
				}
			}
		}

		return true;
	}

	public boolean controlloCorrettezza(){
		boolean corretta = false;
		for(Transizione trans : this.getInsiemeTransizioni()){
			for(ElemFlusso flusso : this.getRelazioneFlusso()){
				if(flusso.getElem1().getName().equalsIgnoreCase(trans.getName())){
					corretta=true;
					break;
				}
				corretta=false;
			}
			if(!corretta) return false;
		}
		return corretta;
	}

	

	public String toString() {
		StringBuilder description = new StringBuilder("Descrizione della rete: \n");
		description.append("POSTI: " + getStringList(getInsiemePosti()) + "\n");
		description.append("TRANSIZIONI: " + getStringList(getInsiemeTransizioni()) + "\n");
		description.append("RELAZIONE FLUSSO: " + getStringList(getRelazioneFlusso()) + "\n");
		return description.toString();

	}

}
