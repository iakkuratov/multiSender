package net.ddns.pzshare;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class Application {
    public static void main(String[] args){
        new ClassPathXmlApplicationContext("application.xml");
    }
}
