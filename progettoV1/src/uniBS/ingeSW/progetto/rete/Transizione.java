package uniBS.ingeSW.progetto.rete;

/**
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * Classe per l'implementazione di una transizione in una rete
 */
public class Transizione extends ElementoSemplice {
	
	/**
	 * @param nome scelto dal configuratore al momento della creazione
	 */
	public Transizione(String nome) {
		setName(nome);
	}	
}
