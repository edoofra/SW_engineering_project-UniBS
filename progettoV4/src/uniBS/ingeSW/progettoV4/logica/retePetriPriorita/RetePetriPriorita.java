package uniBS.ingeSW.progettoV4.logica.retePetriPriorita;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV4.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV4.logica.retePetri.RetePetri;

/**
 * Classe per l'implementazione di una rete di Petri con priorità che estende una rete di Petri.
 * Una rete di petri è composta da <ul> <li> <em> un insieme di posti </em> </li> <li> <em> un insieme di transizioni </em> </li> <li> <em> un insieme di elementi di flusso </em> </li> </ul> una marcatura </em> </li> </ul> una lista pesi flusso </em> </li> </ul>
 * Una rete di Petri con periorità ha un <em> livello di priorità </em> assegnato ad ogni transizione.
 * Una rete deve essere connessa per essere tale, ovvero ogni elemento di rete deve essere raggiunto da almeno un elemento di flusso.
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @version 1.0
 */
public class RetePetriPriorita extends RetePetri{

    private Priorita listaPriorita;

    public RetePetriPriorita(RetePetri retePN){
        this.insiemePosti = retePN.getInsiemePosti();
        this.insiemeTransizioni = retePN.getInsiemeTransizioni();
        this.relazioneFlusso = retePN.getRelazioneFlusso();
        this.marcatura = retePN.getMarcatura();
        this.listaPesi = retePN.getListaPesi();
        this.listaPriorita = new Priorita(retePN.getInsiemeTransizioni());
    }

    public Priorita getPriorita(){
        return this.listaPriorita;
    }

    /**
	 * Metodo per controllare se due reti di Petri con priorità sono o meno uguali
	 * <em> Due reti di petri (derivate dalla stessa rete) si considerano uguali se la loro marcatura e la loro lista pesi è uguale </em>
	 * Due reti di Petri con Priorità devono inoltre avere le stesse priorità assegnate alle transizioni 
     * per essere considerate uguali.
     * Si assume che le reti da cui sono derivate le due reti di petri siano uguali, altrimenti le reti di petri sono diverse a prescindere
	 * dalla marcatura e dalla lista pesi
	 * @param toCompare rete da comparare
	 * @return boolean che indica se le due reti sono uguali o meno 
	 */
    public boolean controlloRetePetriPrioritaUguale(RetePetriPriorita toCompare){
		if(!super.isEqual(toCompare)) return false;
		if(!this.getPriorita().isEqual(toCompare.getPriorita())){
			return false;
		}			
		
		return true;
	}

    public ArrayList<ElemFlusso> getTransizioniPrioritaMaggiore(ArrayList<ElemFlusso> transizioniPossibili){
        return listaPriorita.getTransizioniPrioritaMaggiore(transizioniPossibili);
    }    
    
}
