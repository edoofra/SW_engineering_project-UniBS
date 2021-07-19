package uniBS.ingeSW.progettoV4.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe per il salvataggio e il recuperso da file di stringhe.
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @version 1.0 
 */
public class salvataggioFile {
    
    
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

    public static void salvaRete(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File("RETI");
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

    public static void salvaRetePetri(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File("RETI_PETRI");
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

    public static void salvaRetePetriPriorita(String daSalvare, String nomeRetePath){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        File folder = new File("RETI_PETRI_PRIORITA");
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
     * Metodo per la lettura da file di una stringa.
     * @return stringa letta da file.
     */
    public static String leggiGestoreRetiDaFile(String path){
        try {
            String gestoreRetiJSON = new String(Files.readAllBytes(Paths.get(path)));
            return gestoreRetiJSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
