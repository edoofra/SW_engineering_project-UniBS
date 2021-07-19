package uniBS.ingeSW.progettoV4.view;

import uniBS.ingeSW.progettoV4.logica.retePetriPriorita.Priorita;

/**
 * Classe per la gestione della <em>presentazione </em> della classe Priorita.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
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
