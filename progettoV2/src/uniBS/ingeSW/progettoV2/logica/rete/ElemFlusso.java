package uniBS.ingeSW.progettoV2.logica.rete;
/**
 * Classe che descrive l'implementazione di un elemento di flusso all'interno di una rete.
 * ElemFlusso è visto come una coppia di <em> due elementi semplici </em>.
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani 
 * @version 2.0 - attuata separazione modello-vista.
 */
public class ElemFlusso {

	private ElementoSemplice elem1; //elemento di partenza	
	private ElementoSemplice elem2; //elemento di arrivo
	
	/**
	 * Metodo che crea un elemento di flusso a partire da due elementi semplici.
	 * @param elem1 elemento di partenza.
	 * @param elem2 elemento di arrivo.
	 */
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
	 * Metodo che controlla che l'elemento sia corretto.
	 * Un elemento di flusso e' corretto se e' composto da due elementi di rete diversi.
	 * Non sono ammessse coppie transizione-transizione o posto-posto.
	 * @return boolean che dice se l'elemento è corretto.
	 */
	public boolean areSameType(){
		boolean doublePosto = this.getElem1() instanceof Posto && this.getElem2() instanceof Posto;
		boolean doubleTransizione = this.getElem1() instanceof Transizione && this.getElem2() instanceof Transizione;
		return (doublePosto || doubleTransizione);
	}
	
	/**
	 * Metodo che confronta due elementi di flusso per valutare se siano uguali o meno.
	 * Sono uguali se entrambi gli elementi interni sono uguali e sono nello stesso ordine.
	 * @param flusso2 elemento di flusso con cui svolgere il confronto.
	 * @return boolean che indica se i nomi degli elementi semplici coincidono.
	 */
	public boolean controlloUguali( ElemFlusso flusso2) {
	    assert flusso2 != null : "elemFlusso = null"; //precondizione
	    return ((this.getElem1().equalControl(flusso2.getElem1()) && 
		    (this.getElem2().equalControl(flusso2.getElem2()))));
	}	

	public String toString(){
		return "(" + this.getElem1().getProperties() + "," + this.getElem2().getProperties() + ")";
	}
}
