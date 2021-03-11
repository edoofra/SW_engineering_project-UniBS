package uniBS.ingeSW.progetto.utils;
import com.google.gson.Gson;


import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;
import uniBS.ingeSW.progetto.rete.ElementoSemplice;


public class ConvertitoreJson {
    
    public static String daOggettoAJson(Object oggetto){
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

    public static ElementoSemplice daJsonAOggetto(String json){
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, ElementoSemplice.class);
    }

    public static GestoreReti daJsonAOggettoHashSet(String json){
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, GestoreReti.class);
    }

}
