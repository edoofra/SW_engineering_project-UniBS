package uniBS.ingeSW.progettoV2.view;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;
import uniBS.ingeSW.progettoV2.logica.retePetri.MarcaturaPN;

public class MarcaturaPresentation  {
    
    MarcaturaPN marcatura;

    public MarcaturaPresentation (MarcaturaPN daPresentare){
        this.marcatura = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("Marcatura: \n { ");
        for(Posto eachPosto : marcatura.getMarcatura().keySet().toArray(new Posto[0])){
            builder.append(eachPosto.getName() + ":");
            builder.append(marcatura.getMarcatura().get(eachPosto) + ", ");
        }
        int lenght = builder.length();
        builder.delete(lenght-1, lenght);
        builder.append(" }");
        return builder.toString();
    }


}
