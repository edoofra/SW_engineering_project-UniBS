package uniBS.ingeSW.progettoV5.logica.retePetriPriorita;

import java.util.ArrayList;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.ReteInterface;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
import uniBS.ingeSW.progettoV5.logica.retePetri.ListaPesiFlussoPN;
import uniBS.ingeSW.progettoV5.logica.retePetri.MarcaturaPN;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;
import uniBS.ingeSW.progettoV5.view.ElemFlussoPresentation;

/**
 * Classe per l'implementazione di una rete di petri che estende una rete.
 * Una rete di petri e' composta da <ul> <li> <em> un insieme di posti </em> </li>
 * <li> <em> un insieme di transizioni </em> </li>
 * <li> <em> un insieme di elementi di flusso </em> </li>
 * <li> <em> una marcatura </em> </li> <li> <em> una lista pesi flusso </em> </li> 
 * <li> <em>una lista di priorita'</em></li></ul>
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da almeno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 2.0 - attuata separazione modello-vista. Cambiati getters e setters.
 */
public class RetePetriPriorita implements ReteInterface{
    
    private static final String QUESTO_ELEMENTO_NON_E_PRESENTE = "Questo elemento non Ã¨ presente.";
    private static final boolean BOOL_CONST_TRUE = true;
    private static final boolean BOOL_CONST_FALSE = false;

    private Priorita listaPriorita;
    private MarcaturaPN marcatura;
    private ListaPesiFlussoPN listaPesi;
    private ArrayList<Posto> insiemePosti;
    private ArrayList<Transizione> insiemeTransizioni;
    private ArrayList<ElemFlusso> relazioneFlusso;

    public RetePetriPriorita(RetePetri retePN){
        this.insiemePosti = retePN.getInsiemePosti();
        this.insiemeTransizioni = retePN.getInsiemeTransizioni();
        this.relazioneFlusso = retePN.getRelazioneFlusso();
        this.marcatura = retePN.getMarcatura();
        this.listaPesi = retePN.getListaPesi();
        this.listaPriorita = new Priorita(retePN.getInsiemeTransizioni());
    }

    public Priorita getPriorita(){
        return this.listaPriorita;
    }

    /**
    	 * Metodo per controllare se due reti di Petri con priorita'  sono o meno uguali
    	 * <em> Due reti di petri (derivate dalla stessa rete) si considerano uguali se la loro marcatura e la loro lista pesi sono uguali </em>
    	 * Due reti di Petri con Priorita' devono inoltre avere le stesse priorita' assegnate alle transizioni 
         * per essere considerate uguali.
         * Si assume che le reti da cui sono derivate le due reti di petri siano uguali, altrimenti le reti di petri sono diverse a prescindere
    	 * dalla marcatura e dalla lista pesi
    	 * @param toCompare rete da comparare
    	 * @return boolean che indica se le due reti sono uguali o meno 
    	 */
    public boolean controlloRetePetriPrioritaUguale(RetePetriPriorita toCompare){
		if(!controlloRetePetriUguale(toCompare)) return false;
		if(!this.getPriorita().isEqualPriorita(toCompare.getPriorita())){
			return false;
		}			
		
		return true;
	}
    
    public MarcaturaPN getMarcatura(){
	return this.marcatura;
    }
    
    public ListaPesiFlussoPN getListaPesi(){
	return this.listaPesi;
    }
    
    public boolean controlloRetePetriUguale(RetePetriPriorita toCompare){
	if(isEqual(toCompare)) return false;
	if(!this.marcatura.isEqual(toCompare.getMarcatura()) || !this.listaPesi.isEqual(toCompare.getListaPesi())){
		return false;
	}			
	
	return true;
}

    public ArrayList<ElemFlusso> getTransizioniPrioritaMaggiore(ArrayList<ElemFlusso> transizioniPossibili){
        return listaPriorita.getTransizioniPrioritaMaggiore(transizioniPossibili);
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
}
