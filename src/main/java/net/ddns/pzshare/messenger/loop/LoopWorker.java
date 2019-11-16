package net.ddns.pzshare.messenger.loop;

import net.ddns.pzshare.messenger.SendException;
import net.ddns.pzshare.messenger.Worker;

public class LoopWorker extends Worker {
    @Override
    public void start() {

    }

    @Override
    public void send(String userId, String msg) {
        toSubscribers(userId, msg);
    }
}
