package uniBS.ingeSW.progettoV5.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
import uniBS.ingeSW.progettoV5.logica.retePetri.RetePetri;
import uniBS.ingeSW.progettoV5.logica.retePetriPriorita.RetePetriPriorita;
import uniBS.ingeSW.progettoV5.utils.CaricamentoDaFileHandler;
import uniBS.ingeSW.progettoV5.utils.ConvertitoreJson;
import uniBS.ingeSW.progettoV5.utils.salvataggioFile;
import uniBS.ingeSW.progettoV5.utils.eccezioni.ErroreFormatoException;
import uniBS.ingeSW.progettoV5.utils.eccezioni.giaPresenteException;

public class SalvataggioFileTest {

    @Test
    public void testNewFileCreated() {
		String name = "reteProvaTest";
		ReteSemplice reteProvaTest = new ReteSemplice();
		try {
			Posto postoTest = new Posto("1");
			reteProvaTest.addPosto(postoTest);
			Transizione transTest = new Transizione("a");
			reteProvaTest.addTrans(transTest);
			reteProvaTest.addElemFlusso(new ElemFlusso(postoTest,transTest));
			reteProvaTest.addElemFlusso(new ElemFlusso(transTest,postoTest));
		} catch (giaPresenteException | ErroreFormatoException e) {
			e.getMessage();
		}
		
		File reteFolder = new File("RETI");
		int numFileBefore = reteFolder.listFiles().length;
		String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(reteProvaTest);
		salvataggioFile.salvaRete(reteProvaJsonTest,name);
		int numFileAfter = reteFolder.listFiles().length;
		assertNotEquals(numFileBefore, numFileAfter);
    }


	@Test
	public void testNomeCorretto() {
		String name = "reteProvaTest";
		ReteSemplice reteProvaTest = new ReteSemplice();
		try {
			Posto postoTest = new Posto("1");
			reteProvaTest.addPosto(postoTest);
			Transizione transTest = new Transizione("a");
			reteProvaTest.addTrans(transTest);
			reteProvaTest.addElemFlusso(new ElemFlusso(postoTest,transTest));
			reteProvaTest.addElemFlusso(new ElemFlusso(transTest,postoTest));
		} catch (giaPresenteException | ErroreFormatoException e) {
			e.getMessage();
		}
		
		File reteFolder = new File("RETI");
		String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(reteProvaTest);
		salvataggioFile.salvaRete(reteProvaJsonTest,name);
		File[] listaFileRete = reteFolder.listFiles();
		for(File file : listaFileRete){
			System.out.println(file.getName());
			if(file.getName().equalsIgnoreCase(name)) {
				assertEquals(file.getName(),name);
				return;
			}
		}
		fail("file non trovato");
	}

	@Test
	public void testReteSalvataCorrettamente() {
		String name = "reteProvaTest";
		ReteSemplice reteProvaTest = new ReteSemplice();
		try {
			Posto postoTest = new Posto("1");
			reteProvaTest.addPosto(postoTest);
			Transizione transTest = new Transizione("a");
			reteProvaTest.addTrans(transTest);
			reteProvaTest.addElemFlusso(new ElemFlusso(postoTest,transTest));
			reteProvaTest.addElemFlusso(new ElemFlusso(transTest,postoTest));
		} catch (giaPresenteException | ErroreFormatoException e) {
			e.getMessage();
		}
		
		String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(reteProvaTest);
		salvataggioFile.salvaRete(reteProvaJsonTest,name);
		CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
		ReteSemplice reteLettaDaFile = handler.caricaReteDaFile("RETI/reteProvaTest");
		assertTrue(reteLettaDaFile.isEqual(reteProvaTest));
	}

	@Test
	public void testRetePetriSalvataCorrettamente() {
		String name = "reteProvaTest";
		ReteSemplice reteProvaTest = new ReteSemplice();
		try {
			Posto postoTest = new Posto("1");
			reteProvaTest.addPosto(postoTest);
			Transizione transTest = new Transizione("a");
			reteProvaTest.addTrans(transTest);
			reteProvaTest.addElemFlusso(new ElemFlusso(postoTest,transTest));
			reteProvaTest.addElemFlusso(new ElemFlusso(transTest,postoTest));
		} catch (giaPresenteException | ErroreFormatoException e) {
			e.getMessage();
		}
		RetePetri retePetriTest = new RetePetri(reteProvaTest);
		
		
		String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(retePetriTest);
		salvataggioFile.salvaRetePetri(reteProvaJsonTest,name);
		CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
		RetePetri reteLettaDaFile = handler.caricaPNDaFile("RETI_PETRI/reteProvaTest");
		assertTrue(retePetriTest.isEqual(reteLettaDaFile));
	}

	@Test
	public void testRetePetriPrioritaSalvataCorrettamente() {
		String name = "reteProvaTest";
		ReteSemplice reteProvaTest = new ReteSemplice();
		try {
			Posto postoTest = new Posto("1");
			reteProvaTest.addPosto(postoTest);
			Transizione transTest = new Transizione("a");
			reteProvaTest.addTrans(transTest);
			reteProvaTest.addElemFlusso(new ElemFlusso(postoTest,transTest));
			reteProvaTest.addElemFlusso(new ElemFlusso(transTest,postoTest));
		} catch (giaPresenteException | ErroreFormatoException e) {
			e.getMessage();
		}
		RetePetri retePetriTest = new RetePetri(reteProvaTest);
		RetePetriPriorita retePNPTest = new RetePetriPriorita(retePetriTest);
		
		
		String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(retePNPTest);
		salvataggioFile.salvaRetePetriPriorita(reteProvaJsonTest,name);
		CaricamentoDaFileHandler handler = new CaricamentoDaFileHandler();
		RetePetriPriorita reteLettaDaFile = handler.caricaPNPDaFile("RETI_PETRI_PRIORITA/reteProvaTest");
		assertTrue(retePNPTest.isEqual(reteLettaDaFile));
	}
}
