package ua.edu.vntu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
//    public static ApplicationContext context = new ClassPathXmlApplicationContext("spring/config.xml");

    public static void main(String[] args) {
//        context.getBean("form");
        final int INTERVAL = 100 * 100 * 100 * 100;
        long start = System.currentTimeMillis();
        int res = 0;
        for (int i = 0; i < INTERVAL; i++) {
            res += 1;
        }
        long stop = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(stop - start);

        start = System.currentTimeMillis();
        res = 0;
        for (int i = 0; i < INTERVAL; i += 20) {
            res += 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
        }
        stop = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(stop - start);
    }
}
