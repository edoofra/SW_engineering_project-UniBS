package uniBS.ingeSW.progettoV5.view;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;

/**
 * Classe per la gestione della <em>presentazione </em> della classe ElemFlusso.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
public class ElemFlussoPresentation {
    
    private ElemFlusso daPresentare;
    
    public ElemFlussoPresentation(ElemFlusso daPresentare) {
	    this.daPresentare = daPresentare;
    }
    
    public String getName() {
	    return "(" + daPresentare.getElem1().getProperties() + "," + daPresentare.getElem2().getProperties() + ")";
	}

}
