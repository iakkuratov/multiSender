package net.ddns.pzshare.messenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public abstract class Worker {
    private final Set<Worker> receivers = new HashSet<>();

    private static final Logger log = LogManager.getLogger();

    public abstract void start() throws StartException;

    public abstract void stop();

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
    }

    public void subscribe(Worker receiver){
        receivers.add(receiver);
    }
}
