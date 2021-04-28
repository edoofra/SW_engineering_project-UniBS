package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.List;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;

public class MarcaturaPN {
    
    private HashMap<Posto, Integer> marcatura;
    
    public MarcaturaPN(List<Posto> daMarcare) {
	for(int i=0; i<daMarcare.size(); i++) {
	    marcatura.put(daMarcare.get(i), 0);
	}
    }
    
    

}
