package uniBS.ingeSW.progettoV2.logica.rete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV2.utils.eccezioni.ErroreFormatoException;
import uniBS.ingeSW.progettoV2.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV2.utils.eccezioni.giaPresenteException;
import uniBS.ingeSW.progettoV2.view.ElemFlussoPresentation;

/**
 * Classe per l'implementazione di una rete.
 * Una rete e' composta da <ul> <li> <em> un insieme di posti </em> </li> <li> <em> un insieme di transizioni </em> </li> <li> <em> un insieme di elementi di flusso </em> </li> </ul>
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da alemno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 2.0 - attuata separazione modello-vista. Cambiati getters e setters.
 */
public class Rete {

	private static final boolean BOOL_CONST_TRUE = true;
	private static final boolean BOOL_CONST_FALSE = false;

	protected ArrayList<Posto> insiemePosti;
	protected ArrayList<Transizione> insiemeTransizioni;
	protected ArrayList<ElemFlusso> relazioneFlusso;

	/**
	 * Metodo per la creazione di una rete.
	 * Vengono istanziati i 3 insiemi, implementati attraverso un arrayList.
	 */
	public Rete() {
		this.insiemePosti = new ArrayList<Posto>();
		this.insiemeTransizioni = new ArrayList<Transizione>();
		this.relazioneFlusso = new ArrayList<ElemFlusso>();
	}

	public ArrayList<Posto> getInsiemePosti() {
		return this.insiemePosti;
	}

	public ArrayList<Transizione> getInsiemeTransizioni() {
		return this.insiemeTransizioni;
	}

	public ArrayList<ElemFlusso> getRelazioneFlusso() {
		return this.relazioneFlusso;
	}

	/**
	 * Metodo che, dato il nome di un posto, lo cerca all'interno dell'insieme e restituisce l'oggetto 
	 * posto corrispondente.
	 * @param daCercare il nome del posto da cercare.
	 * @return il posto cercato o <em> NULL </em> se non e' stato trovato.
	 * @throws NonPresenteException
	 */
	public Posto getPostoByName(String daCercare) throws NonPresenteException {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		var trovato = Stream.of(getInsiemePosti().toArray(new Posto[0]))
						.filter(n -> n.getName().equalsIgnoreCase(daCercare))
						.findFirst()
						.orElse(null);
		if(trovato == null) throw new NonPresenteException("Questo posto non è presente.");				
		return trovato;
	}

	/**
	 * Metodo che, dato il nome di una transizione, la cerca all'interno dell'insieme e restituisce l'oggetto 
	 * transizione corrispondente.
	 * @param daCercare il nome della transizione da cercare.
	 * @return la transizione cercata o <em> NULL </em> se non e' stato trovata.
	 * @throws NonPresenteException
	 */
	public Transizione getTransByName(String daCercare) throws NonPresenteException {
	    assert daCercare != null : "stringaNome = null"; //precondizione
		var trovato = Stream.of(getInsiemeTransizioni().toArray(new Transizione[0]))
					.filter(n -> n.getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
		if(trovato == null) throw new NonPresenteException("Questa transizione non è presente.");
		return trovato;
	}

	public ElemFlusso getElemFlussoByName(String daCercare) throws NonPresenteException{
		assert daCercare != null : "stringaNome = null"; //precondizione
		var trovato = Stream.of(getRelazioneFlusso().toArray(new ElemFlusso[0]))
					.filter(n -> new ElemFlussoPresentation(n).getName().equalsIgnoreCase(daCercare))
					.findFirst()
					.orElse(null);
		if(trovato == null) throw new NonPresenteException("Questo elemento di flusso non è presente.");
		return trovato;
	}

	/**************************************************************************************************************************/
	//SEZIONE AGGIUNTE

	/**
	 * Metodo che aggiunge una transizione all'insieme delle transizioni.
	 * Una transizione non puo' essere aggiunta se all'interno dell'insieme e' gia'� presente una transizione con lo stesso nome.
	 * @param toAdd Transizione da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 */
	public void addTrans(Transizione toAdd) throws giaPresenteException {
	    assert toAdd != null : "Transizione da aggiungere = null"; //precondizione
		boolean giaPresente = Stream.of(insiemeTransizioni.toArray(new Transizione[0]))
								.anyMatch(n -> n.getName().equalsIgnoreCase(toAdd.getName()));

		if (!giaPresente) {
			int size = insiemeTransizioni.size();
			insiemeTransizioni.add(toAdd);
			assert size < insiemeTransizioni.size() : "error add"; //postcondizione
		}
		else throw new giaPresenteException("Questa transizione è già presente.");
	}

	/**
	 * Metodo che aggiunge un posto all'insieme dei posti.
	 * Un posto non puo' essere aggiunto se all'interno dell'insieme e' gia'� presente un posto con lo stesso nome.
	 * @param toAdd Posto da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 */
	public void addPosto(Posto toAdd) throws giaPresenteException{
	    assert toAdd != null : "Posto da aggiungere = null"; //precondizione
		boolean giaPresente = Stream.of(insiemePosti.toArray(new Posto[0]))
								.anyMatch(n -> n.getName().equalsIgnoreCase(toAdd.getName()));

		if (!giaPresente) {
			int size = insiemePosti.size();
			insiemePosti.add(toAdd);
			assert size < insiemePosti.size() : "error add"; //postcondizione
		}
		else throw new giaPresenteException("Questo posto è già presente.");
	}

	

	/**
	 * Metodo che aggiunge un elemento di flusso all'insieme degli elementi di flusso.
	 * Un elemento di flusso non puo' essere aggiunto se e' duplicato o se non e' corretto.
	 * @param toAdd ElemFlusso da aggiungere.
	 * @return boolean che indica se l'aggiunta e' andata a buon fine.
	 * @throws giaPresenteException
	 * @throws ErroreFormatoException
	 */
	public void addElemFlusso(ElemFlusso elem) throws giaPresenteException, ErroreFormatoException {
	    assert elem != null : "elem = null"; //precondizione
		if (!elem.areSameType()) {
			if(!duplicatedElemFlusso(elem)){
			    int size = relazioneFlusso.size();
				relazioneFlusso.add(elem);
				assert size < relazioneFlusso.size() : "error in add"; //postcondizione
			}
			else throw new giaPresenteException("Questo elemento di flusso è già presente.");
		} 
		else throw new ErroreFormatoException();
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
		if(this.getInsiemePosti().size() != toCheck.getInsiemePosti().size()) return BOOL_CONST_FALSE;
		if(this.getInsiemeTransizioni().size() != toCheck.getInsiemeTransizioni().size()) return BOOL_CONST_FALSE;
		if(this.getRelazioneFlusso().size() != toCheck.getRelazioneFlusso().size()) return BOOL_CONST_FALSE;


		for (ElemFlusso elemRete1 : this.getRelazioneFlusso()) {
			ciclo2:
			for (ElemFlusso elemRete2 : toCheck.getRelazioneFlusso()) {
				if (elemRete1.controlloUguali(elemRete2)) {
					uguali = BOOL_CONST_TRUE;
					break ciclo2;
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
		return Stream.of(getRelazioneFlusso().toArray(new ElemFlusso[0]))
					.anyMatch(n -> n.controlloUguali(toCheck));		
	}

	/**
	 * Metodo per il controllo della connessione della rete
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
	 * Una rete e' corretta se ha almeno un posto e una transizione e se ogni transizione
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
}
