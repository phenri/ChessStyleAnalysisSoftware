package ua.edu.vntu.readparty;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ParsePgnToParty {

    private Map<String,String> tags = new TreeMap<String, String>();

    public void test(String pgn){
        pgn = pgn.trim();
        String strings[] =  pgn.split("\n");

        ArrayList<String> code = new ArrayList<String>();

        int  count = 0;
        for (String s:strings){
            if (s.contains("[Event")){
                count++;
            }
        }
        System.out.println("Кількість партій у файлі: "+ count);

    }

    public ArrayList<String> readCodeAndTags(String pgn){

        if (true){
            test(pgn);
            return null;
        }

        pgn = pgn.trim();
        String strings[] =  pgn.split("\n");

        ArrayList<String> code = new ArrayList<String>();
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
}
