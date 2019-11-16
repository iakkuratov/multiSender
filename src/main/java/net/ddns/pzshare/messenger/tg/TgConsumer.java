package net.ddns.pzshare.messenger.tg;

public interface TgConsumer {
    void toSubscribers(String id, String text);
}
