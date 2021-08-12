package uniBS.ingeSW.progettoV5.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;
import uniBS.ingeSW.progettoV5.view.InterazioneUtente;

public class LetturaFile {

    public static final String RETI_PETRI_PRIORITA = "RETI_PETRI_PRIORITA";

    public static final String RETI_PETRI = "RETI_PETRI";

    public static final String RETI = "RETI";     
    
    public static ReteSemplice leggiReteDaFile(String path) {
	 File fileRete = new File(path);
	 if (fileRete.isFile()) {
	     String reteJson = leggiGestoreRetiDaFile(fileRete.getPath());
	     ReteSemplice reteCaricata = ConvertitoreJson.daJsonAOggettoHashSet(reteJson);
	     return reteCaricata;
	 }
	 else {
	     InterazioneUtente.printErrorNoFile();
	     return null;	     
	 }
   }
   
   public static RetePetri leggiRetePetriDaFile(String path) {
	 File fileRete = new File(path);
	 if (fileRete.isFile()) {
	     String reteJson = leggiGestoreRetiDaFile(fileRete.getPath());
	     RetePetri reteCaricata = ConvertitoreJson.daJsonARetePetri(reteJson);
	     return reteCaricata;
	 }
	 else {
	     InterazioneUtente.printErrorNoFile();
	     return null;	     
	 }
   }
   
   public static RetePetriPriorita leggiRetePetriPrioritaDaFile(String path) {
	 File fileRete = new File(path);
	 if (fileRete.isFile()) {
	     String reteJson = leggiGestoreRetiDaFile(fileRete.getPath());
	     RetePetriPriorita reteCaricata = ConvertitoreJson.daJsonARetePetriPriorita(reteJson);
	     return reteCaricata;
	 }
	 else {
	     InterazioneUtente.printErrorNoFile();
	     return null;	     
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
   
   public static GestoreReti recuperoOCreazione(){
       GestoreReti retiSalvate = new GestoreReti();
       File folder = new File(RETI);

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
               String reteJson = leggiGestoreRetiDaFile(file.getPath());
               ReteSemplice reteCaricata = ConvertitoreJson.daJsonAOggettoHashSet(reteJson);
               try {
                   retiSalvate.addRete(file.getName(), reteCaricata);
               } catch (giaPresenteException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
			}
		}
       return retiSalvate;
   }

   public static GestoreRetiPetri recuperoOCreazionePetri(){
       GestoreRetiPetri retiSalvate = new GestoreRetiPetri();
       File folder = new File(RETI_PETRI);

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
               String retePetriJson = leggiGestoreRetiDaFile(file.getPath());
               RetePetri reteCaricata = ConvertitoreJson.daJsonARetePetri(retePetriJson);
               try {
                   retiSalvate.addRete(file.getName(), reteCaricata);
               } catch (giaPresenteException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
			}
		}
       return retiSalvate;
   }

   public static GestoreRetiPetriPriorita recuperoOCreazionePetriPriorita(){
       GestoreRetiPetriPriorita retiSalvate = new GestoreRetiPetriPriorita();
       File folder = new File(RETI_PETRI_PRIORITA);

		if (!folder.exists())
			folder.mkdirs();

		for (File file : folder.listFiles()) {
			if (file.isFile()) {
               String retePetriJson = leggiGestoreRetiDaFile(file.getPath());
               RetePetriPriorita reteCaricata = ConvertitoreJson.daJsonARetePetriPriorita(retePetriJson); //
               try {
                   retiSalvate.addRete(file.getName(), reteCaricata);
               } catch (giaPresenteException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
			}
		}
       return retiSalvate;
   }

}
