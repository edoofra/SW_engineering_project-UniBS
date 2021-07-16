package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.ArrayList;
import java.util.List;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;

/** 
 * Classe che identifica la lista dei pesi legati ad ogni elemento di flusso 
 * contenuto all'interno di una rete di Petri.
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * @version 1.0
*/
public class ListaPesiFlussoPN {
    
	private ArrayList<ElemFlusso> listaElementiFlusso;
	private ArrayList<Integer> listaPesi;
    
    public ListaPesiFlussoPN(List<ElemFlusso> daAssociare) {
		listaPesi = new ArrayList<Integer>();
		listaElementiFlusso = new ArrayList<ElemFlusso>();
		for(int i=0; i<daAssociare.size(); i++) {
			listaElementiFlusso.add(daAssociare.get(i));
			listaPesi.add(1);
		}
    }

	public ArrayList<Integer> getListaPesi() {
		return this.listaPesi;
	}

	public ArrayList<ElemFlusso> getListaElemFlusso(){
		return this.listaElementiFlusso;
	}


     /**
	* Metodo che imposta il peso di un elemento di flusso (il valore è preimpostato ad 1 alla creazione della rete di petri) 
	* @param nomeElemento1 primo elemento
	* @param nomeElemento2 secondo elemento
	* @param nuovoPeso Peso da impostare
	* @return boolean che indica se la modifica è andata a buon fine
	*/
    public boolean impostaPeso(String nomeElemento1, String nomeElemento2, int nuovoPeso) {
    	int posizione = -1;
		boolean trovato = false;
		for(int i=0; i<listaElementiFlusso.size(); i++){
			if(listaElementiFlusso.get(i).getElem1().getName().equalsIgnoreCase(nomeElemento1) 
				&& listaElementiFlusso.get(i).getElem2().getName().equalsIgnoreCase(nomeElemento2)){
					posizione = i;
					trovato = true;
				}
		}
    	if(trovato) {
    	   listaPesi.set(posizione, nuovoPeso);
    	    return true;		
    	}
    	return false;	
     }

	 /** Metodo che controlla se due listePesi sono uguali
	 * @param toCompare listaPesiFlussoPN da confrontare con la listaPesi su cui è stato chiamato il metodo
	 * @return boolean che ritorna true se la lista è uguale, false se la ListaPesiFlussoPN è diversa.  
	 */
	 public boolean isEqual(ListaPesiFlussoPN toCompare){
		ArrayList<Integer> toCompareList = toCompare.getListaPesi();
		if(toCompareList.size() != listaPesi.size()) return false;
		for(int i=0; i<toCompareList.size(); i++){
			if(toCompareList.get(i) != listaPesi.get(i)) return false;
		}
		return true;
	 }

	 
}
