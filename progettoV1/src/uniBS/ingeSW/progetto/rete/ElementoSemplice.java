package uniBS.ingeSW.progetto.rete;

/**
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * Classe per la gestione ad un livello più alto di posti e transizioni
 */
public class ElementoSemplice {
	
	/**
	 * nome dell'elemento della rete
	 */
	private String nome;

	public String getName(){
		return this.nome;
	}

	public void setName(String name){
		this.nome = name;
	}

	public boolean equalControl(ElementoSemplice toCheck){
		return (this.nome.equalsIgnoreCase(toCheck.getName()));
	}
	
	/**
	 * Metodo per ottenere le proprietà principali di ogni elemento 
	 * Viene fatto override in ogni elemento che eredita da questa classe 
	 * @return nome dell'elemento (attributo base di ogni elemento della rete)
	 */
	public String getProperties() {
		return this.nome;
	}
	
}
