package ua.edu.vntu.readparty;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Castling;
import ua.edu.vntu.descriptions.EndParty;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Position;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 02.11.12
 * Time: 22:42
 */
public class Parser  {

    private Map<String,String> tags = new TreeMap<String, String>();

    ArrayList<MovingDescription> whiteMoves, blackMoves;

    public ArrayList<MovingDescription> getBlackMoves() {
        return blackMoves;
    }

    public ArrayList<MovingDescription> getWhiteMoves() {
        return whiteMoves;
    }

    public Parser(String filename){
        ArrayList<String> codeList = parseCode(readCodeAndTags(readPGN(filename)));

        readMoves(codeList);

    }

    private String readPGN(String filename){
        try{
            FileInputStream file = new FileInputStream(filename); //you are must create this file, or write new path to file
            int c, i = 0;
            char[] chars = new char[file.available()];

            while(( c = file.read())!= -1){
                if (c == (int)'{'){                 //this code ignores text comment with is in {...}
                    while ((c = file.read())!= '}'){
                        continue;
                    }
                    c = file.read();
                }
                if (c == (int)';'){                 //this code ignores text comment in ;.......\n
                    while ((c = file.read())!= '\n'){
                        continue;
                    }
                }
                chars[i] = (char) c;
                i++;
            }
            return new String(chars);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> readCodeAndTags(String pgn){
        pgn = pgn.trim();
        String strings[] =  pgn.split("\n");

        ArrayList<String> code = new ArrayList<String>();  //this variable used for saving a coding game
        int i = 0;
        for (String s: strings){
            if (s.contains("[Event")){
                tags.put("Event",getTagContent(s));
            }  else
            if (s.contains("[Site")){
                tags.put("Site",getTagContent(s));
            }  else
            if (s.contains("[Date")){
                tags.put("Date",getTagContent(s));
            }  else
            if (s.contains("[Round")){
                tags.put("Round",getTagContent(s));
            }  else
            if (s.contains("[White")){
                tags.put("White",getTagContent(s));
            }  else
            if (s.contains("[Black")){
                tags.put("Black",getTagContent(s));
            }  else
            if (s.contains("[Result")){
                tags.put("Result",getTagContent(s));
            }  else
            if (!(s.contains("[") || s.contains("]"))){
                code.add(s);
            }
        }
        return code;
    }

    private String getTagContent(String in){    //зчитування зм1сту тега, те що записано в кавичках
        try{
            String content;

            int begin = in.indexOf('"');
            int end = in.lastIndexOf('"');

            char body[] = new char[end - begin];
            in.getChars(begin + 1,end,body,0);
            return new String(body);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> parseCode(ArrayList<String> code){     //метод для сортування ходiв. На входi колекц1я рядк1в ход1в, на виход1 колекц1я кожен х1д в окреому рядку
        int count = 0;
        ArrayList<String> result = new ArrayList<String>();
        for(String s:code){
            if(s.indexOf('.')==-1){
                continue;

            }
            int dec = 1;
            while (s.indexOf('.') != -1){
                if (count > 10)
                    dec = 2;
                char [] chars = s.toCharArray();
                int beginMove = s.indexOf('.');
                chars[beginMove] = ' ';
                s = new String(chars);
                int endMove = s.indexOf('.');
                if ((endMove == -1)){
                    char[] move = new char[s.length() - beginMove + dec];
                    s.getChars(beginMove-dec, s.length(), move, 0);
                    result.add(new String(move));
                    count++;
                    continue;

                }
                s = new String(chars);
                char[] move = new char[endMove - beginMove];
                s.getChars(beginMove-dec, endMove - dec, move, 0);
                result.add(new String(move));
                count++;
            }
        }
        return result;
    }

    public Map getTags(){
        return tags;
    }

    private void readMoves(ArrayList<String> strings){
        String[] white = new String[strings.size()],
                black = new String[strings.size()];
        int i = 0;
        for(String s:strings){
            String[] words = s.split(" ");
            white[i] = words[1];

            black[i] = words[2];
            i++;
        }
        whiteMoves = parseToMovingDescription(white);
        blackMoves = parseToMovingDescription(black);

    }

    private  ArrayList<MovingDescription> parseToMovingDescription(String[] move){
        int i = 0;
        ArrayList<MovingDescription> descriptions = new ArrayList<MovingDescription>();
        MovingDescription description;
        for(String s:move){

            char[] chars = s.toCharArray();

            FigureName figure;
            switch (chars[0]){
                case 'N':
                    figure = FigureName.KNIGHT;
                    break;
                case 'B':
                    figure = FigureName.BISHOP;
                    break;
                case 'R':
                    figure = FigureName.ROOK;
                    break;
                case 'Q':
                    figure = FigureName.QUEEN;
                    break;
                case 'K':
                    figure = FigureName.KING;
                    break;
                case 'O':
                    figure = null;
                    break;
                case '1':
                    if (chars[0] == '1')
                        description = new MovingDescription(new EndParty(true));
                    else {
                        description = new MovingDescription(new EndParty(false));
                    }
                    descriptions.add(description);
                    figure = null;
                    break;
                case 'P':
                    figure = FigureName.PAWN;
                default:
                    figure = FigureName.PAWN;
            }



            int index = chars.length - 1;
            int length = chars.length;

            if (s.contains("+")||s.contains("?")||s.contains("!")){
                index--;
                length--;
            }

            if (s.contains("O")){
                boolean b = chars.length != 3;
                description = new MovingDescription(new Castling(b));
                descriptions.add(description);
                continue;

            }
            if (figure == null)
                continue;

            int num = 0;

            if(!Character.isDigit(chars[index])){
                --index;
                length--;
            }

            Position p = new Position(chars[index-1],chars[index]);

            description = new MovingDescription(p,figure);

            if (s.contains("x")) {
                description.setBeat(true);
            }

            if (figure == FigureName.PAWN && description.isBeat()) {
                description.setFromVertical(chars[0]);
            }

            if (!s.contains("x") && length == 4 && figure != FigureName.PAWN){
                if (Character.isDigit(chars[1])){
                    description.setFromHorizontal(Integer.parseInt(Character.toString(chars[1])));
                }
                else {
                    description.setFromVertical(chars[1]);
                }


            }
            descriptions.add(description);

            i++;

        }
        return descriptions;

    }

}
