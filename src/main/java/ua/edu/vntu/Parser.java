package ua.edu.vntu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 02.11.12
 * Time: 22:42
 */
public class Parser  {
    private ArrayList<String> white,black;
    private Map<String,String> tags = new HashMap<String, String>();
    public Parser(){
        readPGN();
    }

    private void readPGN(){
        try{
            FileInputStream file = new FileInputStream("tmp\\file.pgn"); //you are must create this file, or write new path to file
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
            readTags(content);

        }catch (IOException e){}
    }

    private void readTags(String pgn){
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
        getMotion(code);
    }

    private String getTagContent(String in){
        try{
            String content;

            int begin = in.indexOf('"');
            int end = in.lastIndexOf('"');

            char body[] = new char[end - begin];
            in.getChars(begin + 1,end,body,0);
            content = new String(body);

            return content;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    private Map<String,Map> getMotion(ArrayList<String> code){     //метод для сортування ходiв
        int count = 0;
        for(String s:code){
            if(s.indexOf('.')==-1){
                continue;

            }
            int dec = 1;
            while (s.indexOf('.') != -1){

                System.out.println(count);
                switch (count){
                    case 10 : {dec = 2;
                        break; }
                    case 20 : {dec = 3;
                        break; }
                    case 30 : {dec = 4;
                        break; }

                }


                char [] chars = s.toCharArray();

                int beginMove = s.indexOf('.');

                chars[beginMove] = '~';
                s = new String(chars);
                int endMove = s.indexOf('.');

                if ((endMove == -dec)){

                    char[] move = new char[s.length() - beginMove + dec];

                    s.getChars(beginMove-dec, s.length(), move, 0);

//                    s = new String(move);
//                    move = new char[s.length()+1];
//                    s.getChars(0, s.lastIndexOf(' '),move,0);
//
                    System.out.println(new String(move));

                    continue;

                }

//                System.out.println("end = " + endMove);
                s = new String(chars);

                char[] move = new char[endMove - beginMove];

                s.getChars(beginMove-dec, endMove - dec, move, 0);

                System.out.println(new String(move));


            }

            count++;
        }


        return null;
    }

    public Map getTags(){
        return tags;
    }
}
