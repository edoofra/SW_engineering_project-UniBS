package uniBS.ingeSW.progetto.utils;

import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;
import uniBS.ingeSW.progetto.rete.Rete;

public class Main {

    private static final String [] VOCI_MENU_INIZIALE= {"crea nuova rete",
     "visualizza le reti esistenti"};

    public static void main(String[] args) {

        Menu menuIniziale = new Menu("Seleziona una tra le seguenti scelte", VOCI_MENU_INIZIALE );
        GestoreReti retiSalvate = new GestoreReti();
        boolean fine = false;

        do{
            int scelta1 = menuIniziale.scegli();
            switch(scelta1){
                case 1: InterazioneUtente.creazioneRete(new Rete());
                        break;
                case 2: InterazioneUtente.visualizzaReteDaGestore(retiSalvate);
                        break;

                case 0: fine=true;
                        break;
                        
                default : System.out.println("attenzione selezione non valida");
                
            }
        }while(fine == false);

    }
}
