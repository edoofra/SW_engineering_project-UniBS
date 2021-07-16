package uniBS.ingeSW.progettoV2.utils;
import com.google.gson.Gson;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.*;
import uniBS.ingeSW.progettoV2.logica.rete.Rete;
import uniBS.ingeSW.progettoV2.logica.retePetri.RetePetri;

/** 
 * Classe per la conversione di un oggetto GestoreReti da/in formato JSON.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
 */
public class ConvertitoreJson {
    
    /**
     * Metodo che converte un oggetto Rete in una stringa formattata in JSON.
     * @param oggetto oggetto da convertire.
     * @return stringa formattata in JSON contenente la descrizione di tutte le reti contenute nell'oggetto GestoreReti.
     */
    public static String daOggettoAJson(Rete oggetto){
        assert oggetto != null: "oggetto = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

    public static String daOggettoAJson(RetePetri oggetto){
        assert oggetto != null: "oggetto = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.toJson(oggetto);
    }

    /**
     * Metodo che converte una stringa formattata in JSON in un oggetto GestoreReti.
     * @param json stringa da convertire.
     * @return oggetto GestoreReti creato a partire da stringa JSON.
     */
    public static Rete daJsonAOggettoHashSet(String json){
        assert json != null: "json = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, Rete.class);
    }

    public static RetePetri daJsonARetePetri(String json){
        assert json != null: "json = null"; //precondizione
        Gson convertitore = new Gson();
        return convertitore.fromJson(json, RetePetri.class);
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
        return convertitore.fromJson(json, GestoreRetiPetri.class);
    }
}
