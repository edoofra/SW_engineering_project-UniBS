package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;

public class ListaPesiFlussoPN {
    
private LinkedHashMap<ElemFlusso, Integer> listaPesi;
    
    public ListaPesiFlussoPN(List<ElemFlusso> daAssociare) {
		listaPesi = new LinkedHashMap<ElemFlusso, Integer>();
		for(int i=0; i<daAssociare.size(); i++) {
			listaPesi.put(daAssociare.get(i), 1);
		}
    }

	public HashMap<ElemFlusso, Integer> getListaPesi() {
		return listaPesi;
	}
    /**
	* Metodo che imposta il peso di un elemento di flusso (il valore è preimpostato ad 1 alla creazione della rete di petri) 
	* @param nomeElemento1 primo elemento, nomeElemento2 secondo elemento, nuovoPeso Peso da impostare
	* @return boolean che indica se la modifica è andata a buon fine
	*/
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

	 /** Metodo che controlla se due listePesi sono uguali
	 * @param toCompare listaPesiFlussoPN da confrontare con la listaPesi su cui è stato chiamato il metodo
	 * @return boolean che ritorna true se la lista è uguale, false se la ListaPesiFlussoPN è diversa.  
	 */
	 public boolean isEqual(ListaPesiFlussoPN toCompare){
		ElemFlusso[] arrayFlusso1 = this.getListaPesi().keySet().toArray(new ElemFlusso[0]);
		ElemFlusso[] arrayFlusso2 = toCompare.getListaPesi().keySet().toArray(new ElemFlusso[0]);

		boolean uguale = false;
		for(int i=0; i<arrayFlusso1.length; i++){
			cicloInterno:
			for(int j=0; j<arrayFlusso2.length; j++){
				if(arrayFlusso1[i].controlloUguali(arrayFlusso2[j])){
					if(this.getListaPesi().get(arrayFlusso1[i]) == toCompare.getListaPesi().get(arrayFlusso2[j])){
						uguale = true;
						break cicloInterno;
					}
					uguale = false;
				}
			}
			if(!uguale) return false;
		}
		return true;
	 }

	 
}
