package uniBS.ingeSW.progettoV3.view;

import uniBS.ingeSW.progettoV3.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV3.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV3.logica.rete.Posto;
import uniBS.ingeSW.progettoV3.logica.rete.Transizione;
import uniBS.ingeSW.progettoV3.logica.retePetri.RetePetri;

/**
 * Classe per la gestione della <em>presentazione </em> della classe RetePetri.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */

public class RetePetriPresentation {
    
    private RetePetri daPresentare;

    /**
	 * Metodo per istanziare la rete da presentare.
	 * @param daPresentare Rete di cui costruire la presentazione.
	 */
    public RetePetriPresentation(RetePetri daPresentare) {
        this.daPresentare = daPresentare;
    }
    
    /**
     * Metodo che ritorna una stringa composta dai nomi dei posti e delle transizioni della rete.
     * @overloaded
     * @return stringa creata.
     */
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
    
    /**
     * Metodo che ritorna una stringa composta dai nomi degli elementi di flusso.
     * @overloaded
     * @return stringa creata.
     */
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
        description.append(new MarcaturaPresentation(daPresentare.getMarcatura()).toString() + "\n");
        description.append(new ListaPesiPresentation(daPresentare.getListaPesi()).toString() + "\n");
        
        return description.toString();

    }
}
