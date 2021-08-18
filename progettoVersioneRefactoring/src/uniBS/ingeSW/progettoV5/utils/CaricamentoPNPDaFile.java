package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.rete.ReteInterface;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;

public class CaricamentoPNPDaFile implements InterfaceTipoCaricamentoDaFile {

    @Override
    public ReteInterface caricaReteDaFile(String path) {
	RetePetriPriorita reteCaricata = LetturaFile.leggiRetePetriPrioritaDaFile(path); //modificato per single resp
   	return reteCaricata;
    }

}
