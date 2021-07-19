package uniBS.ingeSW.progettoV3.logica.rete;

/**
 * Classe che descrive l'implementazione di una transizione all'interno di una rete.
 * Transizione e' vista come un' <em> estensione di un elemento semplice </em>.
 * @author Camilla Bonomini
 * @author Edoardo Fratus
 * @author Lorenzo Bargnani 
 * @version 1.0
 */
public class Transizione extends ElementoSemplice {
	
	/**
	 * Prefisso che viene aggiunto al nome di ogni transizone per poterla identificare meglio.
	 */
	private static final String PREFISSO_TRANSIZIONE = "T:";

	/**
	 * Metodo che assegna un nome alla transizione al momento della creazione.
	 * Viene aggiunto un prefisso al nome.
	 * @param nome nome scelto dal configuratore al momento della creazione
	 */
	public Transizione(String nome) {
		setName(PREFISSO_TRANSIZIONE + nome);
	}	
}
