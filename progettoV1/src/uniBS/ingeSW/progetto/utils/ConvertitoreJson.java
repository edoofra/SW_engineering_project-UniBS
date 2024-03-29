package uniBS.ingeSW.progetto.utils;

import com.google.gson.Gson;
import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;

/** 
 * Classe per la conversione di un oggetto GestoreReti da/in formato JSON.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
public class ConvertitoreJson {
    
    /**
     * Metodo che converte un oggetto GestoreReti in una stringa formattata in JSON.
     * @param oggetto oggetto da convertire.
     * @return stringa formattata in JSON contenente la descrizione di tutte le reti contenute nell'oggetto GestoreReti.
     */
    public static String daOggettoAJson(GestoreReti oggetto){
        assert oggetto != null: "oggetto = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

    /**
     * Metodo che converte una stringa formattata in JSON in un oggetto GestoreReti.
     * @param json stringa da convertire.
     * @return oggetto GestoreReti creato a partire da stringa JSON.
     */
    public static GestoreReti daJsonAOggettoHashSet(String json){
        assert json != null: "json = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, GestoreReti.class);
    }
}
