package uniBS.ingeSW.progettoV2.logica.retePetri;

import uniBS.ingeSW.progettoV2.logica.rete.Rete;

public class RetePetri extends Rete {
    
    private MarcaturaPN marcatura;
    private ListaPesiFlussoPN listaPesi;
    
    public RetePetri(Rete daUsare) {
	this.insiemePosti = daUsare.getInsiemePosti();
	this.insiemeTransizioni = daUsare.getInsiemeTransizioni();
	this.relazioneFlusso = daUsare.getRelazioneFlusso();
	this.marcatura = new MarcaturaPN(daUsare.getInsiemePosti());
	this.listaPesi = new ListaPesiFlussoPN(daUsare.getRelazioneFlusso());
    }

}
