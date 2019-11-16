package net.ddns.pzshare.messanger;

import net.ddns.pzshare.KnownSenderException;

public class SendException extends KnownSenderException {
    public SendException(String text) {
        super(text);
    }
}
