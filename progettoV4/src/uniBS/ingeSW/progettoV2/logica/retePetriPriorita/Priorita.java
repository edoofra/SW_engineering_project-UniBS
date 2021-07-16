package uniBS.ingeSW.progettoV2.logica.retePetriPriorita;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV2.logica.rete.Transizione;

public class Priorita {

    ArrayList<Transizione> listaTransizioni;
    ArrayList<Integer> listaPriorita;

    public Priorita(ArrayList<Transizione> listaTransizioni){
        this.listaPriorita = new ArrayList<Integer>();
        this.listaTransizioni = listaTransizioni;
        for(int i=0; i<listaTransizioni.size(); i++){
            listaPriorita.add(1);
        }
    }
    
    
}
