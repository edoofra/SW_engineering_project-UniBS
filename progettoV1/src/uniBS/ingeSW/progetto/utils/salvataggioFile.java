package uniBS.ingeSW.progetto.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class salvataggioFile {
    
    /**
     *
     */
    private static final String PATH = "ListaRetiConfiguratore.json";

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
