package uniBS.ingeSW.progettoV3.utils;

import java.io.File;

import uniBS.ingeSW.progettoV3.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV3.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV3.logica.rete.Rete;
import uniBS.ingeSW.progettoV3.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV3.utils.eccezioni.giaPresenteException;

public class Main {
    public static final String SELEZIONA_IL_PROFILO_CON_CUI_ACCEDERE = "seleziona il profilo con cui accedere.";

    public static final String ATTENZIONE_SELEZIONE_NON_VALIDA = "Attenzione selezione non valida";

    private static final String SELEZIONA_UN_ALTERNATIVA = "seleziona un'alternativa.";

    public static final String RETI_PETRI = "RETI_PETRI";

    public static final String RETI = "RETI";

    private static final String [] VOCI_MENU_INIZIALE= {"Crea nuova rete",
     "Visualizza le reti esistenti", "Visualizza le reti di Petri esistenti", "Estendi una rete di Petri"};

    private static final String[] VOCI_MENU_ESTERNO= {"Configuratore", "Fruitore"};

    private static final String[] VOCI_MENU_FRUITORE= {"Simula l'evoluzione di una rete"};
    
     private static final String TITOLO = 
  
     "$$$$$$$   $$$$$$$$  $$$$$$$$  $$$$$$$$        $$     $$      $$$$$$         $$$$$$\n" + 
     "$$    $$  $$           $$     $$              $$     $$     $$    $$       $$$   $$\n" +
     "$$    $$  $$           $$     $$              $$     $$           $$       $$$$  $$\n" +
     "$$$$$$$   $$$$$        $$     $$$$$            $$   $$        $$$$$        $$ $$ $$\n" +
     "$$    $$  $$           $$     $$                $$ $$             $$       $$  $$$$\n" +
     "$$    $$  $$           $$     $$                 $$$        $$    $$       $$   $$$\n" +
     "$$    $$  $$$$$$$$     $$     $$$$$$$$            $          $$$$$    $$    $$$$$$\n";                                                                                
                                                                            
    public static void main(String[] args) {  
        GestoreReti retiSalvate = recuperoOCreazione();
        GestoreRetiPetri retiPNSalvate = recuperoOCreazionePetri();
        System.out.println(TITOLO);
        cicloSceltaMenuEsterno(retiPNSalvate, retiSalvate);
    }

    public static GestoreReti recuperoOCreazione(){
        GestoreReti retiSalvate = new GestoreReti();
        File folder = new File(RETI);

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
                String reteJson = salvataggioFile.leggiGestoreRetiDaFile(file.getPath());
                Rete reteCaricata = ConvertitoreJson.daJsonAOggettoHashSet(reteJson);
                try {
                    retiSalvate.addRete(file.getName(), reteCaricata);
                } catch (giaPresenteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		}
        return retiSalvate;
    }

    public static GestoreRetiPetri recuperoOCreazionePetri(){
        GestoreRetiPetri retiSalvate = new GestoreRetiPetri();
        File folder = new File(RETI_PETRI);

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
                String retePetriJson = salvataggioFile.leggiGestoreRetiDaFile(file.getPath());
                RetePetri reteCaricata = ConvertitoreJson.daJsonARetePetri(retePetriJson);
                try {
                    retiSalvate.addRete(file.getName(), reteCaricata);
                } catch (giaPresenteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		}
        return retiSalvate;
    }



    
    public static void cicloSceltaMenuConfiguratore(GestoreReti retiSalvate, GestoreRetiPetri retiPNSalvate){
        Menu menuIniziale = new Menu(SELEZIONA_UN_ALTERNATIVA, VOCI_MENU_INIZIALE);
        boolean fine = false;
        do{
            
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
                        
                case 5: InterazioneUtenteModel.simulazioneEvoluzioneRete(retiPNSalvate);
                    	break;

                case 0: fine=true;
                        break;

                default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
            }
        }while(fine == false);
    }

    public static void cicloSceltaMenuFruitore(GestoreRetiPetri retiPNSalvate){
        Menu menuFruitore = new Menu(SELEZIONA_UN_ALTERNATIVA, VOCI_MENU_FRUITORE);
        boolean fine = false;
        do{
            //vuole chiamata a un solo metodo nello switch e con nomi che si capiscano
            int scelta1 = menuFruitore.scegli();
            switch(scelta1){

                case 1: InterazioneUtenteModel.simulazioneEvoluzioneRete(retiPNSalvate);
                        break;

                case 0: fine=true;
                        break;

                default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
            }
        }while(fine == false);
    }



    public static void cicloSceltaMenuEsterno(GestoreRetiPetri retiPNSalvate, GestoreReti retiSalvate){
        Menu menuEsterno = new Menu(SELEZIONA_IL_PROFILO_CON_CUI_ACCEDERE, VOCI_MENU_ESTERNO);
        boolean fine = false;
        do{
            int scelta=menuEsterno.scegli();
            switch(scelta){

                case 1 : cicloSceltaMenuConfiguratore(retiSalvate, retiPNSalvate);
                        break;
                
                case 2: cicloSceltaMenuFruitore(retiPNSalvate);
                        break;

                case 0: fine=true;
                        break;

                default : System.out.println(ATTENZIONE_SELEZIONE_NON_VALIDA);
            }


        }while(!fine);

    }
}

