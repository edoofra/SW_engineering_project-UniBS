package uniBS.ingeSW.progetto.rete;

/**
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani 
 * 		   Classe per implementazione di un posto di una rete
 *         Eredita dalla classe ElementoSemplice
 */
public class Posto extends ElementoSemplice {

	/**
	 * Costruttore
	 * 
	 * @param nome nome scelto dal configuratore al momento della creazione
	 */
	public Posto(String nome) {
		this.nome = "P:" + nome;
	}

	/**
	 * @return le proprietà principali che descrivono un posto in una rete
	 */
	@Override
	public String getProperties() {
		return this.nome;
	}

}
