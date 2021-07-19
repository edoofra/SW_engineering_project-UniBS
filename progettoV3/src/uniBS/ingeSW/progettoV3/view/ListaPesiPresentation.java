package uniBS.ingeSW.progettoV3.view;

import uniBS.ingeSW.progettoV3.logica.retePetri.ListaPesiFlussoPN;

/**
 * Classe per la gestione della <em>presentazione </em> della classe ListaPesiFlussoPN.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */

public class ListaPesiPresentation {

    private ListaPesiFlussoPN listaPesi;

    public ListaPesiPresentation(ListaPesiFlussoPN daPresentare){
        this.listaPesi = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("LISTA PESI: { ");
        for(int i=0; i<listaPesi.getListaPesi().size(); i++){
            builder.append(new ElemFlussoPresentation(listaPesi.getListaElemFlusso().get(i)).getName()+ ":");
            builder.append(listaPesi.getListaPesi().get(i) + ", ");
        }
        int lenght = builder.length();
        builder.delete(lenght-2, lenght);
        builder.append(" }");
        return builder.toString();
    }
    
}
