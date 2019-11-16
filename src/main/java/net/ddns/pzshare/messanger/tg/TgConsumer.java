package net.ddns.pzshare.messanger.tg;

public interface TgConsumer {
    void toSubscribers(String id, String text);
}
