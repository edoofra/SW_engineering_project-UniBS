package uniBS.ingeSW.progettoV4.utils;

import java.io.File;

import uniBS.ingeSW.progettoV4.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV4.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV4.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV4.logica.rete.Rete;
import uniBS.ingeSW.progettoV4.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV4.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV4.utils.eccezioni.giaPresenteException;

public class Main {

    private static final String [] VOCI_MENU_INIZIALE= {"Crea nuova rete",
     "Visualizza le reti esistenti", "Visualizza le reti di Petri esistenti", "Estendi una rete in rete di Petri",
    "Estendi una rete di petri in rete di petri con priorità", "Visualizza le reti di petri con priorità esistenti"};

    private static final String[] VOCI_MENU_ESTERNO= {"Configuratore", "Fruitore"};

    private static final String[] VOCI_MENU_FRUITORE= {"Simula l'evoluzione di una rete di petri", "Simula l'evoluzione di una rete di petri con priorità"};
    
     private static final String TITOLO = 
  
     "$$$$$$$   $$$$$$$$  $$$$$$$$  $$$$$$$$        $$     $$     $$    $$        $$$$$$\n" + 
     "$$    $$  $$           $$     $$              $$     $$     $$    $$       $$$   $$\n" +
     "$$    $$  $$           $$     $$              $$     $$     $$    $$       $$$$  $$\n" +
     "$$$$$$$   $$$$$        $$     $$$$$            $$   $$      $$$$$$$$       $$ $$ $$\n" +
     "$$    $$  $$           $$     $$                $$ $$             $$       $$  $$$$\n" +
     "$$    $$  $$           $$     $$                 $$$              $$       $$   $$$\n" +
     "$$    $$  $$$$$$$$     $$     $$$$$$$$            $               $$  $$    $$$$$$\n";                                                                                
                                                                            
    public static void main(String[] args) {  
        GestoreReti retiSalvate = recuperoOCreazione();
        GestoreRetiPetri retiPNSalvate = recuperoOCreazionePetri();
        GestoreRetiPetriPriorita retiPNPSalvate = recuperoOCreazionePetriPriorita();
        System.out.println(TITOLO);
        cicloSceltaMenuEsterno(retiPNSalvate, retiSalvate, retiPNPSalvate);
    }

    public static GestoreReti recuperoOCreazione(){
        GestoreReti retiSalvate = new GestoreReti();
        File folder = new File("RETI");

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
        File folder = new File("RETI_PETRI");

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

    public static GestoreRetiPetriPriorita recuperoOCreazionePetriPriorita(){
        GestoreRetiPetriPriorita retiSalvate = new GestoreRetiPetriPriorita();
        File folder = new File("RETI_PETRI_PRIORITA");

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
                String retePetriJson = salvataggioFile.leggiGestoreRetiDaFile(file.getPath());
                RetePetriPriorita reteCaricata = ConvertitoreJson.daJsonARetePetriPriorita(retePetriJson); //
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



    
    public static void cicloSceltaMenuConfiguratore(GestoreReti retiSalvate, GestoreRetiPetri retiPNSalvate, GestoreRetiPetriPriorita retiPNPSalvate){
        Menu menuIniziale = new Menu("seleziona un'alternativa.", VOCI_MENU_INIZIALE);
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
                        
                case 5: InterazioneUtenteModel.estendiRetePNInPNConPriorita(retiPNSalvate, retiPNPSalvate);
                    	break;

                case 6: InterazioneUtenteModel.visualizzaRetiPetriPrioritaDaGestore(retiPNPSalvate);
                    	break;

                case 0: fine=true;
                        break;

                default : System.out.println("Attenzione selezione non valida");
            }
        }while(fine == false);
    }

    public static void cicloSceltaMenuFruitore(GestoreRetiPetri retiPNSalvate, GestoreRetiPetriPriorita retiPNPSalvate){
        Menu menuFruitore = new Menu("seleziona un'alternativa", VOCI_MENU_FRUITORE);
        boolean fine = false;
        do{
            //vuole chiamata a un solo metodo nello switch e con nomi che si capiscano
            int scelta1 = menuFruitore.scegli();
            switch(scelta1){

                case 1: InterazioneUtenteModel.simulazioneEvoluzioneRete(retiPNSalvate);
                        break;

                case 2: InterazioneUtenteModel.simulazioneEvoluzioneRetePriorita(retiPNPSalvate);
                        break;

                case 0: fine=true;
                        break;

                default : System.out.println("Attenzione selezione non valida");
            }
        }while(fine == false);
    }



    public static void cicloSceltaMenuEsterno(GestoreRetiPetri retiPNSalvate, GestoreReti retiSalvate, GestoreRetiPetriPriorita retiPNPSalvate){
        Menu menuEsterno = new Menu("seleziona il profilo con cui accedere.", VOCI_MENU_ESTERNO);
        boolean fine = false;
        do{
            int scelta=menuEsterno.scegli();
            switch(scelta){

                case 1 : cicloSceltaMenuConfiguratore(retiSalvate, retiPNSalvate, retiPNPSalvate);
                        break;
                
                case 2: cicloSceltaMenuFruitore(retiPNSalvate, retiPNPSalvate);
                        break;

                case 0: fine=true;
                        break;

                default : System.out.println("Attenzione selezione non valida");
            }


        }while(!fine);

    }
}

