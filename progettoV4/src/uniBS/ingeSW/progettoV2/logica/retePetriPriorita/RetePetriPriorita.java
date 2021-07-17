package uniBS.ingeSW.progettoV2.logica.retePetriPriorita;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV2.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV2.logica.retePetri.RetePetri;

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
