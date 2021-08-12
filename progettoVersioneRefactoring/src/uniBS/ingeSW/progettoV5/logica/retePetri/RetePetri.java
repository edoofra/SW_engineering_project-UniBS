package uniBS.ingeSW.progettoV5.logica.retePetri;

import java.util.ArrayList;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.ReteInterface;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV5.view.ElemFlussoPresentation;

/**
 * Classe per l'implementazione di una rete di petri che estende una rete.
 * Una rete di petri e' composta da <ul> <li> <em> un insieme di posti </em> </li>
 * <li> <em> un insieme di transizioni </em> </li>
 * <li> <em> un insieme di elementi di flusso </em> </li>
 * <li> <em> una marcatura </em> </li> <li> <em> una lista pesi flusso </em> </li> </ul>
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da almeno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 3.0 - aggiunti metodi per simulazione
 */

public class RetePetri implements ReteInterface {
    
    private static final String QUESTO_ELEMENTO_NON_E_PRESENTE = "Questo elemento non è presente.";
    private static final boolean BOOL_CONST_TRUE = true;
    private static final boolean BOOL_CONST_FALSE = false;
    
    private MarcaturaPN marcatura;
    private ListaPesiFlussoPN listaPesi;
    private ArrayList<Posto> insiemePosti;
    private ArrayList<Transizione> insiemeTransizioni;
    private ArrayList<ElemFlusso> relazioneFlusso;
    
    
	/**
	 * Metodo per la creazione di una rete.
	 * Vengono istanziati i 2 insiemi (marcatura e listaPesi), aggiunti i posti, le transizioni e la relazione di flusso della rete
	 * da cui viene implementata la rete di petri
	 */
    public RetePetri(ReteSemplice daUsare) {
		this.insiemePosti = daUsare.getInsiemePosti();
		this.insiemeTransizioni = daUsare.getInsiemeTransizioni();
		this.relazioneFlusso = daUsare.getRelazioneFlusso();
		this.marcatura = new MarcaturaPN(daUsare.getInsiemePosti());
		this.listaPesi = new ListaPesiFlussoPN(daUsare.getRelazioneFlusso());
    }

	public RetePetri(ArrayList<Posto> insiemePosti, ArrayList<Transizione> insiemeTransizioni, ArrayList<ElemFlusso> relazioneFlusso, MarcaturaPN marcatura, ListaPesiFlussoPN listaPesi){
		this.insiemePosti = insiemePosti;
		this.insiemeTransizioni = insiemeTransizioni;
		this.relazioneFlusso = relazioneFlusso;
		this.marcatura = marcatura;
		this.listaPesi = listaPesi;
	}

	public MarcaturaPN getMarcatura(){
		return this.marcatura;
	}

	public ListaPesiFlussoPN getListaPesi(){
		return this.listaPesi;
	}


	/**
	 * Metodo per controllare se due reti sono o meno uguali
	 * <em> Due reti di petri (derivate dalla stessa rete) si considerano uguali se la loro marcatura e la loro lista pesi e' uguale </em>
	 * Si assume che le reti da cui sono derivate le due reti di petri siano uguali, altrimenti le reti di petri sono diverse a prescindere
	 * dalla marcatura e dalla lista pesi
	 * @param toCompare rete da comparare
	 * @return boolean che indica se le due reti sono uguali o meno 
	 */
	public boolean controlloRetePetriUguale(RetePetri toCompare){
		if(isEqual(toCompare)) return false;
		if(!this.getMarcatura().isEqual(toCompare.getMarcatura()) || !this.getListaPesi().isEqual(toCompare.getListaPesi())){
			return false;
		}			
		
		return true;
	}

	/**
	 * Metodo per trovare le <em> transizioni abilitate </em> all'interno della rete.
	 * Una transizione e' abilitata se il posto di partenza ha una marcatura maggiore o uguale
	 * del peso per far scattare la transizione.
	 * @return arrayList contenente tutte le transizioni abilitate. 
	 */
	public ArrayList<ElemFlusso> getPossibiliTransizioni(){
		ArrayList<ElemFlusso> possibiliTrans = new ArrayList<ElemFlusso>();
		for(int i=0; i<marcatura.getListaPosti().size(); i++){
			Posto postoControllato = marcatura.getListaPosti().get(i);
			Integer marcaturaPostoControllato = marcatura.getMarcatura().get(i);
			for(int j=0; j<listaPesi.getListaElemFlusso().size(); j++){
				ElemFlusso elemControllato = listaPesi.getListaElemFlusso().get(j);
				if(elemControllato.getElem1().equalControl(postoControllato)){
					if(marcaturaPostoControllato>= listaPesi.getListaPesi().get(j)){
						possibiliTrans.add(elemControllato);
					}
				}
			}
		}
		return possibiliTrans;
	}

