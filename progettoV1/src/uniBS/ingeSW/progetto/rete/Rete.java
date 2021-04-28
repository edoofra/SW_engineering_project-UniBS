package uniBS.ingeSW.progetto.rete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Classe per l'implementazione di una rete composta da posti, transizioni ed elementi di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 */
public class Rete {

	private static final boolean BOOL_CONST_TRUE = true;
	private static final boolean BOOL_CONST_FALSE = false;

	private ArrayList<Posto> insiemePosti;
	private ArrayList<Transizione> insiemeTransizioni;
	private ArrayList<ElemFlusso> relazioneFlusso;

	public Rete() {
		this.insiemePosti = new ArrayList<Posto>();
		this.insiemeTransizioni = new ArrayList<Transizione>();
		this.relazioneFlusso = new ArrayList<ElemFlusso>();
	}

	public Posto[] getInsiemePosti() {
		return (Posto[]) insiemePosti.toArray(new Posto[0]);
	}

	public Transizione[] getInsiemeTransizioni() {
		return (Transizione[]) insiemeTransizioni.toArray(new Transizione[0]);
	}

	public ElemFlusso[] getRelazioneFlusso() {
		return (ElemFlusso[]) relazioneFlusso.toArray(new ElemFlusso[0]);
	}

	// restituisce un posto dalla lista cercandolo per nome
	public Posto getPostoByName(String daCercare) {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		return Stream.of(getInsiemePosti())
					.filter(n -> n.getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
	}

	// restituisce una trans dalla lista cercandola per nome
	public Transizione getTransByName(String daCercare) {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		return Stream.of(getInsiemeTransizioni())
					.filter(n -> n.getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
	}

	/**************************************************************************************************************************/
	//SEZIONE AGGIUNTE

	// aggiunge una transizione, rstituisce bool cosi' so se e' andata
	// a buon fine nel metodo esterno che la chiama
	public boolean addTrans(Transizione toAdd) {
	    assert toAdd != null : "Transizione da aggiungere = null"; //precondizione
		boolean giaPresente = Stream.of(insiemeTransizioni.toArray(new Transizione[0]))
								.anyMatch(n -> n.getName().equalsIgnoreCase(toAdd.getName()));

		if (giaPresente) return BOOL_CONST_FALSE;
		int size = insiemeTransizioni.size();
		insiemeTransizioni.add(toAdd);
		assert size < insiemeTransizioni.size() : "error add"; //postcondizione
		return BOOL_CONST_TRUE;
	}

	public boolean addPosto(Posto toAdd) {
	    assert toAdd != null : "Posto da aggiungere = null"; //precondizione
		boolean giaPresente = Stream.of(insiemePosti.toArray(new Posto[0]))
								.anyMatch(n -> n.getName().equalsIgnoreCase(toAdd.getName()));

		if (giaPresente) return BOOL_CONST_FALSE;
		int size = insiemePosti.size();
		insiemePosti.add(toAdd);
		assert size < insiemePosti.size() : "error add"; //postcondizione
		return BOOL_CONST_TRUE;
	}

	

	// Metodo che aggiunge un elemento di flusso alla relazione di flusso e
	// controlla che un Elemento di flusso sia composto da una coppia (Posto,
	// Transizione) o viceversa
	// altrimenti non lo aggiunge e ritorna BOOL_CONST_FALSE
	public boolean addElemFlusso(ElemFlusso elem) {
	    assert elem != null : "elem = null"; //precondizione
		if (!elem.areSameType()) {
			if(!duplicatedElemFlusso(elem)){
			    	int size = relazioneFlusso.size();
				relazioneFlusso.add(elem);
				assert size < relazioneFlusso.size() : "error in add"; //postcondizione
				return BOOL_CONST_TRUE;
			}
		} 
		return BOOL_CONST_FALSE;
	}

	/**************************************************************************************************************************/
	//SEZIONE CONTROLLI

	/**
	 * Metodo per controllare se due reti sono o meno uguali
	 * Due reti si considerano uguali se tutti i loro elementi di flusso sono uguali 
	 * Si assume che le reti siano corrette, quindi connesse, quindi se gli elementi di flusso sono tutti uguali
	 * lo sono anche tutti gli elementi della rete
	 */
	public boolean isEqual(Rete toCheck){
	    assert toCheck != null : "toCheck = null";
		boolean uguali = BOOL_CONST_FALSE;

		for (ElemFlusso elemRete1 : this.getRelazioneFlusso()) {
			for (ElemFlusso elemRete2 : toCheck.getRelazioneFlusso()) {
				if (elemRete1.equals(elemRete2)) {
					uguali = BOOL_CONST_TRUE;
					break;
					//se ne trovo uno uguale esco dal ciclo interno 
					//con valore BOOL_CONST_TRUE
				}
				uguali = BOOL_CONST_FALSE;
			}
			if (!uguali)
				return BOOL_CONST_FALSE;
		}
		return BOOL_CONST_TRUE; 
	}

	// controlla se uno dei tre e' vuoto
	public boolean emptyControl() {
		if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty() || relazioneFlusso.isEmpty())
			return BOOL_CONST_TRUE;
		else
			return BOOL_CONST_FALSE;
	}

	//controlla che elementi flusso siano univoci
	private boolean duplicatedElemFlusso(ElemFlusso toCheck){
	    assert toCheck != null : "toCheck = null";
		return Stream.of(getRelazioneFlusso())
					.anyMatch(n -> n.getElem1().getName().equalsIgnoreCase(toCheck.getElem1().getName()) &&
					n.getElem2().getName().equalsIgnoreCase(toCheck.getElem2().getName()));
		
	}

	/**
	 * Metodo per il conrollo della connessione della rete
	 * Visitati1 = lista degli ElementiSemplici raggiunti dagli elementi di flusso della rete
	 * Visitati2 = lista degli ElementiSemplici raggiunti considerando invertiti gli elementi di flusso
	 * Se un posto o una transizione non è contenuto ne in Visitati1 ne in Visitati2 allora è isolato e la rete non è connessa
	 */
	public boolean controlloConnessione(){
		HashMap<ElementoSemplice,Boolean> visitati1 = new HashMap<ElementoSemplice,Boolean>();
		HashMap<ElementoSemplice,Boolean> visitati2 = new HashMap<ElementoSemplice,Boolean>();
		for (ElemFlusso elem : this.getRelazioneFlusso()){
			visitati1.put(elem.getElem2(), BOOL_CONST_TRUE); //secondo elemento è quello verso cui punta la freccia
			visitati2.put(elem.getElem1(), BOOL_CONST_TRUE); //inverto gli elementi di flusso
		}
		for (Posto posto : this.getInsiemePosti()){
			if(! visitati1.containsKey(posto)) {
				if(! visitati2.containsKey(posto)){
					return BOOL_CONST_FALSE;
				}
			}
		}

		for (Transizione trans : this.getInsiemeTransizioni()){
			if(! visitati1.containsKey(trans)) {
				if(! visitati2.containsKey(trans)){
					return BOOL_CONST_FALSE;
				}
			}
		}
		return BOOL_CONST_TRUE;
	}

	/**
	 * Metodo che controlla se una rete è corretta 
	 * Una rete è corretta se ha almeno un posto e una transizione e se ogni transizione
	 * non si trova mai come ultimo elemento della rete
	 */
	public boolean controlloCorrettezza(){
		if(emptyControl()) return BOOL_CONST_FALSE;
		boolean corretta = BOOL_CONST_FALSE;
		for(Transizione trans : this.getInsiemeTransizioni()){
			for(ElemFlusso flusso : this.getRelazioneFlusso()){
				if(flusso.getElem1().getName().equalsIgnoreCase(trans.getName())){
					corretta=BOOL_CONST_TRUE;
					break;
				}
				corretta=BOOL_CONST_FALSE;
			}
			if(!corretta) return BOOL_CONST_FALSE;
		}
		corretta = false;
		for(Transizione trans : this.getInsiemeTransizioni()){
			for(ElemFlusso flusso : this.getRelazioneFlusso()){
				if(flusso.getElem2().getName().equalsIgnoreCase(trans.getName())){
					corretta=BOOL_CONST_TRUE;
					break;
				}
				corretta=BOOL_CONST_FALSE;
			}
			if(!corretta) return BOOL_CONST_FALSE;
		}
		return corretta;
	}

	/**************************************************************************************************************************/
	//SEZIONE TOSTRING (DA ELIMINARE)


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

	public String toString() {
		StringBuilder description = new StringBuilder("Descrizione della rete: \n");
		description.append("POSTI: " + getStringList(getInsiemePosti()) + "\n");
		description.append("TRANSIZIONI: " + getStringList(getInsiemeTransizioni()) + "\n");
		description.append("RELAZIONE FLUSSO: " + getStringList(getRelazioneFlusso()) + "\n");
		return description.toString();

	}
}
