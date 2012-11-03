package ua.edu.vntu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 02.11.12
 * Time: 22:42
 */
public class Parser  {
    private Map<String,String> white,black;
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

    public Map getTags(){
        return tags;
    }
}
