package ua.edu.vntu.readparty;

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

    private String[] partyCode;

    public Parser(String filename){
        ArrayList<String> codeList = parseCode(readCodeAndTags(readPGN(filename)));
        partyCode = new String[codeList.size()];
        int i = 0;

        for(String s:codeList){
            partyCode[i] = s;
            i++;
        }
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
            String content = new String(chars);
            return content;

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
    public String[] getPartyCode(){
        return partyCode;
    }

}
