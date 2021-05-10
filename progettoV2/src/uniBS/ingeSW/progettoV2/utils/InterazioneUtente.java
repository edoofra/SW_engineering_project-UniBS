package uniBS.ingeSW.progettoV2.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import uniBS.ingeSW.progettoV2.logica.gestioneReti.*;
import uniBS.ingeSW.progettoV2.logica.rete.*;
import uniBS.ingeSW.progettoV2.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV2.view.*;

public class InterazioneUtente {	

	/**
	 *
	 */
	
	private static Scanner lettore = creaScanner();

	private static final String WARNING_TRANSIZIONE_GIA_PRESENTE = "Attenzione:questa transizione è già stata aggiunta";
	private static final String WARNING_POSTO_GIA_AGGIUNTO = "Attenzione: questo posto è già stato aggiunto";
	private static final String WARNING_RETE_GIA_PRESENTE = "Attenzione: questa rete è già presente!";
	private static final boolean BOOL_CONST_FALSE = false;
	private static final boolean BOOL_CONST_TRUE = true;
	private static final String MESSAGGIO_RETE_CORRETTA_CONNESSA = "COMPLIMENTI! LA TUA RETE E' CORRETTA E CONNESSA \n";
	private static final String WARNING_RETE_NON_CORRETTA = "Attenzione: la tua rete non è corretta!\n";
	private static final String WARNING_RETE_NON_CONNESSA = "Attenzione: la tua rete non è connessa!\n";
	private static final String WARNING_RETE_NON_ESISTE = "Attenzione: Non esiste una rete con questo nome";
	private static final String MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE = "\nScegli una tra le seguenti reti da visualizzare: \n";
	private static final String WARNING_LISTA_RETI_VUOTA = "\nAttenzione: La lista di reti è vuota";
	private static final String WARNING_ELEMENTO_INTERNO_FLUSSO_NON_TROVATO = "\tUno dei due elementi non è stato trovato! \n";
	private static final String MESSAGGIO_SCELTA_ELEMENTO_2_FLUSSO = "\tElemento 2 -> ";
	private static final String MESSAGGIO_SCELTA_ELEMENTO_1_FLUSSO = "\tElemento 1 -> ";
	private static final String TRANSIZIONI = "\tTRANSIZIONI ";
	private static final String POSTI = "\tPOSTI: ";
	private static final String ELEMENTI_DELLA_TUA_RETE = "\tElementi della tua rete:";
	private static final String DOMANDA_AGGIUNTA_ALTRI_FLUSSO = "\tVuoi aggiungere altri elementi di flusso? (S|N) -> ";
	private static final String WARNING_ELEMENTO_SCORRETTO = "\tAttenzione: l'elemento non è corretto!";
	private static final String COMBINAZIONE_AMMESSA_FLUSSO_2 = "Elemento di flusso = (transizione -> posto)\n";
	private static final String COMBINAZIONE_AMMESSA_FLUSSO_1 = "Elemento di flusso = (posto -> transizione)";
	private static final String AVVERTIMENTO_INIZIALE_FLUSSO = "ATTENZIONE: sono ammesse solo le seguenti combinazioni:";
	private static final String MESSAGGIO_INIZIALE_FLUSSO = "\nCi siamo quasi! Ora devi aggiungere gli elementi di flusso!";
	private static final String MESSAGGIO_AGGIUNTA_ALTRE_TRANSIZIONI = "\tVuoi aggiungere altre transizioni? (S|N) -> ";
	private static final String MESSAGGIO_SCELTA_NOME_TRANSIZIONE = "\tScegli un nome per la transizione -> ";
	private static final String AVVERTIMENTO_INIZIALE_TRANSIZIONE = "\nOra devi aggiungere almeno una transizione!";
	private static final String DOMANDA_AGGIUNTA_ALTRI_POSTI = "\tVuoi aggiungere altri posti? (S|N) -> ";
	private static final String MESSAGGIO_SCELTA_NOME_POSTO = "\tScegli un nome per il posto -> ";
	private static final String AVVERTIMENTO_INIZIALE_POSTO = "Per iniziare devi aggiungere almeno un posto!";
	private static final String DOMANDA_CAMBIO_NOME = "vuoi cambiare nome? (S|N) -> ";
	private static final String WARNING_NOME_GIA_USATO = "Attenzione: esiste già una rete con questo nome.";
	private static final String DOMANDA_NOME_RETE = "Che nome vuoi dare a questa rete? -> ";
	private static final String DOMANDA_SALVATAGGIO_RETE = "Vuoi salvare in modo permanente la tua rete? -> ";
	private static final String MESSAGGIO_CREAZIONE_RETE = "\nHAI DECISO DI CREARE UNA RETE!\n";
	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	private final static String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere";
	private final static String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String MESSAGGIO_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";
	private final static char RISPOSTA_SI = 'S';
	private final static char RISPOSTA_NO = 'N';

