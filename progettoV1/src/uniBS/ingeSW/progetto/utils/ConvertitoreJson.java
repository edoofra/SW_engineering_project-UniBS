package uniBS.ingeSW.progetto.utils;

import com.google.gson.Gson;
import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;


public class ConvertitoreJson {
    
    public static String daOggettoAJson(GestoreReti oggetto){
        assert oggetto != null: "oggetto = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

    public static GestoreReti daJsonAOggettoHashSet(String json){
        assert json != null: "json = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, GestoreReti.class);
    }
}
