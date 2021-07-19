package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.ArrayList;
import java.util.List;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;

/** 
 * Classe che identifica la <em> lista della marcatura </em> legata ad ogni posto 
 * contenuto all'interno di una rete di Petri.
 * @author Lorenzo Bargnani
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @version 1.0
*/
public class MarcaturaPN {
    
    private ArrayList<Posto> listaPosti;
	private ArrayList<Integer> marcatura;
    
    public MarcaturaPN(List<Posto> daMarcare) {
		listaPosti = new ArrayList<Posto>();
		marcatura = new ArrayList<Integer>();
		for(int i=0; i<daMarcare.size(); i++) {
			listaPosti.add(daMarcare.get(i));
			marcatura.add(0);
		}
    }

	public ArrayList<Integer> getMarcatura() {
		return this.marcatura;
	}

	public ArrayList<Posto> getListaPosti(){
		return this.listaPosti;
	}
    
	/** 
	* Metodo che imposta la marcatura di un posto (il valore e' preimpostato a 0 alla creazione della rete di petri) 
	* @param nomePosto posto a cui va cambiata la marcatura
	* @param nuovaMarcatura Marcatura da impostare
	* @return boolean che indica se la modifica e' andata a buon fine
	*/
    public boolean impostaNuovaMarcatura(String nomePosto, int nuovaMarcatura) {
        int posizione = -1;
		boolean trovato = false;
		for(int i=0; i<listaPosti.size(); i++) {
			if(listaPosti.get(i).getName().equalsIgnoreCase(nomePosto)){
				posizione = i;
				trovato = true;
				break;
			}
		}
		if(trovato){
			marcatura.set(posizione, nuovaMarcatura);
			return true;
		}
		else return false;
	}
	
	 /** Metodo che controlla se due marcature sono uguali
	 * @param toCompare Marcatura da confrontare con la Marcatura su cui e' stato chiamato il metodo
	 * @return boolean che ritorna true se la Marcatura e' uguale, false se la Marcatura e' diversa.  
	 */
	public boolean isEqual(MarcaturaPN toCompare){
		ArrayList<Integer> toCompareList =toCompare.getMarcatura();
		if(toCompareList.size() != marcatura.size()) return false;
		for(int i=0;i<toCompareList.size();i++){
			if(toCompareList.get(i) != marcatura.get(i)) return false;
		}
		return true;
	}
}

	