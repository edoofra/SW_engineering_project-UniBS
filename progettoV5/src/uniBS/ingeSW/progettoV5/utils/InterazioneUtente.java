package uniBS.ingeSW.progettoV5.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import uniBS.ingeSW.progettoV5.logica.gestioneReti.*;
import uniBS.ingeSW.progettoV5.logica.rete.*;
import uniBS.ingeSW.progettoV5.logica.retePetri.ListaPesiFlussoPN;
import uniBS.ingeSW.progettoV5.logica.retePetri.MarcaturaPN;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.Priorita;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.view.*;

public class InterazioneUtente {	

	private static final String LA_RETE_DI_PETRI_NON_E_ACCETTATA_PERCHE_NON_ESISTE_IN_MEMORIA_UNA_RETE_COMPATIBILE = "La rete di Petri non e' accettata perche' non esiste in memoria una rete compatibile.";
	private static final String LA_RETE_DI_PETRI_CON_PRIORITA_NON_E_ACCETTATA_PERCHE_NON_ESISTE_IN_MEMORIA_UNA_RETE_DI_PETRI_COMPATI = "La rete di Petri con Priorita' non e' accettata perche' non esiste in memoria una rete di Petri compatibile.";
	private static final String IL_PATH_INSERITO_NON_COINCIDE_CON_NESSUN_FILE = "Il path inserito non coincide con nessun file";
	private static final String INSERISCI_IL_PATH_DEL_FILE_DA_CUI_IMPORTARE_LA_RETE = "Inserisci il path del file da cui importare la rete: \n";
	private static final String ERROR_FLUSSO_NON_PRESENTE = "L'elemento scelto non e' presente o il nome e' stato digitato in modo sbagliato";
	private static final String ERROR_POSTO_NON_PRESENTE = "Il posto non e' presente o il nome e' stato digitato in modo sbagliato";
	private static final String ERROR_RETE_NON_PRESENTE = "La rete scelta non e'presente o il nome e' stato digitato in modo sbagliato";
	private static final String ERROR_TRANSIZIONE_NON_PRESENTE = "La transizione scelta non e' presente o il nome e' stato digitato in modo sbagliato";
	private static final String E_IN_DEADLOCK = " e' in deadlock.\n";
	private static final String LA_RETE = "La rete ";
	private static final String DOMANDA_ESTENSIONE_RETEPETRI_RETEPNP = "Scegli una delle seguenti reti di petri da estendere in Rete di Petri con priorità:";	
	private static final String DOMANDA_SALVATAGGIO_RETEPNP = "Vuoi salvare in modo persistente la Rete di Petri con Priorità appena creata?";
	private static final String DOMANDA_AGGIUNTA_ALTRE_PRIORITA = "vuoi cambiare altre priorità?";	
	private static Scanner lettore = creaScanner();
	private static final String MESSAGGIO_RETE_PN_DUPLICATA = "Questa rete di Petri esiste già quindi non puo' essere salvata";
	private static final String DOMANDA_SALVATAGGIO_RETEPN = "Vuoi salvare in modo persistente la rete di Petri appena creata?";
	private static final String DOMANDA_AGGIUNTA_ALTRI_PESI = "Vuoi cambiare altri pesi?";
	private static final String DOMANDA_AGGIUNTA_ALTRE_MARCATURE = "Vuoi cambiare altre marcature?";
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
	private static final String DOMANDA_ESTENSIONE_RETE = "Scegli una delle seguenti reti da estendere in Rete di Petri:";
	private static final String SCEGLI_UN_NUOVO_VALORE = "scegli un nuovo valore: \n->";
	private static final String TRANSIZIONI_ABILITATE = "Transizioni abilitate: \n";
	private static final String USCITA_SIMULAZIONE = "Uscire dalla simulazione? ";
	private static final String SCATTO_ELEMENTOFLUSSO = "scegli l'elemento di flusso da far scattare: ";
	private static final String ELEMENTOFLUSSO_CAMBIAMENTO_PESO = "scegli l'elemento di flusso a cui cambiare il peso:";
	private static final String POSTO_CAMBIAMENTO_MARCATURA = "scegli il posto a cui cambiare la marcatura:";
	private static final String TRANSIZIONE_CAMBIAMENTO_PRIORITA = "scegli la transizione di cui cambiare la priorità: ";
	private static final String CAMBIAMENTO_ELEMENTOFLUSSO = "vuoi cambiare il peso di qualche elemento di flusso?";
	private static final String CAMBIAMENTO_MARCATURA = "vuoi cambiare qualche marcatura?";
	private static final String CAMBIAMENTO_PRIORITA = "vuoi cambiare qualche priorità?";

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
		String[] possibiliPresentazioni = {AVVERTIMENTO_INIZIALE_POSTO, AVVERTIMENTO_INIZIALE_TRANSIZIONE};
		System.out.println(possibiliPresentazioni[tipoAggiunta]);
		String[] possibiliDomande = {MESSAGGIO_SCELTA_NOME_POSTO,MESSAGGIO_SCELTA_NOME_TRANSIZIONE };
		String nome = leggiStringaNonVuota(possibiliDomande[tipoAggiunta]);
		return nome;
	}

	public static boolean continuareAggiuntaYesOrNo(int tipoAggiunta){
		String[] possibiliPresentazioni = {DOMANDA_AGGIUNTA_ALTRI_POSTI, MESSAGGIO_AGGIUNTA_ALTRE_TRANSIZIONI, DOMANDA_AGGIUNTA_ALTRI_FLUSSO,
			 DOMANDA_AGGIUNTA_ALTRE_MARCATURE, DOMANDA_AGGIUNTA_ALTRI_PESI, DOMANDA_AGGIUNTA_ALTRE_PRIORITA};
		boolean risposta = yesOrNo(possibiliPresentazioni[tipoAggiunta]);
		return risposta;
	}

	public static String[] aggiuntaFlusso(Rete rete){
		assert rete != null; //precondizione
		RetePresentation reteView = new RetePresentation(rete);
		System.out.println(MESSAGGIO_INIZIALE_FLUSSO);
		System.out.println(AVVERTIMENTO_INIZIALE_FLUSSO);
		System.out.println(COMBINAZIONE_AMMESSA_FLUSSO_1);
		System.out.println(COMBINAZIONE_AMMESSA_FLUSSO_2);
		System.out.println(ELEMENTI_DELLA_TUA_RETE); 
		System.out.println(POSTI + reteView.getStringList(rete.getInsiemePosti().toArray(new Posto[0])));
		System.out.println(TRANSIZIONI + reteView.getStringList(rete.getInsiemeTransizioni().toArray(new Transizione[0])) + "\n");
		String nome1 = leggiStringaNonVuota(MESSAGGIO_SCELTA_ELEMENTO_1_FLUSSO);
		String nome2 = leggiStringaNonVuota(MESSAGGIO_SCELTA_ELEMENTO_2_FLUSSO);
		String[] nomi = {nome1, nome2};
		return nomi;
	}

	public static void controlloRete(int tipoMessaggio){
		String[] possibiliPresentazioni = {WARNING_RETE_NON_CONNESSA,WARNING_RETE_NON_CORRETTA,WARNING_RETE_GIA_PRESENTE,MESSAGGIO_RETE_CORRETTA_CONNESSA };
		System.out.println(possibiliPresentazioni[tipoMessaggio]);
	}

	public static String salvataggioRete(int tipoRete){
		String[] possibiliPresentazioni = {DOMANDA_SALVATAGGIO_RETE, DOMANDA_SALVATAGGIO_RETEPN, 
			DOMANDA_SALVATAGGIO_RETEPNP};
		boolean risposta = yesOrNo(possibiliPresentazioni[tipoRete]);
		if(risposta){
			return leggiStringaNonVuota(DOMANDA_NOME_RETE);
		}
		else return null;
	}

	public static void aggiuntaRete(){
		System.out.println(MESSAGGIO_CREAZIONE_RETE);
	}

	public static void messaggioErroreListaRetiDaVisualizzareVuota(){
		System.out.println(WARNING_LISTA_RETI_VUOTA);
	}

	public static String getNomeReteDaVisualizzare(GestoreReti listaReti){
		System.out.println(MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE);
		stampaListaRetiGestore(listaReti);
		String daVisualizzare = leggiStringaNonVuota("\n-> ");
		return daVisualizzare;
	}

	//overloaded
	public static String getNomeReteDaVisualizzare(GestoreRetiPetri listaReti){
		System.out.println(MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE);
		stampaListaRetiGestore(listaReti);
		String daVisualizzare = leggiStringaNonVuota("\n-> ");
		return daVisualizzare;
	}

	public static String getNomeReteDaVisualizzare(GestoreRetiPetriPriorita listaReti){
		System.out.println(MESSAGGIO_SCELTA_RETE_DA_VISUALIZZARE);
		stampaListaRetiGestore(listaReti);
		String daVisualizzare = leggiStringaNonVuota("\n-> ");
		return daVisualizzare;
	}

	public static void stampaListaRetiGestore(GestoreReti listaReti){
		GestoreRetiPresentation listaView = new GestoreRetiPresentation(listaReti);
		System.out.println("\t" + listaView.toString());
	}

	//overloaded 
	public static void stampaListaRetiGestore(GestoreRetiPetri listaReti){
		GestoreRetiPetriPresentation listaView = new GestoreRetiPetriPresentation(listaReti);
		System.out.println("\t" + listaView.toString());
	}

	public static void stampaListaRetiGestore(GestoreRetiPetriPriorita listaReti){
		GestoreRetiPetriPrioritaPresentation listaView = new GestoreRetiPetriPrioritaPresentation(listaReti);
		System.out.println("\t" + listaView.toString());
	}

	public static void stampaReteSceltaPerVisualizzazione(Rete daVisualizzare){
		System.out.println(new RetePresentation(daVisualizzare));
	}

	//overloaded
	public static void stampaReteSceltaPerVisualizzazione(RetePetri daVisualizzare){
		System.out.println(new RetePetriPresentation(daVisualizzare));
	}

	public static void stampaReteSceltaPerVisualizzazione(RetePetriPriorita daVisualizzare){
		System.out.println(new RetePetriPrioritaPresentation(daVisualizzare));
	}

	public static String estendiReteView(GestoreReti listaReti){
		System.out.println(DOMANDA_ESTENSIONE_RETE);
		stampaListaRetiGestore(listaReti);
		String nome = leggiStringaNonVuota("->");
		return nome;
	}

	public static boolean domandaCambiamentoDatiRetePetri(int tipoDato){
		String[] possibiliDomande = {CAMBIAMENTO_MARCATURA, CAMBIAMENTO_ELEMENTOFLUSSO, CAMBIAMENTO_PRIORITA};
		return yesOrNo(possibiliDomande[tipoDato]);
	}
	
	public static void printListaMarcature(MarcaturaPN marcatura){
		MarcaturaPresentation view = new MarcaturaPresentation(marcatura);
		System.out.println(view.toString());
	}

	public static String leggiElementoDaCambiare(int tipoElemento){
		String[] possibiliDomande = {POSTO_CAMBIAMENTO_MARCATURA, ELEMENTOFLUSSO_CAMBIAMENTO_PESO, SCATTO_ELEMENTOFLUSSO, TRANSIZIONE_CAMBIAMENTO_PRIORITA};
		String nomePosto = leggiStringaNonVuota(possibiliDomande[tipoElemento]);
		return nomePosto;
	}

	public static int leggiNuovoValoreDaInserirePerCambiamentoDati(int tipoElemento){
		int[] possibiliMinimi = {0,1};
		int nuovoValore = leggiInteroConMinimo(SCEGLI_UN_NUOVO_VALORE, possibiliMinimi[tipoElemento]);
		return nuovoValore;
	}

	public static void printListaPesi(ListaPesiFlussoPN listaPesi){
		ListaPesiPresentation view = new ListaPesiPresentation(listaPesi);
		System.out.println(view.toString());
	}

	public static void printErroreRetePNDuplicata(){
		System.out.println(MESSAGGIO_RETE_PN_DUPLICATA);
	}

	public static void printPossibiliTransizioniPerSimulazione(ArrayList <ElemFlusso> possibiliTrans){
		System.out.println(TRANSIZIONI_ABILITATE);
		for(ElemFlusso elem : possibiliTrans){
			System.out.println(new ElemFlussoPresentation(elem).getName()+ "\n");
		}
	}

	public static void printErrorDeadlock(String nomeRete){
		System.out.println(LA_RETE+ nomeRete + E_IN_DEADLOCK);
	}

	public static boolean domandaContinuareSimulazione(){
		return yesOrNo(USCITA_SIMULAZIONE);
	}

	public static String estendiRetePNinPrioritaView(GestoreRetiPetri listaRetiPN){
		System.out.println(DOMANDA_ESTENSIONE_RETEPETRI_RETEPNP);
		stampaListaRetiGestore(listaRetiPN);
		String nome = leggiStringaNonVuota("->");
		return nome;
	}

	public static void printListaPriorita(Priorita listaPriorita){
		PrioritaPresentation view = new PrioritaPresentation(listaPriorita);
		System.out.println(view.toString());
	}

	public static void printErrorReteNonPresente(){
		System.out.println(ERROR_RETE_NON_PRESENTE);
	}

	
	public static void printErrorPostoNonPresente(){
		System.out.println(ERROR_POSTO_NON_PRESENTE);
	}

	
	public static void printErrorElemFlussoNonPresente(){
		System.out.println(ERROR_FLUSSO_NON_PRESENTE);
	}

	
	public static void printErrorTransizioneNonPresente(){
		System.out.println(ERROR_TRANSIZIONE_NON_PRESENTE);
	}	

	public static String leggiPath(){
		return leggiStringaNonVuota(INSERISCI_IL_PATH_DEL_FILE_DA_CUI_IMPORTARE_LA_RETE);
	}

	public static void printErrorNoFile(){
		System.out.println(IL_PATH_INSERITO_NON_COINCIDE_CON_NESSUN_FILE);
	}

	public static void printErrorRetePetriPrioritaNonAccettata(){
		System.out.println(LA_RETE_DI_PETRI_CON_PRIORITA_NON_E_ACCETTATA_PERCHE_NON_ESISTE_IN_MEMORIA_UNA_RETE_DI_PETRI_COMPATI);
	}

	public static void printErrorRetePetriNonAccettata(){
		System.out.println(LA_RETE_DI_PETRI_NON_E_ACCETTATA_PERCHE_NON_ESISTE_IN_MEMORIA_UNA_RETE_COMPATIBILE);
	}
}
