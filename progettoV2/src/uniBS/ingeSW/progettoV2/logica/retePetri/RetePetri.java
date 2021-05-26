package uniBS.ingeSW.progettoV2.logica.retePetri;

import uniBS.ingeSW.progettoV2.logica.rete.Rete;

public class RetePetri extends Rete {
    
    private MarcaturaPN marcatura;
    private ListaPesiFlussoPN listaPesi;
	//in caso aggiungere variabile che tiene in memoria la rete da cui deriva
    
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

	
	public boolean controlloRetePetriUguale(RetePetri toCompare){
		if(super.isEqual(toCompare)){
			if(this.getMarcatura().isEqual(toCompare.getMarcatura()) && this.getListaPesi().isEqual(toCompare.getListaPesi())){
				return true;
			}			
		}
		return false;
	}

}
