package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.List;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;

public class ListaPesiFlussoPN {
    
private HashMap<ElemFlusso, Integer> listaPesi;
    
    public ListaPesiFlussoPN(List<ElemFlusso> daAssociare) {
	for(int i=0; i<daAssociare.size(); i++) {
	    listaPesi.put(daAssociare.get(i), 1);
	}
    }

}
