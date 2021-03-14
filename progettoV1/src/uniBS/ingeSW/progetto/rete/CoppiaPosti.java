package uniBS.ingeSW.progetto.rete;

public class CoppiaPosti {
    Posto posto1;
    Posto posto2;

    public CoppiaPosti(Posto posto1, Posto posto2){
        this.posto1= posto1;
        this.posto2 = posto2;
    }

    public Posto getPosto1(){
        return this.posto1;
    }

    public Posto getPosto2(){
        return this.posto2;
    }

}
