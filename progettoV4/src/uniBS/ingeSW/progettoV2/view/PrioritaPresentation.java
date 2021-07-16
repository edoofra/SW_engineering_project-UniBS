package uniBS.ingeSW.progettoV2.view;

import uniBS.ingeSW.progettoV2.logica.retePetriPriorita.Priorita;

public class PrioritaPresentation {
    
    Priorita priorita;

    public PrioritaPresentation (Priorita daPresentare){
        this.priorita = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("PRIORITA': { ");
        for(int i=0; i<priorita.getListaTransizioni().size(); i++){
            builder.append(priorita.getListaTransizioni().get(i).getName() + ":");
            builder.append(priorita.getListaPriorita().get(i) + ", ");
        }
        int lenght = builder.length();
        builder.delete(lenght-2, lenght);
        builder.append(" }");
        return builder.toString();
    }


}
