package uniBS.ingeSW.progettoV2.utils;

import java.io.File;

import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;

public class Main {

    /**
     *
     */
    private static final String PATH_RETE = "ListaRetiConfiguratore.json";
    private static final String PATH_RETE_PETRI = "ListaRetiPetriConfiguratore.json";

    private static final String [] VOCI_MENU_INIZIALE= {"Crea nuova rete",
     "Visualizza le reti esistenti", "Visualizza le reti di Petri esistenti", "Estendi una rete di Petri"};

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
        GestoreRetiPetri retiPNSalvate = recuperoOCreazionePetri();
        System.out.println(TITOLO);
        cicloSceltaMenu(retiSalvate, retiPNSalvate);
    }

    public static GestoreReti recuperoOCreazione(){
        GestoreReti retiSalvate;
        
        File fileSalvataggio = new File(PATH_RETE);
        if(fileSalvataggio.exists() && fileSalvataggio.length() != 0) {
            String retiSalvateJSON = salvataggioFile.leggiGestoreRetiDaFile(PATH_RETE);
            retiSalvate = ConvertitoreJson.daJsonAOggettoHashSet(retiSalvateJSON);
            return retiSalvate;
        }
        return new GestoreReti();
    }

    public static GestoreRetiPetri recuperoOCreazionePetri(){
        GestoreRetiPetri retiSalvate;
        
        File fileSalvataggio = new File(PATH_RETE_PETRI);
        if(fileSalvataggio.exists() && fileSalvataggio.length() != 0) {
            String retiSalvateJSON = salvataggioFile.leggiGestoreRetiDaFile(PATH_RETE_PETRI);
            retiSalvate = ConvertitoreJson.daJsonAOggettoPetriHashSet(retiSalvateJSON);
            return retiSalvate;
        }
        return new GestoreRetiPetri();
    }



    
    public static void cicloSceltaMenu(GestoreReti retiSalvate, GestoreRetiPetri retiPNSalvate){
        Menu menuIniziale = new Menu("seleziona un'alternativa.", VOCI_MENU_INIZIALE);
        boolean fine = false;
        do{
            //vuole chiamata a un solo metodo nello switch e con nomi che si capiscano
            int scelta1 = menuIniziale.scegli();
            switch(scelta1){
                case 1: InterazioneUtenteModel.aggiuntaRete(new Rete(), retiSalvate);
                        break;
                        
                case 2: InterazioneUtenteModel.visualizzaRetiDaGestore(retiSalvate);
                        break;

                case 3: InterazioneUtenteModel.visualizzaRetiPetriDaGestore(retiPNSalvate);
                        break;

                case 4: InterazioneUtenteModel.estendiReteInPN(retiSalvate,retiPNSalvate);
                        break;

                case 0: fine=true;
                        break;

                default : System.out.println("Attenzione selezione non valida");
            }
        }while(fine == false);
    }
}

