package uniBS.ingeSW.progettoV2.view;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV2.logica.rete.Posto;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;
import uniBS.ingeSW.progettoV2.logica.rete.Transizione;

public class RetePresentation {
    
    private Rete daPresentare;
    
    public RetePresentation(Rete daPresentare) {
	this.daPresentare = daPresentare;
    }
    
    public String getStringList(ElementoSemplice[] list) {
	StringBuilder stringList = new StringBuilder("{ ");
	for (int i = 0; i < list.length; i++) {
		stringList.append(list[i].getProperties() + ", ");
	}
	int last = stringList.length();
	stringList.delete(last-2, last-1);
	stringList.append("}");
	return stringList.toString();
    }
    
    public String getStringList(ElemFlusso[] list) {
	StringBuilder stringList = new StringBuilder("{ ");
	for (int i = 0; i < list.length; i++) {
	    	ElemFlussoPresentation view = new ElemFlussoPresentation(list[i]);
		stringList.append(view.getName() + ", ");
	}
	int last = stringList.length();
	stringList.delete(last-2, last-1);
	stringList.append("}");
	return stringList.toString();
}
    
    public String toString() {
	StringBuilder description = new StringBuilder("Descrizione della rete: \n");
	description.append("POSTI: " + getStringList(daPresentare.getInsiemePosti().toArray(new Posto[0])) + "\n");
	description.append("TRANSIZIONI: " + getStringList(daPresentare.getInsiemeTransizioni().toArray(new Transizione[0])) + "\n");
	description.append("RELAZIONE FLUSSO: " + getStringList(daPresentare.getRelazioneFlusso().toArray(new ElemFlusso[0])) + "\n");
	return description.toString();

}

}
