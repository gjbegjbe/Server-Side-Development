package app;

import concert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAnnotationApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConcertConfig.class);

        Encoreable concert = (Encoreable)ctx.getBean("concert", Performance.class);
        concert.performEncore();

        Encoreable concert2 = (Encoreable)ctx.getBean("concert2", Performance.class);
        concert2.performEncore();
    }
}
