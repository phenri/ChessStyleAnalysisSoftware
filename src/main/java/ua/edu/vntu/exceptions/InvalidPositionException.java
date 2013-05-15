package ua.edu.vntu.exceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException() {
        super("Координати не повинні покидати межі поля");
    }

    @Override
    public String getMessage() {
        return "Координати не повинні покидати межі поля";
    }
}
