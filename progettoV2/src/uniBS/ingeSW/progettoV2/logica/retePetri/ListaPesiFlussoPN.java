package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;

public class ListaPesiFlussoPN {
    
private HashMap<ElemFlusso, Integer> listaPesi;
    
    public ListaPesiFlussoPN(List<ElemFlusso> daAssociare) {
	for(int i=0; i<daAssociare.size(); i++) {
	    listaPesi.put(daAssociare.get(i), 1);
	}
    }
    
    public boolean impostaPeso(String nomeElemento1, String nomeElemento2, int nuovoPeso) {
    	
    	ElemFlusso[] elemFlussoArray = listaPesi.keySet().toArray(new ElemFlusso[0]);
    	ElemFlusso elemFlussoDaCambiare = Stream.of(elemFlussoArray)
    		.filter(n -> n.getElem1().getName().equalsIgnoreCase(nomeElemento1) &&
    			n.getElem2().getName().equalsIgnoreCase(nomeElemento2))
    		.findFirst()
    		.orElse(null);
    	if(elemFlussoDaCambiare != null) {
    	   listaPesi.replace(elemFlussoDaCambiare, nuovoPeso);
    	    return true;		
    	}
    	return false;	
     }

}
