package uniBS.ingeSW.progetto.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;

public class salvataggioFile {
    
    public static void salvaGestoreReti(GestoreReti daSalvare){
        try {
            FileWriter fileSalvataggio = new FileWriter("ListaRetiConfiguratore.json");
            fileSalvataggio.write(ConvertitoreJson.daOggettoAJson(daSalvare));
            fileSalvataggio.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
