package uniBS.ingeSW.progetto.rete;

public class Transizione extends ElementoSemplice {
	
	public Transizione(String nome) {
		this.nome = "T:" + nome;
	}

	public String getProperties(){
		return this.nome;
	}
}
