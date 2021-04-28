package uniBS.ingeSW.progettoV2.logica.rete;
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

	/**
	 * Controlla che l'elemento sia corretto
	 * Un elemento di flusso è corretto se è composto da due elementi di rete diversi
	 */
	public boolean areSameType(){
		boolean doublePosto = this.getElem1() instanceof Posto && this.getElem2() instanceof Posto;
		boolean doubleTransizione = this.getElem1() instanceof Transizione && this.getElem2() instanceof Transizione;
		return (doublePosto || doubleTransizione);
	}
	
	/**
	 * confronta due elementi di flusso per valutare se siano uguali o meno
	 * sono uguali se entrambi gli elementi interni sono uguali e sono nello stesso ordine
	 * @param flusso1
	 * @param flusso2
	 */
	//@requires flusso2 != null;
	public boolean equal( ElemFlusso flusso2) {
	    assert flusso2 != null : "elemFlusso = null"; //precondizione
	    return ((this.getElem1().equalControl(flusso2.getElem1()) && 
		    (this.getElem2().equalControl(flusso2.getElem2()))));
	}	
}
