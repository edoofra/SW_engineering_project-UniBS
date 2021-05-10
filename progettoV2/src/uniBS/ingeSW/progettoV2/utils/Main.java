package uniBS.ingeSW.progettoV2.utils;

import java.io.File;

import uniBS.ingeSW.progettoV2.logica.*;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;

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
        GestoreReti retiSalvate = recuperoOCreazione();
        System.out.println(TITOLO);
        cicloSceltaMenu(retiSalvate);
    }

    public static GestoreReti recuperoOCreazione(){
        GestoreReti retiSalvate;
        File fileSalvataggio = new File("ListaRetiConfiguratore.json");
        if(fileSalvataggio.exists()) {
            String retiSalvateJSON = salvataggioFile.leggiGestoreRetiDaFile();
            retiSalvate = ConvertitoreJson.daJsonAOggettoHashSet(retiSalvateJSON);
            return retiSalvate;
        }
        return new GestoreReti();
    }

    
    public static void cicloSceltaMenu(GestoreReti retiSalvate){
        Menu menuIniziale = new Menu("seleziona un'alternativa.", VOCI_MENU_INIZIALE);
        boolean fine = false;
        do{
            //vuole chiamata a un solo metodo nello switch e con nomi che si capiscano
            int scelta1 = menuIniziale.scegli();
            switch(scelta1){
                case 1: InterazioneUtenteModel.aggiuntaRete(new Rete(), retiSalvate);
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

