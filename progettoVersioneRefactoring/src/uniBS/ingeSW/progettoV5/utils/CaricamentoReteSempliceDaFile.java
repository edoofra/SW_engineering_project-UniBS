package uniBS.ingeSW.progettoV5.utils;

import uniBS.ingeSW.progettoV5.logica.rete.ReteInterface;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;

public class CaricamentoReteSempliceDaFile implements InterfaceTipoCaricamentoDaFile {

    @Override
    public ReteInterface caricaReteDaFile(String path) {
	ReteSemplice reteCaricata = LetturaFile.leggiReteDaFile(path); //modificato per single resp
	return reteCaricata;
    }

}
