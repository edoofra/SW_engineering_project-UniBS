package uniBS.ingeSW.progettoV4.view;

import uniBS.ingeSW.progettoV4.logica.retePetri.MarcaturaPN;

public class MarcaturaPresentation  {
    
    MarcaturaPN marcatura;

    public MarcaturaPresentation (MarcaturaPN daPresentare){
        this.marcatura = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("MARCATURA: { ");
        for(int i=0; i<marcatura.getListaPosti().size(); i++){
            builder.append(marcatura.getListaPosti().get(i).getName() + ":");
            builder.append(marcatura.getMarcatura().get(i) + ", ");
        }
        int lenght = builder.length();
        builder.delete(lenght-2, lenght);
        builder.append(" }");
        return builder.toString();
    }


}
