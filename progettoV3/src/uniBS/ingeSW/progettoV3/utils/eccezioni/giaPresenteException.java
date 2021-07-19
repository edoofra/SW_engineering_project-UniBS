package uniBS.ingeSW.progettoV3.utils.eccezioni;

public class giaPresenteException extends Exception {
    
    String message;

    public giaPresenteException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return this.message;
    }

    

}
