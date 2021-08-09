package uniBS.ingeSW.progettoV5.view;



public class UIMenu {
    
    public static final String SELEZIONA_IL_PROFILO_CON_CUI_ACCEDERE = "seleziona il profilo con cui accedere.";

    public static final String ATTENZIONE_SELEZIONE_NON_VALIDA = "Attenzione selezione non valida";

    private static final String SELEZIONA_UN_ALTERNATIVA = "seleziona un'alternativa.";

    public static final String RETI_PETRI_PRIORITA = "RETI_PETRI_PRIORITA";

    public static final String RETI_PETRI = "RETI_PETRI";

    public static final String RETI = "RETI";

    private static final String [] VOCI_MENU_INIZIALE= {"Crea nuova rete",
     "Visualizza le reti esistenti", "Visualizza le reti di Petri esistenti", "Estendi una rete in rete di Petri",
    "Estendi una rete di petri in rete di petri con priorita'", "Visualizza le reti di petri con priorita' esistenti",
    "Importa una rete da file", "Importa una rete di Petri da file", "Importa una rete di Petri con Priorita'"};

    private static final String[] VOCI_MENU_ESTERNO= {"Configuratore", "Fruitore"};

    private static final String[] VOCI_MENU_FRUITORE= {"Simula l'evoluzione di una rete di petri", "Simula l'evoluzione di una rete di petri con priorita'"};
    
     private static final String TITOLO = 
  
        "$$$$$$$   $$$$$$$$  $$$$$$$$  $$$$$$$$        $$     $$     $$$$$$$$        $$$$$$\n" + 
        "$$    $$  $$           $$     $$              $$     $$     $$             $$$   $$\n" +
        "$$    $$  $$           $$     $$              $$     $$     $$             $$$$  $$\n" +
        "$$$$$$$   $$$$$        $$     $$$$$            $$   $$      $$$$$$$$       $$ $$ $$\n" +
        "$$    $$  $$           $$     $$                $$ $$             $$       $$  $$$$\n" +
        "$$    $$  $$           $$     $$                 $$$        $$    $$       $$   $$$\n" +
        "$$    $$  $$$$$$$$     $$     $$$$$$$$            $          $$$$$   $$     $$$$$$\n";                                                                                
                                   

    public void printTitolo() {
	System.out.println(TITOLO);
    }
     
     public int startMenuEsterno(){
        Menu menuEsterno = new Menu(SELEZIONA_IL_PROFILO_CON_CUI_ACCEDERE, VOCI_MENU_ESTERNO);
        return menuEsterno.scegli();     
    }
    
    public int startMenuFruitore(){
	Menu menuFruitore = new Menu(SELEZIONA_UN_ALTERNATIVA, VOCI_MENU_FRUITORE);
        return menuFruitore.scegli();     
    }
    
    public int startMenuConfiguratore(){
	Menu menuConfiguratore = new Menu(SELEZIONA_UN_ALTERNATIVA, VOCI_MENU_INIZIALE);
           return menuConfiguratore.scegli();     
       }
    
}
