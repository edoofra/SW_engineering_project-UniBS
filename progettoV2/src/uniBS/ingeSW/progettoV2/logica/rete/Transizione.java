package uniBS.ingeSW.progettoV2.logica.rete;

/**
 * @author Edoardo Fratus
 * @author Camilla Bonomini
 * @author Lorenzo Bargnani
 * Classe per l'implementazione di una transizione in una rete
 */
public class Transizione extends ElementoSemplice {
	
	/**
	 *
	 */
	private static final String PREFISSO_TRANSIZIONE = "T:";

	/**
	 * @param nome scelto dal configuratore al momento della creazione
	 */
	public Transizione(String nome) {
		setName(PREFISSO_TRANSIZIONE + nome);
	}	
}
