package ua.edu.vntu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: slavik
 * Date: 02.11.12
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class Parser  {
    private Map<String,String> white,black;
    private String event, site, date,round,playerWhite,playerBlack,result;
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
            parseString(content,"\n");

        }catch (IOException e){}
    }

    private void parseString(String s, String split){
        String s1[] =  s.split(split);
    }
}
