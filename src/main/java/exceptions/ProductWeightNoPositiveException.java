package exceptions;

public class ProductWeightNoPositiveException extends Exception{
    public ProductWeightNoPositiveException(){
    }

    public ProductWeightNoPositiveException(String message){
        super(message);
    }
}
