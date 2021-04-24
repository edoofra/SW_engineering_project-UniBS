package uniBS.ingeSW.progettoV2.logica.rete;

/**
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani 
 * 	Classe per implementazione di un posto di una rete
 */
public class Posto extends ElementoSemplice {

	/**
	 *
	 */
	private static final String PREFISSO_POSTO = "P:";

	/**
	 * @param nome nome scelto dal configuratore al momento della creazione
	 */
	public Posto(String nome) {
		setName(PREFISSO_POSTO+ nome);
	}
}
