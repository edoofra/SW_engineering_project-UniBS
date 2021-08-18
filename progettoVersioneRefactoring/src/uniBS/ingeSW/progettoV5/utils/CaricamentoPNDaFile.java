package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.rete.ReteInterface;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;

public class CaricamentoPNDaFile implements InterfaceTipoCaricamentoDaFile {

    @Override
    public ReteInterface caricaReteDaFile(String path) {
	RetePetri reteCaricata = LetturaFile.leggiRetePetriDaFile(path); //modificato per single resp
   	return reteCaricata;
    }

}
