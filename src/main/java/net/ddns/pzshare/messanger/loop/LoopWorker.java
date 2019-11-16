package net.ddns.pzshare.messanger.loop;

import net.ddns.pzshare.messanger.SendException;
import net.ddns.pzshare.messanger.Worker;

public class LoopWorker extends Worker {
    @Override
    public void start() {

    }

    @Override
    public void send(String userId, String msg) throws SendException {
        toSubscribers(userId, msg);
    }
}
