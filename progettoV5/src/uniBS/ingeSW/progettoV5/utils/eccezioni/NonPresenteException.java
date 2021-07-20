package uniBS.ingeSW.progettoV5.utils.eccezioni;

public class NonPresenteException extends Exception {
    
    String message;

    public NonPresenteException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return this.message;
    }
}
