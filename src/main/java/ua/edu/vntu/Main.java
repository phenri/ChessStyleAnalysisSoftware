package ua.edu.vntu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{
    public static void main( String[] args ){
//        new Parser("tmp\\file.pgn");
//        new Form();
        ApplicationContext context = new ClassPathXmlApplicationContext("cnfg.xml");
        context.getBean("form");
    }
}
