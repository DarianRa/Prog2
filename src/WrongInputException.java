package Übungsblatt4;
public class WrongInputException extends RuntimeException{
    
    public WrongInputException(){
        super();
    }

    public WrongInputException(String s){
        super(s);
    }
}
