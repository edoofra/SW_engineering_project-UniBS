package uniBS.ingeSW.progettoV4.view;

import uniBS.ingeSW.progettoV4.logica.rete.ElemFlusso;

public class ElemFlussoPresentation {
    
    private ElemFlusso daPresentare;
    
    public ElemFlussoPresentation(ElemFlusso daPresentare) {
	    this.daPresentare = daPresentare;
    }
    
    public String getName() {
	    return "(" + daPresentare.getElem1().getProperties() + "," + daPresentare.getElem2().getProperties() + ")";
	}

}