	private static Scanner creaScanner() {
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}

	public static String leggiStringa(String messaggio) {
		System.out.print(messaggio);
		return lettore.next();
	}

	public static String leggiStringaNonVuota(String messaggio) {
		boolean finito = BOOL_CONST_FALSE;
		String lettura = null;
		do {
			lettura = leggiStringa(messaggio);
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito = BOOL_CONST_TRUE;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);

		return lettura;
	}

	public static char leggiChar(String messaggio) {
		boolean finito = BOOL_CONST_FALSE;
		char valoreLetto = '\0';
		do {
			System.out.print(messaggio);
			String lettura = lettore.next();
			if (lettura.length() > 0) {
				valoreLetto = lettura.charAt(0);
				finito = BOOL_CONST_TRUE;
			} else {
				System.out.println(ERRORE_STRINGA_VUOTA);
			}
		} while (!finito);
		return valoreLetto;
	}

	public static char leggiUpperChar(String messaggio, String ammissibili) {
		boolean finito = BOOL_CONST_FALSE;
		char valoreLetto = '\0';
		do {
			valoreLetto = leggiChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = BOOL_CONST_TRUE;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiIntero(String messaggio) {
		boolean finito = BOOL_CONST_FALSE;
		int valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextInt();
				finito = BOOL_CONST_TRUE;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiInteroPositivo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 1);
	}

	public static int leggiInteroNonNegativo(String messaggio) {
		return leggiInteroConMinimo(messaggio, 0);
	}

	public static int leggiInteroConMinimo(String messaggio, int minimo) {
		boolean finito = BOOL_CONST_FALSE;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo)
				finito = BOOL_CONST_TRUE;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = BOOL_CONST_FALSE;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = BOOL_CONST_TRUE;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static double leggiDouble(String messaggio) {
		boolean finito = BOOL_CONST_FALSE;
		double valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextDouble();
				finito = BOOL_CONST_TRUE;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static double leggiDoubleConMinimo(String messaggio, double minimo) {
		boolean finito = BOOL_CONST_FALSE;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = BOOL_CONST_TRUE;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static boolean yesOrNo(String messaggio) {
		String mioMessaggio = messaggio;
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return BOOL_CONST_TRUE;
		else
			return BOOL_CONST_FALSE;
	}

	public static String aggiuntaElemento(int tipoAggiunta){
		String[] possibiliPresentazioni = {AVVERTIMENTO_INIZIALE_POSTO, AVVERTIMENTO_INIZIALE_TRANSIZIONE, AVVERTIMENTO_INIZIALE_FLUSSO };
		System.out.println(possibiliPresentazioni[tipoAggiunta]);
		String nome = leggiStringaNonVuota(MESSAGGIO_SCELTA_NOME_POSTO);
		return nome;
	}

	public static boolean continuareAggiuntaYesOrNo(int tipoAggiunta){
		String[] possibiliPresentazioni = {DOMANDA_AGGIUNTA_ALTRI_POSTI, MESSAGGIO_AGGIUNTA_ALTRE_TRANSIZIONI, DOMANDA_AGGIUNTA_ALTRI_FLUSSO};
		boolean risposta = yesOrNo(possibiliPresentazioni[tipoAggiunta]);
		return risposta;
	}

	public static void creazioneRete(Rete daCreare, GestoreReti listaReti) {
		assert daCreare != null && listaReti != null;  //precondizione
		System.out.println(MESSAGGIO_CREAZIONE_RETE);
		aggiuntaPosto(daCreare);
		aggiuntaTransizione(daCreare);
		aggiuntaElementoFlusso(daCreare);
		System.out.println("");

		if(controlloRete(daCreare, listaReti)){
			boolean risposta = yesOrNo(DOMANDA_SALVATAGGIO_RETE);
			if(risposta) {
				boolean rifare = BOOL_CONST_FALSE;
				do{
					String nome = leggiStringaNonVuota(DOMANDA_NOME_RETE);
					boolean aggiunta = listaReti.addRete(nome, daCreare);
					if(!aggiunta){
						System.out.println(WARNING_NOME_GIA_USATO);
						rifare = yesOrNo(DOMANDA_CAMBIO_NOME); 
					}
					else rifare = BOOL_CONST_FALSE;
					
				}while(rifare);
				String listaRetiJSON = ConvertitoreJson.daOggettoAJson(listaReti);
				salvataggioFile.salvaGestoreReti(listaRetiJSON);
			}
		}
		//postcondizione se file exist EDO
	}

	private static void aggiuntaPosto(Rete daCreare) {
		assert daCreare != null; //precondizione
		boolean risposta = BOOL_CONST_TRUE;
		System.out.println(AVVERTIMENTO_INIZIALE_POSTO);
		while (risposta != BOOL_CONST_FALSE) {

			String nome = leggiStringaNonVuota(MESSAGGIO_SCELTA_NOME_POSTO);
			Posto nuovo = new Posto(nome);
			boolean aggiuntoCorrettamente = daCreare.addPosto(nuovo);
			if(!aggiuntoCorrettamente) System.out.println(WARNING_POSTO_GIA_AGGIUNTO);
			risposta = yesOrNo(DOMANDA_AGGIUNTA_ALTRI_POSTI);
		}
	}

	private static void aggiuntaTransizione(Rete daCreare) {
		assert daCreare != null; //precondizione
		boolean risposta = BOOL_CONST_TRUE;
		System.out.println(AVVERTIMENTO_INIZIALE_TRANSIZIONE);
		while (risposta != BOOL_CONST_FALSE) {

			String nome = leggiStringaNonVuota(MESSAGGIO_SCELTA_NOME_TRANSIZIONE);
			Transizione nuovo = new Transizione(nome);
			boolean aggiuntaCorrettamente = daCreare.addTrans(nuovo);
			if(!aggiuntaCorrettamente) System.out.println(WARNING_TRANSIZIONE_GIA_PRESENTE);
			risposta = yesOrNo(MESSAGGIO_AGGIUNTA_ALTRE_TRANSIZIONI);
		}
	}

	private static void aggiuntaElementoFlusso(Rete daCreare) {
		assert daCreare != null; //precondizione
		boolean risposta = BOOL_CONST_TRUE;
		System.out.println(MESSAGGIO_INIZIALE_FLUSSO);
		System.out.println(AVVERTIMENTO_INIZIALE_FLUSSO);
		System.out.println(COMBINAZIONE_AMMESSA_FLUSSO_1);
		System.out.println(COMBINAZIONE_AMMESSA_FLUSSO_2);
		while (risposta != BOOL_CONST_FALSE) {
			ElemFlusso nuovo = creaElementoFlusso(daCreare);
			if(nuovo!=null) {
				boolean corretto = daCreare.addElemFlusso(nuovo);
				if(!corretto) System.out.println(WARNING_ELEMENTO_SCORRETTO);
			}	
			risposta = yesOrNo(DOMANDA_AGGIUNTA_ALTRI_FLUSSO);
			System.out.println("");
			
		}
	}


    private static ElemFlusso creaElementoFlusso(Rete rete){
    	assert rete != null; //precondizione
    		RetePresentation reteView = new RetePresentation(rete);
		ElementoSemplice elem1 = null;
		ElementoSemplice elem2 = null;
		System.out.println(ELEMENTI_DELLA_TUA_RETE); 
		System.out.println(POSTI + reteView.getStringList(rete.getInsiemePosti().toArray(new Posto[0])));
		System.out.println(TRANSIZIONI + reteView.getStringList(rete.getInsiemeTransizioni().toArray(new Transizione[0])) + "\n");
		String nome1 = leggiStringaNonVuota(MESSAGGIO_SCELTA_ELEMENTO_1_FLUSSO);
		String nome2 = leggiStringaNonVuota(MESSAGGIO_SCELTA_ELEMENTO_2_FLUSSO);

		if(nome1.charAt(0)=='P') elem1 = rete.getPostoByName(nome1);
		else elem1 = rete.getTransByName(nome1);
		
		if(nome2.charAt(0)=='P') elem2 = rete.getPostoByName(nome2);
		else elem2 = rete.getTransByName(nome2);

		if(elem1 == null || elem2 == null){
			System.out.println(WARNING_ELEMENTO_INTERNO_FLUSSO_NON_TROVATO);
			return null;
		} 
		return new ElemFlusso(elem1, elem2);
		
	}	

	public static void visualizzaReteDaGestore(GestoreReti lista){
		assert lista != null; //precondizione
		if(lista.getListaRetiConfiguratore().isEmpty())
			System.out.println(WARNING_LISTA_RETI_VUOTA);
		else {
			GestoreRetiPresentation listaView = new GestoreRetiPresentation(lista);
			System.out.println(MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE);
			boolean trovato = BOOL_CONST_FALSE;
			System.out.println("\t" + listaView.toString());
			String daVisualizzare = leggiStringaNonVuota("\n-> ");
			for(String elem : lista.getKeyLIst()){
				if (elem.equals(daVisualizzare)){
					System.out.println(lista.getListaRetiConfiguratore().get(daVisualizzare));
					trovato = BOOL_CONST_TRUE;
				} 
			}
			if(!trovato) System.out.println(WARNING_RETE_NON_ESISTE);			
		}
	}

	private static boolean controlloRete(Rete daControllare, GestoreReti listaReti){
		assert daControllare != null && listaReti != null; //precondizioni
		boolean connessa = daControllare.controlloConnessione();
		boolean corretta = daControllare.controlloCorrettezza();
		if(!connessa) System.out.println(WARNING_RETE_NON_CONNESSA);
		if(!corretta) System.out.println(WARNING_RETE_NON_CORRETTA);
		if(corretta && connessa) System.out.println(MESSAGGIO_RETE_CORRETTA_CONNESSA);
		for(String nomeRete : listaReti.getKeyLIst()){
			if(listaReti.getListaRetiConfiguratore().get(nomeRete).isEqual(daControllare)) {
				System.out.println(WARNING_RETE_GIA_PRESENTE);
				return BOOL_CONST_FALSE;
			}
			
		}
		return (connessa && corretta);
	}
	
	public void estensioneReteInPN(GestoreReti listaRetiPetri, GestoreReti listaReti) {
	    assert listaReti != null; //precondizione
	    RetePetri reteEstesa = null;
	    if (listaReti.getListaRetiConfiguratore().isEmpty())
		System.out.println(WARNING_LISTA_RETI_VUOTA);
	    else {
		System.out.println(MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE);
		boolean trovato = BOOL_CONST_FALSE;
		System.out.println("\t" + listaReti.toString());
		String daEstendere = leggiStringaNonVuota("\n-> ");
		for(String elem : listaReti.getKeyLIst()){
			if (elem.equalsIgnoreCase(daEstendere)){
				reteEstesa = new RetePetri(listaReti.getListaRetiConfiguratore().get(daEstendere));
				trovato = BOOL_CONST_TRUE;
				//richiesta modifica
				//richiesta salvataggio
			} 
		}
		if(!trovato) System.out.println(WARNING_RETE_NON_ESISTE);
	    } 
	}
	
	
}

//cambiare ogni syso con la chiamata a due metodi printInfo(msg) o printError(msg) in modo
//che sia chiaro cosa fa ogni stampa

//dare nomi autoesplicativi ai metodi 