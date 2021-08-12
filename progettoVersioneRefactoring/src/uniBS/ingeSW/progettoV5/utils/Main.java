package uniBS.ingeSW.progettoV5.utils;

import java.io.File;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreReti;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetri;
import uniBS.ingeSW.progettoV5.logica.gestioneReti.GestoreRetiPetriPriorita;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class Main {                                                                            
                                                                            
    public static void main(String[] args) {  
        GestoreReti retiSalvate = LetturaFile.recuperoOCreazione();
        GestoreRetiPetri retiPNSalvate = LetturaFile.recuperoOCreazionePetri();
        GestoreRetiPetriPriorita retiPNPSalvate = LetturaFile.recuperoOCreazionePetriPriorita();
        ControllerMVC controller = new ControllerMVC();
        controller.startMenuUI(retiSalvate, retiPNSalvate, retiPNPSalvate);
    }

    
}

