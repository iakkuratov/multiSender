package net.ddns.pzshare.messenger.tg;

interface TgConsumer {
    void toSubscribers(String id, String text);
}
