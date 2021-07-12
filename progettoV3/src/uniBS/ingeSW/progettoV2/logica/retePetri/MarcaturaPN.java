package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.ArrayList;
import java.util.List;

import uniBS.ingeSW.progettoV2.logica.rete.Posto;

public class MarcaturaPN {
    
	//linked hashMap dovrebbe mantenere ordine
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

	public boolean impostaNuovaMarcaturaConSomma(String nomePosto, int nuovaMarcatura) {
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
			int vecchiaMarcatura = marcatura.get(posizione);
			marcatura.set(posizione, vecchiaMarcatura + nuovaMarcatura);
			return true;
		}
		else return false;
	}
    
	/** 
	* Metodo che imposta la marcatura di un posto (il valore è preimpostato a 0 alla creazione della rete di petri) 
	* @param nomePosto posto a cui va cambiata la marcatura, nuovaMarcatura Marcatura da impostare
	* @return boolean che indica se la modifica è andata a buon fine
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
	 * @param toCompare Marcatura da confrontare con la Marcatura su cui è stato chiamato il metodo
	 * @return boolean che ritorna true se la Marcatura è uguale, false se la Marcatura è diversa.  
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

	