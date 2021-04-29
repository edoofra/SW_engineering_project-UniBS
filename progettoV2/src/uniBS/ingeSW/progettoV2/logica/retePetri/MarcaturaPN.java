package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;

public class MarcaturaPN {
    
    private HashMap<Posto, Integer> marcatura;
    
    public MarcaturaPN(List<Posto> daMarcare) {
	for(int i=0; i<daMarcare.size(); i++) {
	    marcatura.put(daMarcare.get(i), 0);
	}
    }
    
    
    public boolean impostaMarcatura(String nomePosto, int nuovaMarcatura) {
	
	Posto[] postiArray = marcatura.keySet().toArray(new Posto[0]);
	Posto postoDaCambiare = Stream.of(postiArray)
		.filter(n -> n.getName().equalsIgnoreCase(nomePosto))
		.findFirst()
		.orElse(null);
	if(postoDaCambiare != null) {
	    marcatura.replace(postoDaCambiare, nuovaMarcatura);
	    return true;		
	}
	return false;	
    }

}
