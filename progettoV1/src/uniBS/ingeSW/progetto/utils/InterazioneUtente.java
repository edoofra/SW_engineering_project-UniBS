package uniBS.ingeSW.progetto.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

import uniBS.ingeSW.progetto.rete.ElemFlusso;
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
		String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO + ")";
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

		if (valoreLetto == RISPOSTA_SI)
			return true;
		else
			return false;
	}

	public static void creazioneRete(Rete daCreare) {
		System.out.println("hai deciso di creare una rete");
		aggiuntaPosto(daCreare);
		aggiuntaTransizione(daCreare);
		aggiuntaElementoFlusso(daCreare);

	}

	private static void aggiuntaPosto(Rete daCreare) {
		boolean risposta = true;
		System.out.println("per prima cosa devi aggiungere i posti.");
		while (risposta != false) {

			String nome = leggiStringaNonVuota("scegli un nome per il posto");
			Posto nuovo = new Posto(nome);
			daCreare.addPosto(nuovo);
			risposta = yesOrNo("vuoi aggiungere altri posti?");
		}
	}

	private static void aggiuntaTransizione(Rete daCreare) {
		boolean risposta = true;
		System.out.println("ora devi aggiungere le transizioni");
		while (risposta != false) {

			String nome = leggiStringaNonVuota("scegli un nome per la transizione");
			Transizione nuovo = new Transizione(nome);
			daCreare.addTrans(nuovo);
			risposta = yesOrNo("vuoi aggiungere altre transizioni?");
		}
	}

	private static void aggiuntaElementoFlusso(Rete daCreare) {
		boolean risposta = true;
		System.out.println("ora devi aggiungere gli elementi di flusso");
		while (risposta != false) {

			int scelta = leggiIntero("vuoi partire da un posto (1) o da una transizione(2)?", 1, 2);
			if (scelta == 1) {
				System.out.println(daCreare.getInsiemePosti());
				String nome=leggiStringaNonVuota("scegli il posto da cui far partire il flusso.");
				Posto elem1 = daCreare.getSinglePosto(nome);
				if(elem1 != null){
					System.out.println(daCreare.getInsiemeTransizioni());
					nome = leggiStringaNonVuota("ora scegli la transizione di destinazione");
					Transizione elem2 = daCreare.getSingleTrans(nome);
					if(elem2 != null){
						daCreare.addElemFlusso(new ElemFlusso(elem1, elem2));
					}
					//vanno gestiti casi in cui è null
					//vanno estratti i due metodi
					
				}
			} else
				System.out.println(daCreare.getInsiemeTransizioni());
				//stessa cosa ma per transizione
				//qualcuno riesce a trovare un fottuto modo per non ripetere tutto?
			
			
			
			risposta = yesOrNo("vuoi aggiungere altri elementi di flusso?");
		}
	}

	
}
