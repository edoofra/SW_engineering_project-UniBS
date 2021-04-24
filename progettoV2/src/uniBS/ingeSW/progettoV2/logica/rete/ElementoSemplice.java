package uniBS.ingeSW.progettoV2.logica.rete;

/**
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * Classe per la gestione ad un livello più alto di posti e transizioni
 */
public class ElementoSemplice {
	
	private String nome;

	public String getName(){
		return this.nome;
	}

	public void setName(String name){
		this.nome = name;
	}

	//due elementi semplici con lo stesso nome sono considerati uguali
	public boolean equalControl(ElementoSemplice toCheck){
	    assert toCheck != null : "ElementoSemplice = null"; //precondizione 
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
