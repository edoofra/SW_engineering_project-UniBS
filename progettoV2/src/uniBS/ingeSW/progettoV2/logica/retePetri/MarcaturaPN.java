package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;

public class MarcaturaPN {
    
	//linked hashMap dovrebbe mantenere ordine
    private LinkedHashMap<Posto, Integer> marcatura;
    
    public MarcaturaPN(List<Posto> daMarcare) {
		marcatura = new LinkedHashMap<Posto, Integer>();
		for(int i=0; i<daMarcare.size(); i++) {
			marcatura.put(daMarcare.get(i), 0);
		}
    }

	public HashMap<Posto, Integer> getMarcatura() {
		return this.marcatura;
	}
    
	// cambiuare e invece di far restituire un boolean lanciare eccezione personalizzata
    public boolean impostaMarcatura(String nomePosto, int nuovaMarcatura) {
	
		Posto[] postiArray = marcatura.keySet().toArray(new Posto[0]);
		Posto postoDaCambiare = Stream.of(postiArray)
			.filter(n -> n.getName().equalsIgnoreCase(nomePosto))
			.findFirst()
			.orElse(null);
		//if null allora eccezione
		if(postoDaCambiare != null) {
			marcatura.replace(postoDaCambiare, nuovaMarcatura);
			return true;		
		}
		return false;	
    }

	public boolean isEqual(MarcaturaPN toCompare){
		
		//per snellirlo devo assumere che la lista sia ordinata
		//ma devo prima ordinarla da un'altra parte 
		
		//per ordinare la hashmap in base alle chiavi: Object[] keys = map.keySet().toArray(); mi d� l'array delle chiavi
		//Arrays.sort(keys); mette in ordine l'array delle chiavi
		//poi itero la hashmap per ogni chiave e recupero il valore dei posti
		Posto[] arrayPosti1 = this.getMarcatura().keySet().toArray(new Posto[0]);
		Posto[] arrayPosti2 = toCompare.getMarcatura().keySet().toArray(new Posto[0]);

		boolean uguale = false;
		for(int i=0; i<arrayPosti1.length; i++){
			cicloInterno:
			for(int j=0; j<arrayPosti2.length; j++){
				if(arrayPosti1[i] == arrayPosti2[j]){
					if(this.getMarcatura().get(arrayPosti1[i]) == toCompare.getMarcatura().get(arrayPosti2[j])){
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
