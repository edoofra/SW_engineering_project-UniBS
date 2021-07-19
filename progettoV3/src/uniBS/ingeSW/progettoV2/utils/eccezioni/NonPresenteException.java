package uniBS.ingeSW.progettoV2.utils.eccezioni;

public class NonPresenteException extends Exception {
    
    String message;

    public NonPresenteException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return this.message;
    }

}
