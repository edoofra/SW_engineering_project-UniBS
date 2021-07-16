package uniBS.ingeSW.progettoV2.logica.retePetriPriorita;

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
    
}
