package ua.edu.vntu.moving;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 10.11.12
 * Time: 19:33
 */
public class Position {

    private char letter;

    private byte number;

    public Position(char letter, int number){

        try{
            if (letter < 'a' || letter > 'h')
                throw new InvalidPositionException();

            if (number < 1 || number > 8)
                throw new InvalidPositionException();

            this.letter = letter;
            this.number = (byte)number;

        }catch (InvalidPositionException e){
            e.printStackTrace();
        }
    }

    public Position(char letter, char num){
        int number = Integer.parseInt(Character.toString(num));

        try{
            if (letter < 'a' || letter > 'h')
                throw new InvalidPositionException();

            if (number < 1 || number > 8)
                throw new InvalidPositionException();

            this.letter = letter;
            this.number = (byte)number;

        }catch (InvalidPositionException e){
            e.printStackTrace();
        }
    }

    public char getX(){
        return letter;
    }

    public byte getY(){
        return number;
    }

    @Override
    public String toString() {
        return ""+letter+number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj.getClass() != this.getClass())
            return false;
        try{
            Position position = (Position) obj;

            if ((position.getX() == this.getX())&&(position.getY() == this.getY())){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Throwable e){
            return false;
        }
    }

}
