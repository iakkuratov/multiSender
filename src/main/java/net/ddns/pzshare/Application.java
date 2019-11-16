package net.ddns.pzshare;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("FieldCanBeLocal")
public class Application {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("application.xml");
    }
}
