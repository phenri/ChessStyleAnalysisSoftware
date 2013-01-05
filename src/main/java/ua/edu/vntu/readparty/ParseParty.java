package ua.edu.vntu.readparty;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.descriptions.Castling;
import ua.edu.vntu.descriptions.EndParty;
import ua.edu.vntu.descriptions.MovingDescription;
import ua.edu.vntu.descriptions.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParseParty implements Runnable{

    static int id = 0;

    private Map<String,String> tags = new TreeMap<String, String>();

    ArrayList<MovingDescription> whiteMoves, blackMoves;

    List<String> party;

    public ParseParty(List<String> party) {
        this.party = party;
        new Thread(this).start();

    }

    @Override
    public void run() {
        readCodeAndTags(party);
    }

    /**
     * Читання тегів та коду партії
     * @param list список рядків з яких буде проводитись читання
     * @return повертає список рядків, в яких міститься код партії, теги записуються в поля класу зміння tags
     */
    public ArrayList<String> readCodeAndTags(List<String> list){
        System.err.println(Thread.currentThread());

        ArrayList<String> code = new ArrayList<>();

        for (String s: list){
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
        parseCode(code);
        return code;
    }

    private String getTagContent(String in){    //зчитування зм1сту тега, те що записано в кавичках
        try{
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
        readMoves(result);
        return result;
    }

    /**
     * @return Повертає теги шо описують партію
     */
    public Map getTags(){
        return tags;
    }

    private void readMoves(ArrayList<String> strings){
        String[] white = new String[strings.size()],
                black = new String[strings.size()];
        int i = 0;
        EndParty end = EndParty.NOT_HAVE_END;
        for(String s:strings){
            String[] words = s.split(" ");
            white[i] = words[1];

            black[i] = words[2];
            i++;

            if (s.contains("  ")){
                String m =  s.replace("  ", " ");
            }
            for (String st:words){
                if(st.contains("1-0")){
                    end = EndParty.WHITE_WIN;
                }else
                if (st.contains("0-1")){
                    end = EndParty.BLACK_WIN;
                }
                else
                if(st.contains("1/2-1/2")){
                    end = EndParty.NOBODY;
                }
            }
        }
        MovingDescription endParty = new MovingDescription(end);

        whiteMoves = parseToMovingDescription(white);
        whiteMoves.add(endParty);
        blackMoves = parseToMovingDescription(black);
        blackMoves.add(endParty);

    }

    private  ArrayList<MovingDescription> parseToMovingDescription(String[] move){
        int i = 0;
        ArrayList<MovingDescription> descriptions = new ArrayList<MovingDescription>();
        MovingDescription description;
        for(String s:move){

            char[] chars = s.toCharArray();

            FigureName figure;
            if (chars.length == 0){
                continue;
            }
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
                    figure = null;
                    break;
                case ' ':
                    continue;
                case 'P':
                    figure = FigureName.PAWN;
                    break;
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
