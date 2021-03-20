package uniBS.ingeSW.progetto.utils;

import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;
import uniBS.ingeSW.progetto.rete.Rete;

public class Main {

    private static final String [] VOCI_MENU_INIZIALE= {"Crea nuova rete",
     "Visualizza le reti esistenti"};

     private static final String TITOLO = 
  
        "$$$$$$$   $$$$$$$$  $$$$$$$$  $$$$$$$$        $$     $$    $$         $$$$$$\n" + 
        "$$    $$  $$           $$     $$              $$     $$  $$$$        $$$   $$\n" +
        "$$    $$  $$           $$     $$              $$     $$    $$        $$$$  $$\n" +
        "$$$$$$$   $$$$$        $$     $$$$$            $$   $$     $$        $$ $$ $$\n" +
        "$$    $$  $$           $$     $$                $$ $$      $$        $$  $$$$\n" +
        "$$    $$  $$           $$     $$                 $$$       $$        $$   $$$\n" +
        "$$    $$  $$$$$$$$     $$     $$$$$$$$            $      $$$$$$  $$   $$$$$$\n";                                                                               
                                                                            
    public static void main(String[] args) {

        Menu menuIniziale = new Menu("seleziona un'alternativa.", VOCI_MENU_INIZIALE );
        
        GestoreReti retiSalvate = new GestoreReti();
        boolean fine = false;
        System.out.println(TITOLO);
        do{

            int scelta1 = menuIniziale.scegli();
            switch(scelta1){
                case 1: InterazioneUtente.creazioneRete(new Rete(), retiSalvate);
                        break;
                case 2: InterazioneUtente.visualizzaReteDaGestore(retiSalvate);
                        break;

                case 0: fine=true;
                        break;
                        
                default : System.out.println("Attenzione selezione non valida");
                
            }
        }while(fine == false);

    }
}
