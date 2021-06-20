package uniBS.ingeSW.progettoV2.logica.retePetri;

import uniBS.ingeSW.progettoV2.logica.rete.Rete;

/**
 * Classe per l'implementazione di una rete di petri che estende una rete.
 * Una rete di petri è composta da <ul> <li> <em> un insieme di posti </em> </li> <li> <em> un insieme di transizioni </em> </li> <li> <em> un insieme di elementi di flusso </em> </li> </ul> una marcatura </em> </li> </ul> una lista pesi flusso </em> </li> </ul>
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da almeno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 2.0 - attuata separazione modello-vista. Cambiati getters e setters.
 */

public class RetePetri extends Rete {
    
    private MarcaturaPN marcatura;
    private ListaPesiFlussoPN listaPesi;
	//in caso aggiungere variabile che tiene in memoria la rete da cui deriva
    
	/**
	 * Metodo per la creazione di una rete.
	 * Vengono istanziati i 2 insiemi (marcatura e listaPesi), aggiunti i posti, le transizioni e la relazione di flusso della rete
	 * da cui viene implementata la rete di petri
	 */
    public RetePetri(Rete daUsare) {
		this.insiemePosti = daUsare.getInsiemePosti();
		this.insiemeTransizioni = daUsare.getInsiemeTransizioni();
		this.relazioneFlusso = daUsare.getRelazioneFlusso();
		this.marcatura = new MarcaturaPN(daUsare.getInsiemePosti());
		this.listaPesi = new ListaPesiFlussoPN(daUsare.getRelazioneFlusso());
    }

	public MarcaturaPN getMarcatura(){
		return this.marcatura;
	}

	public ListaPesiFlussoPN getListaPesi(){
		return this.listaPesi;
	}


	/**
	 * Metodo per controllare se due reti sono o meno uguali
	 * <em> Due reti di petri (derivate dalla stessa rete) si considerano uguali se la loro marcatura e la loro lista pesi è uguale </em>
	 * Si assume che le reti da cui sono derivate le due reti di petri siano uguali, altrimenti le reti di petri sono diverse a prescindere
	 * dalla marcatura e dalla lista pesi
	 * @param toCompare rete da comparare
	 * @return boolean che indica se le due reti sono uguali o meno 
	 */
	public boolean controlloRetePetriUguale(RetePetri toCompare){
		if(super.isEqual(toCompare)){
			if(this.getMarcatura().isEqual(toCompare.getMarcatura()) && this.getListaPesi().isEqual(toCompare.getListaPesi())){
				return true;
			}			
		}
		return false;
	}
}
