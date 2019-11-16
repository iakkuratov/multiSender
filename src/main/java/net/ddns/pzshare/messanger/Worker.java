package net.ddns.pzshare.messanger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class Worker {
    Set<Worker> receivers = new HashSet<>();

    private static Logger log = LogManager.getLogger();

    public abstract void start();

    public abstract void send(String userId, String msg) throws SendException;

    public void toSubscribers(String id, String text){
        if (receivers.isEmpty()) log.info("No receivers for " + this.getClass().getName() + " new massage would be described");

        for (Worker receiver : receivers) {
            log.debug("Received message from:" + id + " with text:" + text);
            try {
                receiver.send(id, text);
            }catch (SendException ex){
                log.error("Failed to send message: " + ex.getMessage());
            }
        }
    };

    public void subscribe(Worker receiver){
        receivers.add(receiver);
    };
}
