package uniBS.ingeSW.progettoV5.logica.rete;

import java.util.ArrayList;

import uniBS.ingeSW.progettoV5.utils.eccezioni.NonPresenteException;

public interface ReteInterface {

    public ArrayList<Posto> getInsiemePosti();
    
    public ArrayList<Transizione> getInsiemeTransizioni();
    
    public ArrayList<ElemFlusso> getRelazioneFlusso();
    
    public Posto getPostoByName(String daCercare) throws NonPresenteException;
    
    public Transizione getTransByName(String daCercare) throws NonPresenteException;
    
    public ElemFlusso getElemFlussoByName(String daCercare) throws NonPresenteException;
    
    public boolean isEqual(ReteInterface toCheck);
    
    public boolean emptyControl();

}
