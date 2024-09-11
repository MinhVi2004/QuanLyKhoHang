package ErrorResponse;

public class TheValueAlreadyExists extends Exception{

    public TheValueAlreadyExists(String errorMessage){
        super(errorMessage);
    }
}