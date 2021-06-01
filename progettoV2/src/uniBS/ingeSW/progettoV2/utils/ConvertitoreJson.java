package uniBS.ingeSW.progettoV2.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.*;

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

     /**
     * Metodo che converte un oggetto GestoreRetiPetri in una stringa formattata in JSON.
     * @param oggetto oggetto da convertire.
     * @return stringa formattata in JSON contenente la descrizione di tutte le reti contenute nell'oggetto GestoreRetiPetri.
     */
    public static String daOggettoAJson(GestoreRetiPetri oggetto){
        assert oggetto != null: "oggetto = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

     /**
     * Metodo che converte una stringa formattata in JSON in un oggetto GestoreRetiPetri.
     * @param json stringa da convertire.
     * @return oggetto GestoreRetiPetri creato a partire da stringa JSON.
     */
    public static GestoreRetiPetri daJsonAOggettoPetriHashSet(String json){
        assert json != null: "json = null"; //precondizione
        Gson convertitore = new Gson();
//        try {
//	    GestoreRetiPetri message= new ObjectMapper().readValue(json, GestoreRetiPetri.class);
//	} catch (JsonMappingException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	} catch (JsonProcessingException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
        return convertitore.fromJson(json, GestoreRetiPetri.class);
    }
}
