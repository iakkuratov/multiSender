package net.ddns.pzshare.messenger.loop;

import net.ddns.pzshare.messenger.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoopWorker extends Worker {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void start() {
        log.info("Starting new loopWorker");
    }

    @Override
    public void stop(){ log.info("Stopping loopWorker");}

    @Override
    public void send(String userId, String msg) {
        toSubscribers(userId, msg);
    }
}
