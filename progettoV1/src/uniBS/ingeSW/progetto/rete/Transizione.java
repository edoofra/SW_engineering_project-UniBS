package uniBS.ingeSW.progetto.rete;

public class Transizione extends ElementoSemplice {
	
	public Transizione(String nome) {
		setName(nome);
	}

	public String getProperties(){
		return this.getName();
	}
}
