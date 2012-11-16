package ua.edu.vntu.moving;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 11.11.12
 * Time: 15:41
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(){
        super("Координати не повинні покидати межі поля");
    }

    @Override
    public String getMessage() {
        return "Координати не повинні покидати межі поля";
    }
}
