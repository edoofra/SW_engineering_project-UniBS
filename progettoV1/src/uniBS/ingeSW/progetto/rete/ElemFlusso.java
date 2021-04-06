package uniBS.ingeSW.progetto.rete;

/**
 * @author Lorenzo Bargnani
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * classe per l'implementazione di un elemento di flusso di una rete
 */
public class ElemFlusso {

	
	private ElementoSemplice elem1; //elemento di partenza	
	private ElementoSemplice elem2; //elemento di arrivo
	
	public ElemFlusso(ElementoSemplice elem1, ElementoSemplice elem2) {
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	public ElementoSemplice getElem1() {
		return elem1;
	}

	public ElementoSemplice getElem2() {
		return elem2;
	}
	
	public String getName() {
	    return "( " + elem1.getProperties() + "," + elem2.getProperties() + " )";
	}
	
	/**
	 * confronta due elementi di flusso per valutare se siano uguali o meno
	 * @param flusso1
	 * @param flusso2
	 * @return true se sono uguali, false altrimenti
	 */
	public boolean equal(ElemFlusso flusso1, ElemFlusso flusso2) {
	    
	    return ((flusso1.getElem1().equals(flusso2.getElem1()) && 
		    (flusso1.getElem2().equals(flusso2.getElem2()))) ? true : false);
	}

	
}
