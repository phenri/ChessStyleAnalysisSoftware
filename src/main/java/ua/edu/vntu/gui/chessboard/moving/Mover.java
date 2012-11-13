package ua.edu.vntu.gui.chessboard.moving;

import ua.edu.vntu.gui.chessboard.Cells;
import ua.edu.vntu.readparty.Parser;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 10.11.12
 * Time: 18:12
 */
public class Mover  {

    private Cells cells;

    private String[] way;
    public Mover(Cells cells){
        this.cells = cells;
        Parser parser = new Parser("tmp\\file.pgn");
        way = parser.getPartyCode();
        readMoves(way);
    }


    private void readMoves(String[] strings){
        String[] white = new String[strings.length ],
                 black = new String[strings.length ];
        int i = 0;
        for(String s:strings){
            String[] words = s.split(" ");
            white[i] = words[1];

            black[i] = words[2];
            i++;
        }
        move(white);
//        move(black);

    }
    private void move(String[] move){
        int i = 0;
        ArrayList<MovingDescription> descriptions = new ArrayList<MovingDescription>();
        for(String s:move){

            char[] chars = s.toCharArray();

            String figure = "";
            System.out.print(++i + ".");
            switch (chars[0]){
                case 'N':
                    figure = "Knight";
                    break;
                case 'B':
                    figure = "Bishop";
                    break;
                case 'R':
                    figure = "Rook";
                    break;
                case 'Q':
                    figure = "Queen";
                    break;
                case 'K':
                    figure = "King";
                    break;
                case 'O':
                    figure = "Castling";
                    break;
                case '1':
                    System.out.println("Win white");
                    figure = "end";
                    break;
                case '0':
                    System.out.println("Los white");
                    figure = "end";
                    break;
                case 'P':
                    figure = "Pawn";
                default:
                    figure = "Pawn";
            }

            int index = chars.length - 1;

            if (s.contains("+")||s.contains("?")||s.contains("!"))
                index--;

            if (s.contains("O")){
                boolean b = chars.length != 3;
                MovingDescription description = new MovingDescription(new Castling(b));
//                System.out.println(description);
                descriptions.add(description);
                continue;

            }

            MovingDescription description = new MovingDescription(new Position((char)chars[index-1],chars[index]),figure);

            if (s.contains("x"))
                    description.setBeat(true);

            System.out.println(description);
            descriptions.add(description);


        }

    }

}
