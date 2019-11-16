package net.ddns.pzshare.messenger;

import net.ddns.pzshare.KnownSenderException;

public class SendException extends KnownSenderException {
    public SendException(String text) {
        super(text);
    }
}
