package uniBS.ingeSW.progetto.rete;

public class ElemFlusso {

	ElementoSemplice elem1;
	ElementoSemplice elem2;
	
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
	    return "( " + elem1.getName() + "," + elem2.getName() + " )";
	}

	
}
