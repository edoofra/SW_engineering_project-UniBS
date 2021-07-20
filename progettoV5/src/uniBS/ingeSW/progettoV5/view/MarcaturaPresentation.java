package uniBS.ingeSW.progettoV5.view;

import uniBS.ingeSW.progettoV5.logica.retePetri.MarcaturaPN;

/**
 * Classe per la gestione della <em>presentazione </em> della classe MarcaturaPN.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
public class MarcaturaPresentation  {
    
    public static final String MARCATURA = "MARCATURA: { ";
    MarcaturaPN marcatura;

    public MarcaturaPresentation (MarcaturaPN daPresentare){
        this.marcatura = daPresentare;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder(MARCATURA);
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
