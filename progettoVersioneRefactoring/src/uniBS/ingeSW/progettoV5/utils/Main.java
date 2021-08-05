package uniBS.ingeSW.progettoV5.utils;

import java.io.File;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.Rete;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class Main {

   

    public static final String RETI_PETRI_PRIORITA = "RETI_PETRI_PRIORITA";

    public static final String RETI_PETRI = "RETI_PETRI";

    public static final String RETI = "RETI";                                                                              
                                                                            
    public static void main(String[] args) {  
        GestoreReti retiSalvate = recuperoOCreazione();
        GestoreRetiPetri retiPNSalvate = recuperoOCreazionePetri();
        GestoreRetiPetriPriorita retiPNPSalvate = recuperoOCreazionePetriPriorita();
        ControllerMVC controller = new ControllerMVC();
        controller.startMenuUI(retiSalvate, retiPNSalvate, retiPNPSalvate);
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

    public static GestoreRetiPetriPriorita recuperoOCreazionePetriPriorita(){
        GestoreRetiPetriPriorita retiSalvate = new GestoreRetiPetriPriorita();
        File folder = new File(RETI_PETRI_PRIORITA);

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
}

