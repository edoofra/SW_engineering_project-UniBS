package uniBS.ingeSW.progetto.utils;

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
    
    private static final String PATH = "ListaRetiConfiguratore.json";

    /**
     * Metodo per il salvataggio di una stringa su file.
     * @param daSalvare Stringa da salvare.
     */
    public static void salvaGestoreReti(String daSalvare){
        assert daSalvare != null : "daSalvare = null"; //precondizione
        try {
            FileWriter fileSalvataggio = new FileWriter(PATH);
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
    public static String leggiGestoreRetiDaFile(){
        try {
            String gestoreRetiJSON = new String(Files.readAllBytes(Paths.get(PATH)));
            return gestoreRetiJSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
