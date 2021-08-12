package uniBS.ingeSW.progettoV5.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.view.InterazioneUtente;

/**
 * Classe per il salvataggio e il recuperso da file di stringhe.
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @version 3.0 - aggiunti metodi per Reti di Petri con Priorita' 
 */
public class salvataggioFile {
    
    
    public static final String RETI = "RETI";
    public static final String RETI_PETRI = "RETI_PETRI";
    public static final String RETI_PETRI_PRIORITA = "RETI_PETRI_PRIORITA";
    private static final String PATH_RETE = "ListaRetiConfiguratore.json";
    private static final String PATH_RETE_PETRI = "ListaRetiPetriConfiguratore.json";

    /**
     * Metodo per il salvataggio di una stringa su file.
     * @param daSalvare Stringa da salvare.
     */
    public static void salvaGestoreReti(String daSalvare, int path){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        String[] possibiliPath = {PATH_RETE, PATH_RETE_PETRI};
        try {
            FileWriter fileSalvataggio = new FileWriter(possibiliPath[path]);
            fileSalvataggio.write(daSalvare);
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per il salvataggio di una rete in formato JSON
     * @param daSalvare JSON da salvare
     * @param nomeRetePath path salvataggio
     */
    public static void salvaRete(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File(RETI);
		if (!folder.exists())
			folder.mkdirs();

        try {
            FileWriter fileSalvataggio = new FileWriter(folder+"/"+nomeRetePath);
            fileSalvataggio.write(daSalvare);
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per il salvataggio di una rete di Petri in formato JSON
     * @param daSalvare JSON da salvare
     * @param nomeRetePath path salvataggio
     */
    public static void salvaRetePetri(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File(RETI_PETRI);
		if (!folder.exists())
			folder.mkdirs();

        try {
            FileWriter fileSalvataggio = new FileWriter(folder+"/"+nomeRetePath);
            fileSalvataggio.write(daSalvare);
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per il salvataggio di una rete di Petri con Priorita' in formato JSON
     * @param daSalvare JSON da salvare
     * @param nomeRetePath path salvataggio
     */
    public static void salvaRetePetriPriorita(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File(RETI_PETRI_PRIORITA);
		if (!folder.exists())
			folder.mkdirs();

        try {
            FileWriter fileSalvataggio = new FileWriter(folder+"/"+nomeRetePath);
            fileSalvataggio.write(daSalvare);
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