	/**
	 * Metodo per aggiornare la marcatura della rete in seguito allo scatto di una transizione.
	 * @param elemScelto elemento di flusso che identifica la transizione da far scattare.
	 */
	public void aggiornaMarcaturaPerSimulazione(ElemFlusso elemScelto){
		ElementoSemplice daCercare = elemScelto.getElem2();
		ElementoSemplice primoPosto = elemScelto.getElem1();
		int indice = -1;
		boolean trovato = false;
		for(int j=0; j<listaPesi.getListaElemFlusso().size(); j++){
			if(listaPesi.getListaElemFlusso().get(j).controlloUguali(elemScelto)){
				indice = j;
				trovato = true;
				break;
			}
		}
		if(trovato){
			Integer pesoIniziale = listaPesi.getListaPesi().get(indice);
			marcatura.impostaNuovaMarcaturaConDifferenza(primoPosto.getName(), pesoIniziale);
		}

		for(int i=0; i<listaPesi.getListaElemFlusso().size(); i++){
			if(listaPesi.getListaElemFlusso().get(i).getElem1().equalControl(daCercare)){
				Integer peso = listaPesi.getListaPesi().get(i);
				String nomePosto = listaPesi.getListaElemFlusso().get(i).getElem2().getName();
				marcatura.impostaNuovaMarcaturaConSomma(nomePosto, peso);
			}
		}
		
	}

	@Override
	public ArrayList<Posto> getInsiemePosti() {
	    return this.insiemePosti;	    
	}

	@Override
	public ArrayList<Transizione> getInsiemeTransizioni() {
	    return this.insiemeTransizioni;
	}

	@Override
	public ArrayList<ElemFlusso> getRelazioneFlusso() {
	    return this.relazioneFlusso;
	}

	@Override
	public Posto getPostoByName(String daCercare) throws NonPresenteException {
	    assert daCercare != null : "stringaNome = null"; // precondizione
	    var trovato = Stream.of(getInsiemePosti().toArray(new Posto[0]))
		    .filter(n -> n.getName().equalsIgnoreCase(daCercare)).findFirst().orElse(null);
	    if (trovato == null)
		throw new NonPresenteException(QUESTO_ELEMENTO_NON_E_PRESENTE);
	    return trovato;
	}

	@Override
	public Transizione getTransByName(String daCercare) throws NonPresenteException {
	    assert daCercare != null : "stringaNome = null"; // precondizione
	    var trovato = Stream.of(getInsiemeTransizioni().toArray(new Transizione[0]))
		    .filter(n -> n.getName().equalsIgnoreCase(daCercare)).findFirst().orElse(null);
	    if (trovato == null)
		throw new NonPresenteException(QUESTO_ELEMENTO_NON_E_PRESENTE);
	    return trovato;
	}

	@Override
	public ElemFlusso getElemFlussoByName(String daCercare) throws NonPresenteException {
	    assert daCercare != null : "stringaNome = null"; // precondizione
	    var trovato = Stream.of(getRelazioneFlusso().toArray(new ElemFlusso[0]))
		    .filter(n -> new ElemFlussoPresentation(n).getName().equalsIgnoreCase(daCercare)).findFirst()
		    .orElse(null);
	    if (trovato == null)
		throw new NonPresenteException(QUESTO_ELEMENTO_NON_E_PRESENTE);
	    return trovato;
	}

	@Override
	public boolean isEqual(ReteInterface toCheck) {
	    assert toCheck != null : "toCheck = null";
	    boolean uguali = BOOL_CONST_FALSE;
	    if (this.getInsiemePosti().size() != toCheck.getInsiemePosti().size())
		return BOOL_CONST_FALSE;
	    if (this.getInsiemeTransizioni().size() != toCheck.getInsiemeTransizioni().size())
		return BOOL_CONST_FALSE;
	    if (this.getRelazioneFlusso().size() != toCheck.getRelazioneFlusso().size())
		return BOOL_CONST_FALSE;

	    for (ElemFlusso elemRete1 : this.getRelazioneFlusso()) {
		ciclo2: for (ElemFlusso elemRete2 : toCheck.getRelazioneFlusso()) {
		    if (elemRete1.controlloUguali(elemRete2)) {
			uguali = BOOL_CONST_TRUE;
			break ciclo2;
			// se ne trovo uno uguale esco dal ciclo interno
			// con valore BOOL_CONST_TRUE
		    }
		    uguali = BOOL_CONST_FALSE;
		}
		if (!uguali)
		    return BOOL_CONST_FALSE;
	    }
	    return BOOL_CONST_TRUE;
	}

	@Override
	public boolean emptyControl() {
	    if (insiemePosti.isEmpty() || insiemeTransizioni.isEmpty() || relazioneFlusso.isEmpty())
		return BOOL_CONST_TRUE;
	    else
		return BOOL_CONST_FALSE;
	}


}
