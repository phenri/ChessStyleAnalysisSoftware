package ua.edu.vntu.gui.chessboard;

import ua.edu.vntu.readparty.Parser;

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
//        readMoves(way);
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

    }
    private void move(String[] move){
        int i = 0;
        for(String s:move){
            char[] chars = s.toCharArray();
            System.out.print(++i + ".");
            switch (chars[0]){
                case 'N':
                    System.out.println("Knight " +chars[1]+chars[2] );
                    break;
                case 'B':
                    System.out.println("Bishop " +chars[1]+chars[2]);
                    break;
                case 'R':
                    System.out.println("Rook " +chars[1]+chars[2]);
                    break;
                case 'Q':
                    System.out.println("Queen " +chars[1]+chars[2]);
                    break;
                case 'K':
                    System.out.println("King " +chars[1]+chars[2]);
                    break;
                case 'O':
                    System.out.println("Rakirovka ");
                    break;
                case '1':
                    System.out.println("Win white");
                    break;
                case '0':
                    System.out.println("Los white");
                default:
                    System.out.println("Pawn "  +chars[0]+chars[1]);
            }
        }

    }

}
