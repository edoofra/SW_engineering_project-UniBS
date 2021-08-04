package uniBS.ingeSW.progettoV5.logica.rete;

/**
 * Classe per la gestione ad un livello piu' alto di posti e transizioni. 
 * Un elemento semplice e' un' <em> astrazione </em> di un posto o di una transizione e 
 * descrive un elemento di una rete caratterizzato da un nome.
 * @author Lorenzo Bargnani
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @version 1.0
 */
public class ElementoSemplice {
	
	private String nome;

	public String getName(){
		return this.nome;
	}

	public void setName(String name){
		this.nome = name;
	}

	/**
	 * Metodo per controllare se due elementi semplici coincidono.
	 * Due elementi semplici <em> con lo stesso nome </em> sono considerati uguali.
	 * @return boolean che indica se i loro nomi sono uguali.
	 */
	public boolean equalControl(ElementoSemplice toCheck){
	    assert toCheck != null : "ElementoSemplice = null"; //precondizione 
		return (this.nome.equalsIgnoreCase(toCheck.getName()));
	}
	
	/**
	 * Metodo per ottenere le proprieta' principali di ogni elemento di una rete.
	 * In questo caso viene considerato solo il nome come elemento caratterizzante.
	 * @return nome dell'elemento.
	 */
	public String getProperties() {
		return this.nome;
	}
	
}
