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

    public ArrayList<Integer> getListaPriorita(){
        return this.listaPriorita;
    }

    /** 
	* Metodo che imposta la priorità di una transizione (il valore è preimpostato a 0 alla creazione della rete di petri) 
	* @param nomePosto transizione a cui va cambiata la priorita, nuovaPriorita priorità da impostare
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
			listaPriorita.set(posizione, Math.min(1,nuovaPriorita));
			return true;
		}
		else return false;
	}

     /** Metodo che controlla se due liste di priorità sono uguali
	 * @param toCompare lista priorità da confrontare con la lista priorità su cui è stato chiamato il metodo
	 * @return boolean che ritorna true se la lista priorità è uguale 
	 */
	public boolean isEqual(Priorita toCompare){
		ArrayList<Integer> toCompareList =toCompare.getListaPriorita();
		if(toCompareList.size() != listaPriorita.size()) return false;
		for(int i=0;i<toCompareList.size();i++){
			if(toCompareList.get(i) != listaPriorita.get(i)) return false;
		}
		return true;
	}


    
    
}
