package uniBS.ingeSW.progetto.rete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Classe per l'implementazione di una rete.
 * Una rete e' composta da <ul> <li> <em> un insieme di posti </em> </li> <li> <em> un insieme di transizioni </em> </li> <li> <em> un insieme di ekementi di flusso </em> </li> </ul>
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da almeno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 1.0
 */
public class Rete {

	private static final boolean BOOL_CONST_TRUE = true;
	private static final boolean BOOL_CONST_FALSE = false;

	private ArrayList<Posto> insiemePosti;
	private ArrayList<Transizione> insiemeTransizioni;
	private ArrayList<ElemFlusso> relazioneFlusso;

	/**
	 * Metodo per la creazione di una rete.
	 * Vengono istanziati i 3 insiemi, implementati attraverso un arrayList.
	 */
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

	/**
	 * Metodo che, dato il nome di un posto, lo cerca all'interno dell'insieme e restituisce l'oggetto 
	 * posto corrispondente.
	 * @param daCercare il nome del posto da cercare.
	 * @return il posto cercato o <em> NULL </em> se non e' stato trovato.
	 */
	public Posto getPostoByName(String daCercare) {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		return Stream.of(getInsiemePosti())
					.filter(n -> n.getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
	}

	/**
	 * Metodo che, dato il nome di una transizione, la cerca all'interno dell'insieme e restituisce l'oggetto 
	 * transizione corrispondente.
	 * @param daCercare il nome della transizione da cercare.
	 * @return la transizione cercata o <em> NULL </em> se non e' stato trovata.
	 */
	public Transizione getTransByName(String daCercare) {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		return Stream.of(getInsiemeTransizioni())
					.filter(n -> n.getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
	}

	/**************************************************************************************************************************/
	//SEZIONE AGGIUNTE

	/**
	 * Metodo che aggiunge una transizione all'insieme delle transizioni.
	 * Una transizione non puo' essere aggiunta se all'interno dell'insieme e' gia'� presente una transizione con lo stesso nome.
	 * @param toAdd Transizione da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 */
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

	/**
	 * Metodo che aggiunge un posto all'insieme dei posti.
	 * Un posto non puo' essere aggiunto se all'interno dell'insieme e' gia'� presente un posto con lo stesso nome.
	 * @param toAdd Posto da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 */
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

	

	/**
	 * Metodo che aggiunge un elemento di flusso all'insieme degli elementi di flusso.
	 * Un elemento di flusso non puo' essere aggiunto se e' duplicato o se non e' corretto.
	 * @param elem ElemFlusso da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 */
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
	 * <em> Due reti si considerano uguali se tutti i loro elementi di flusso sono uguali </em>
	 * Si assume che le reti siano corrette, quindi connesse, quindi se gli elementi di flusso sono tutti uguali
	 * lo sono anche tutti gli elementi della rete
	 * @param toCheck rete da comparare
	 * @return boolean che indica se le due reti sono uguali o meno 
	 */
	public boolean isEqual(Rete toCheck){
	    assert toCheck != null : "toCheck = null";
		boolean uguali = BOOL_CONST_FALSE;
		if(this.getInsiemePosti().length != toCheck.getInsiemePosti().length) return BOOL_CONST_FALSE;
		if(this.getInsiemeTransizioni().length != toCheck.getInsiemeTransizioni().length) return BOOL_CONST_FALSE;
		if(this.getRelazioneFlusso().length!= toCheck.getRelazioneFlusso().length) return BOOL_CONST_FALSE;


		for (ElemFlusso elemRete1 : this.getRelazioneFlusso()) {
			cicloInterno:
			for (ElemFlusso elemRete2 : toCheck.getRelazioneFlusso()) {
				if (elemRete1.controlloUguali(elemRete2)) {
					uguali = BOOL_CONST_TRUE;
					break cicloInterno;
					//con valore BOOL_CONST_TRUE
				}
				uguali = BOOL_CONST_FALSE;
			}
			if (!uguali)
				return BOOL_CONST_FALSE;
		}
		return BOOL_CONST_TRUE; 
	}

	/**
	 * Metodo che controlla se uno o piu' insiemi della rete sono vuoti.
	 * @return boolean che indica se un insieme e' vuoto.
	 */
	public boolean emptyControl() {
		if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty() || relazioneFlusso.isEmpty())
			return BOOL_CONST_TRUE;
		else
			return BOOL_CONST_FALSE;
	}

	/**
	 * Metodo che controlla se due elementi di flusso sono uguali.
	 * @param toCheck elemento di flusso da comparare.
	 * @return boolean che indica se i due elementi sono uguali.
	 */
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
	 * Se un posto o una transizione non e' contenuto ne in Visitati1 ne in Visitati2 allora e' isolato e la rete non e' connessa
	 * @return boolean che indica se la rete e' connessa o meno.
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
	 * Metodo che controlla se una rete e' corretta 
	 * Una rete è corretta se ha almeno un posto e una transizione e se ogni transizione
	 * non si trova mai come ultimo elemento della rete
	 * @return boolean che indica se la rete e' corretta o meno.
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

	/**
	 * Metodo che ritorna una stringa composta dai nomi dei posti e delle transizioni della rete.
	 * @overloaded
	 * @return stringa creata.
	 */
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

	/**
	 * Metodo che ritorna una stringa composta dai nomi degli elementi di flusso.
	 * @overloaded
	 * @return stringa creata.
	 */
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
