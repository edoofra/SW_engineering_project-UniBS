package uniBS.ingeSW.progettoV5.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import uniBS.ingeSW.progettoV5.logica.rete.ElemFlusso;
import uniBS.ingeSW.progettoV5.logica.rete.Posto;
import uniBS.ingeSW.progettoV5.logica.rete.ReteSemplice;
import uniBS.ingeSW.progettoV5.logica.rete.Transizione;
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
	    e.printStackTrace();
	}
	
	File reteFolder = new File("RETI");
	int numFileBefore = reteFolder.listFiles().length;
	String reteProvaJsonTest = ConvertitoreJson.daOggettoAJson(reteProvaTest);
	salvataggioFile.salvaRete(name, reteProvaJsonTest);
	int numFileAfter = reteFolder.listFiles().length;
	assertNotEquals(numFileBefore, numFileAfter);
    }

}
