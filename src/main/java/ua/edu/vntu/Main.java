package ua.edu.vntu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static ApplicationContext context = new ClassPathXmlApplicationContext("spring/config.xml");

    public static void main(String[] args) {
        context.getBean("form");
    }
}
