package ua.edu.vntu.parsing;

import ua.edu.vntu.chessboard.FigureName;
import ua.edu.vntu.containers.ContainerPartiesService;
import ua.edu.vntu.descriptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ParseParty implements Runnable,Tags{

    private Map<String,String> tags = new TreeMap<>();

    private List<MovingDescription> whiteMoves, blackMoves;

    private List<String> party;

    private int id;

    public ParseParty(List<String> party, int id) {
        this.party = party;
        this.id = id;
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
                tags.put(EVENT,getTagContent(s));
            }  else
            if (s.contains("[Site")){
                tags.put(SITE,getTagContent(s));
            }  else
            if (s.contains("[Date")){
                tags.put(DATE,getTagContent(s));
            }  else
            if (s.contains("[Round")){
                tags.put(ROUND,getTagContent(s));
            }  else
            if (s.contains("[White")){
                tags.put(WHITE,getTagContent(s));
            }  else
            if (s.contains("[Black")){
                tags.put(BLACK,getTagContent(s));
            }  else
            if (s.contains("[Result")){
                tags.put(RESULT,getTagContent(s));
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

    private List<String> parseCode(List<String> code){     //метод для сортування ходiв. На входi колекц1я рядк1в ход1в, на виход1 колекц1я кожен х1д в окреому рядку
        int count = 0;
        List<String> result = new ArrayList<>();
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
    public Map<String,String> getTags(){
        return tags;
    }

    private void readMoves(List<String> strings){
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

        Party party = new Party(getTags(),id,whiteMoves,blackMoves);
        ContainerPartiesService.getInstance().addParty(party);

    }

    private  ArrayList<MovingDescription> parseToMovingDescription(String[] move){
        ArrayList<MovingDescription> descriptions = new ArrayList<>();
        MovingDescription description;
        for(String s:move){

            char[] chars = s.toCharArray();

            FigureName figure;
            if (chars.length == 0){
                continue;
            }

            if (chars[0] == ' ')
                continue;

            figure = getFigureNameByChar(chars[0]);

            int index = chars.length - 1;
            int length = chars.length;

            /**
             * Якщо пишка доходить до кінця її заміняє фігура що стоїть після '='
             */
            if (s.contains("=")){
                index = s.indexOf("=");
                figure = getFigureNameByChar(chars[index+1]);
                char h = chars[index - 1];
                char v = chars[index - 2];

                Position pos = new Position(v,h);

                description = new MovingDescription(pos,FigureName.PAWN,true,figure);

                description.setTextNotation(s);
                descriptions.add(description);
                continue;
            }

            if (s.contains("+")||s.contains("?")||s.contains("!")){
                index--;
                length--;
            }

            if (s.contains("O")){
                boolean b = chars.length != 3;
                description = new MovingDescription(new Castling(b));

                description.setTextNotation(s);
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
            description.setTextNotation(s);
            descriptions.add(description);

        }
        return descriptions;

    }

    private FigureName getFigureNameByChar(char c){
        FigureName figure;

        switch (c){
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
            case 'P':
                figure = FigureName.PAWN;
                break;
            default:
                figure = FigureName.PAWN;
        }
        return figure;
    }
}
