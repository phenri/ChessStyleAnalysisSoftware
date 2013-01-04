package ua.edu.vntu.readparty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser  {

//    ArrayList<MovingDescription> whiteMoves, blackMoves;

//    public ArrayList<MovingDescription> getBlackMoves() {
//        return blackMoves;
//    }
//
//    public ArrayList<MovingDescription> getWhiteMoves() {
//        return whiteMoves;
//    }

    public Parser(File file){
        String pgn = readPGN(file);
        List<List<String>> parties = readPartiesFromPGN(pgn);
        for (List<String> list: parties){
            new ParseParty(list);
        }

    }

    /**
     * Зчитування файлу
     * @param f файл з якого будудь зчитуватись партії
     * @return зміст файлу в одному обєкті
     */
    private String readPGN(File f){
        try{
            FileInputStream file = new FileInputStream(f);
            int c, i = 0;
            char[] chars = new char[file.available()];

            while(( c = file.read())!= -1){
                if (c == (int)'{'){                 //this code ignores text comment with is in {...}
                    while ((c = file.read())!= '}'){
                    }
                    c = file.read();
                }
                if (c == (int)';'){                 //this code ignores text comment in ;.......\n
                    while ((c = file.read())!= '\n'){
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

    /**
     * Метод для зчитування партій з файлу.
     * @param pgn вміст файлу який відкривається
     * @return список партій. Кожна партія в окремому списку
     */
    public  List<List<String>> readPartiesFromPGN(String pgn){
        pgn = pgn.trim();
        String strings[] =  pgn.split("\n");

        List<List<String>> parties = new ArrayList<>();
        List<String> party = new ArrayList<>();

        for (String s:strings){
            if (s.contains("[Event")){
                if(!party.isEmpty())
                    parties.add(party);

                party = new ArrayList<>();
            }
            party.add(s);
        }
        parties.add(party);

        return parties;
    }

}
