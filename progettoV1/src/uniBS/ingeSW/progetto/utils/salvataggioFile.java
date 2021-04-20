package uniBS.ingeSW.progetto.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class salvataggioFile {
    
    public static void salvaGestoreReti(String daSalvare){
        try {
            FileWriter fileSalvataggio = new FileWriter("ListaRetiConfiguratore.json");
            fileSalvataggio.write(daSalvare);
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leggiGestoreRetiDaFile(){
        try {
            String gestoreRetiJSON = new String(Files.readAllBytes(Paths.get("ListaRetiConfiguratore.json")));
            return gestoreRetiJSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
