package uniBS.ingeSW.progettoV4.logica.retePetriPriorita;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV4.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV4.logica.rete.Transizione;

/** 
 * Classe che identifica la lista delle priorità legate ad ogni transizione
 * contenuta all'interno di una rete di Petri.
 * @author Lorenzo Bargnani
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @version 1.0 
*/
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

    public ArrayList<Integer> getListaPriorita(){
        return this.listaPriorita;
    }

    public ArrayList<Transizione> getListaTransizioni(){
        return this.listaTransizioni;
    }

    /** 
	* Metodo che imposta la priorità di una transizione (il valore è preimpostato a 0 alla creazione della rete di petri) 
	* @param nomePosto transizione a cui va cambiata la priorita
	* @param nuovaPriorita priorità da impostare
	* @return boolean che indica se la modifica è andata a buon fine
	*/
    public boolean impostaNuovaPriorita(String nomeTrans, int nuovaPriorita) {
        int posizione = -1;
		boolean trovato = false;
		for(int i=0; i<listaTransizioni.size(); i++) {
			if(listaTransizioni.get(i).getName().equalsIgnoreCase(nomeTrans)){
				posizione = i;
				trovato = true;
				break;
			}
		}
		if(trovato){
			listaPriorita.set(posizione, Math.max(1,nuovaPriorita));
			return true;
		}
		else return false;
	}

     /** Metodo che controlla se due liste di priorità sono uguali
	 * @param toCompare lista priorità da confrontare con la lista priorità su cui è stato chiamato il metodo
	 * @return boolean che ritorna true se la lista priorità è uguale 
	 */
	public boolean isEqualPriorita(Priorita toCompare){
		ArrayList<Integer> toCompareList =toCompare.getListaPriorita();
		if(toCompareList.size() != listaPriorita.size()) return false;
		for(int i=0;i<toCompareList.size();i++){
			if(toCompareList.get(i) != listaPriorita.get(i)) return false;
		}
		return true;
	}

	/**
	 * Metodo che restituisce la lista delle transizioni possibili a priorità maggiore.
	 * @param transizioniPossibili lista di tutte le transizioni possibili.
	 * @return lista di tutte le transizioni possibili a priorità maggiore.
	 */
	public ArrayList<ElemFlusso> getTransizioniPrioritaMaggiore(ArrayList<ElemFlusso> transizioniPossibili){
		ArrayList<Transizione> listaTransizioniMaxPriorita = new ArrayList<Transizione>();
		int max = Integer.MIN_VALUE;
		for(int i=0; i<listaPriorita.size(); i++){
			if(listaPriorita.get(i) > max){
				max = listaPriorita.get(i);
			}
		}
		for(int j=0; j<listaTransizioni.size(); j++){
			if(listaPriorita.get(j) == max) listaTransizioniMaxPriorita.add(listaTransizioni.get(j));
		}

		for(int k=0; k<transizioniPossibili.size(); k++){
			boolean massimo = false;
			cicloInterno :
			for(int z=0; z<listaTransizioniMaxPriorita.size(); z++){
				if(transizioniPossibili.get(k).getElem2().getName().equalsIgnoreCase(listaTransizioniMaxPriorita.get(z).getName())){
					massimo = true;
					break cicloInterno;
				}
			}
			if(!massimo) transizioniPossibili.remove(k);
		}
		return transizioniPossibili;
	}


    
    
}
