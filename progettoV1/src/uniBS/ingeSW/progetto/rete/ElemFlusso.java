package uniBS.ingeSW.progetto.rete;

public class ElemFlusso {

	private ElementoSemplice elem1;
	private ElementoSemplice elem2;
	
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
	
	public boolean equal(ElemFlusso flusso1, ElemFlusso flusso2) {
	    
	    return ((flusso1.getElem1().equals(flusso2.getElem1()) && 
		    (flusso1.getElem2().equals(flusso2.getElem2()))) ? true : false);
	}

	
}
