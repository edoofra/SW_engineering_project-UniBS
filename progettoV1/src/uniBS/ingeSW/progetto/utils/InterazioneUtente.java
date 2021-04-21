package uniBS.ingeSW.progetto.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import uniBS.ingeSW.progetto.gestioneReti.GestoreReti;
import uniBS.ingeSW.progetto.rete.ElemFlusso;
import uniBS.ingeSW.progetto.rete.ElementoSemplice;
import uniBS.ingeSW.progetto.rete.Posto;
import uniBS.ingeSW.progetto.rete.Rete;
import uniBS.ingeSW.progetto.rete.Transizione;

public class InterazioneUtente {
	private static Scanner lettore = creaScanner();

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
		boolean finito = false;
		String lettura = null;
		do {
			lettura = leggiStringa(messaggio);
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito = true;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);

		return lettura;
	}

	public static char leggiChar(String messaggio) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			System.out.print(messaggio);
			String lettura = lettore.next();
			if (lettura.length() > 0) {
				valoreLetto = lettura.charAt(0);
				finito = true;
			} else {
				System.out.println(ERRORE_STRINGA_VUOTA);
			}
		} while (!finito);
		return valoreLetto;
	}

	public static char leggiUpperChar(String messaggio, String ammissibili) {
		boolean finito = false;
		char valoreLetto = '\0';
		do {
			valoreLetto = leggiChar(messaggio);
			valoreLetto = Character.toUpperCase(valoreLetto);
			if (ammissibili.indexOf(valoreLetto) != -1)
				finito = true;
			else
				System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
		} while (!finito);
		return valoreLetto;
	}

	public static int leggiIntero(String messaggio) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextInt();
				finito = true;
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
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static int leggiIntero(String messaggio, int minimo, int massimo) {
		boolean finito = false;
		int valoreLetto = 0;
		do {
			valoreLetto = leggiIntero(messaggio);
			if (valoreLetto >= minimo && valoreLetto <= massimo)
				finito = true;
			else if (valoreLetto < minimo)
				System.out.println(ERRORE_MINIMO + minimo);
			else
				System.out.println(ERRORE_MASSIMO + massimo);
		} while (!finito);

		return valoreLetto;
	}

	public static double leggiDouble(String messaggio) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			System.out.print(messaggio);
			try {
				valoreLetto = lettore.nextDouble();
				finito = true;
			} catch (InputMismatchException e) {
				System.out.println(ERRORE_FORMATO);
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
	}

	public static double leggiDoubleConMinimo(String messaggio, double minimo) {
		boolean finito = false;
		double valoreLetto = 0;
		do {
			valoreLetto = leggiDouble(messaggio);
			if (valoreLetto >= minimo)
				finito = true;
			else
				System.out.println(ERRORE_MINIMO + minimo);
		} while (!finito);

		return valoreLetto;
	}

	public static boolean yesOrNo(String messaggio) {
		String mioMessaggio = messaggio;
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return true;
		else
			return false;
	}

	public static void creazioneRete(Rete daCreare, GestoreReti listaReti) {
		System.out.println("\nHAI DECISO DI CREARE UNA RETE!\n");
		aggiuntaPosto(daCreare);
		aggiuntaTransizione(daCreare);
		aggiuntaElementoFlusso(daCreare);
		System.out.println("");

		if(controlloRete(daCreare)){
			boolean risposta = yesOrNo("Vuoi salvare in modo permanente la tua rete? -> ");
			if(risposta) {
				boolean rifare = false;
				do{
					//verificare unicità rete
					String nome = leggiStringaNonVuota("Che nome vuoi dare a questa rete? -> ");
					boolean aggiunta = listaReti.addRete(nome, daCreare);
					if(!aggiunta){
						System.out.println("Attenzione: esiste già una rete con questo nome.");
						rifare = yesOrNo("vuoi cambiare nome? (S|N) -> "); 
					}
					else rifare = false;
					
				}while(rifare);
				String listaRetiJSON = ConvertitoreJson.daOggettoAJson(listaReti);
				salvataggioFile.salvaGestoreReti(listaRetiJSON);
			}
		}		
	}

	private static void aggiuntaPosto(Rete daCreare) {
		boolean risposta = true;
		System.out.println("Per iniziare devi aggiungere almeno un posto!");
		while (risposta != false) {

			String nome = leggiStringaNonVuota("\tScegli un nome per il posto -> ");
			Posto nuovo = new Posto(nome);
			daCreare.addPosto(nuovo);
			risposta = yesOrNo("\tVuoi aggiungere altri posti? (S|N) -> ");
		}
	}

	private static void aggiuntaTransizione(Rete daCreare) {
		boolean risposta = true;
		System.out.println("\nOra devi aggiungere almeno una transizione!");
		while (risposta != false) {

			String nome = leggiStringaNonVuota("\tScegli un nome per la transizione -> ");
			Transizione nuovo = new Transizione(nome);
			daCreare.addTrans(nuovo);
			risposta = yesOrNo("\tVuoi aggiungere altre transizioni? (S|N) -> ");
		}
	}

	private static void aggiuntaElementoFlusso(Rete daCreare) {
		boolean risposta = true;
		System.out.println("\nCi siamo quasi! Ora devi aggiungere gli elementi di flusso!");
		System.out.println("ATTENZIONE: sono ammesse solo le seguenti combinazioni:");
		System.out.println("Elemento di flusso = (posto -> transizione)");
		System.out.println("Elemento di flusso = (transizione -> posto)\n");
		while (risposta != false) {
			ElemFlusso nuovo = creaElementoFlusso(daCreare);
			if(nuovo!=null) {
				boolean corretto = daCreare.addElemFlusso(nuovo);
				if(!corretto) System.out.println("\tAttenzione: l'elemento non è corretto!");
			}	
			risposta = yesOrNo("\tVuoi aggiungere altri elementi di flusso? (S|N) -> ");
			System.out.println("");
			
		}
	}


    private static ElemFlusso creaElementoFlusso(Rete rete){
		ElementoSemplice elem1 = null;
		ElementoSemplice elem2 = null;
		System.out.println("\tElementi della tua rete:"); 
		System.out.println("\tPOSTI: " + rete.getStringList(rete.getInsiemePosti()));
		System.out.println("\tTRANSIZIONI " + rete.getStringList(rete.getInsiemeTransizioni()) + "\n");
		String nome1 = leggiStringaNonVuota("\tElemento 1 -> ");
		String nome2 = leggiStringaNonVuota("\tElemento 2 -> ");

		if(nome1.charAt(0)=='P') elem1 = rete.getPostoByName(nome1);
		else elem1 = rete.getTransByName(nome1);
		
		if(nome2.charAt(0)=='P') elem2 = rete.getPostoByName(nome2);
		else elem2 = rete.getTransByName(nome2);

		if(elem1 == null || elem2 == null){
			System.out.println("\tUno dei due elementi non è stato trovato! \n");
			return null;
		} 
		return new ElemFlusso(elem1, elem2);
		
	}	

	public static void visualizzaReteDaGestore(GestoreReti lista){
		if(lista.getListaRetiConfiguratore().isEmpty())
		System.out.println("\nAttenzione: La lista di reti è vuota");
		else {
			System.out.println("\nScegli una tra le seguenti reti da visualizzare: \n");
			boolean trovato = false;
			System.out.println("\t" + lista.toString());
			String daVisualizzare = leggiStringaNonVuota("\n-> ");
			for(String elem : lista.getKeyLIst()){
				if (elem.equalsIgnoreCase(daVisualizzare)){
					System.out.println(lista.getListaRetiConfiguratore().get(daVisualizzare));
					trovato = true;
				} 
			}
			if(!trovato) System.out.println("Attenzione: Non esiste una rete con questo nome");			
		}
	}

	private static boolean controlloRete(Rete daControllare){
		boolean connessa = daControllare.controlloConnessione();
		boolean corretta = daControllare.controlloCorrettezza();
		if(!connessa) System.out.println("Attenzione: la tua rete non è connessa!\n");
		if(!corretta) System.out.println("Attenzione: la tua rete non è corretta!\n");
		if(corretta && connessa) System.out.println("COMPLIMENTI! LA TUA RETE E' CORRETTA E CONNESSA \n");

		return (connessa && corretta);
	}
}

//cambiare ogni syso con la chiamata a due metodi printInfo(msg) o printError(msg) in modo
//che sia chiaro cosa fa ogni stampa

//dare nomi autoesplicativi ai metodi 