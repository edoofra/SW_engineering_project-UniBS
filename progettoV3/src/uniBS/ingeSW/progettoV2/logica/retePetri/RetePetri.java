package uniBS.ingeSW.progettoV2.logica.retePetri;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.rete.ElementoSemplice;
import uniBS.ingeSW.progettoV2.logica.rete.Posto;
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
	private MarcaturaPN marcaturaAttuale;
    private ListaPesiFlussoPN listaPesi;
	ArrayList<ElemFlusso> possibiliTrans = new ArrayList<ElemFlusso>();
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
		if(!super.isEqual(toCompare)) return false;
		if(!this.getMarcatura().isEqual(toCompare.getMarcatura()) || !this.getListaPesi().isEqual(toCompare.getListaPesi())){
			return false;
		}			
		
		return true;
	}

	public ArrayList<ElemFlusso> getPossibiliTransizioni(){
		for(int i=0; i<marcatura.getListaPosti().size(); i++){
			Posto postoControllato = marcatura.getListaPosti().get(i);
			Integer marcaturaPostoControllato = marcatura.getMarcatura().get(i);
			for(int j=0; j<listaPesi.getListaElemFlusso().size(); j++){
				ElemFlusso elemControllato = listaPesi.getListaElemFlusso().get(j);
				if(elemControllato.getElem1().equalControl(postoControllato)){
					if(marcaturaPostoControllato>= listaPesi.getListaPesi().get(j)){
						possibiliTrans.add(elemControllato);
					}
				}
			}
		}
		return possibiliTrans;
	}

	public void aggiornaMarcaturaPerSimulazione(ElemFlusso elemScelto){
		ElementoSemplice daCercare = elemScelto.getElem2();
		ElementoSemplice primoPosto = elemScelto.getElem1();
		int indice = -1;
		boolean trovato = false;
		for(int j=0; j<listaPesi.getListaElemFlusso().size(); j++){
			if(listaPesi.getListaElemFlusso().get(j).controlloUguali(elemScelto)){
				indice = j;
				trovato = true;
				break;
			}
		}
		if(trovato){
			Integer pesoIniziale = listaPesi.getListaPesi().get(indice);
			marcatura.impostaNuovaMarcaturaConDifferenza(primoPosto.getName(), pesoIniziale);
		}

		for(int i=0; i<listaPesi.getListaElemFlusso().size(); i++){
			if(listaPesi.getListaElemFlusso().get(i).getElem1().equalControl(daCercare)){
				Integer peso = listaPesi.getListaPesi().get(i);
				String nomePosto = listaPesi.getListaElemFlusso().get(i).getElem2().getName();
				marcatura.impostaNuovaMarcaturaConSomma(nomePosto, peso);
			}
		}
		
	}


}
