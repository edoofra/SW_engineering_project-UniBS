package uniBS.ingeSW.progettoV2.view;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.retePetri.ListaPesiFlussoPN;

public class ListaPesiPresentation {

    private ListaPesiFlussoPN listaPesi;

    public ListaPesiPresentation(ListaPesiFlussoPN daPresentare){
        this.listaPesi = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("Lista pesi: \n { ");
        for(ElemFlusso eachFlusso : listaPesi.getListaPesi().keySet().toArray(new ElemFlusso[0])){
            builder.append(new ElemFlussoPresentation(eachFlusso).getName()+ ":");
            builder.append(listaPesi.getListaPesi().get(eachFlusso) + ", ");
        }
        int lenght = builder.length();
        builder.delete(lenght-1, lenght);
        builder.append(" }");
        return builder.toString();
    }
    
}
