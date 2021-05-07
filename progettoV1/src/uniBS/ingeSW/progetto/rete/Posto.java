package uniBS.ingeSW.progetto.rete;

/**
 * Classe che descrive l'implementazione di un posto all'interno di una rete.
 * Posto è visto come un' <em> estensione di un elemento semplice </em>.
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani 
 * @version 1.0
 */
public class Posto extends ElementoSemplice {

	/**
	 * Prefisso che viene aggiunto davanti al nome di ogni posto per poterlo identificare meglio.
	 */
	private static final String PREFISSO_POSTO = "P:";

	/**
	 * Metodo che assegna un nome al posto al momento della creazione.
	 * Viene aggiunto un prefisso al nome.
	 * @param nome nome scelto dal configuratore al momento della creazione
	 */
	public Posto(String nome) {
		super.setName(PREFISSO_POSTO+ nome);
	}
}
